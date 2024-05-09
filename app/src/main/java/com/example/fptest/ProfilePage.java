package com.example.fptest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfilePage extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 100;

    private TextView emailText;
    private Button logoutButton, uploadKtpButton;
    private ImageView ktpImageView;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Uri filePath;
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Initialize views
        emailText = findViewById(R.id.email_text);
        logoutButton = findViewById(R.id.logout_button);
        uploadKtpButton = findViewById(R.id.upload_ktp_button);
        ktpImageView = findViewById(R.id.ktp_image);
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // Display the user's email
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            emailText.setText(email);

            // Load the KTP photo from Firebase Storage
            loadKtpPhoto(user.getUid());
        }

        // Initialize the image picker launcher
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        Uri imageUri = data.getData();
                        if (imageUri != null) {
                            filePath = imageUri;
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                                ktpImageView.setImageBitmap(bitmap);
                                uploadImageToFirebase();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        // Handle logout
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfilePage.this, LoginActivity.class));
            finish();
        });

        // Handle the upload button
        uploadKtpButton.setOnClickListener(v -> {
            // Check camera permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            } else {
                selectImage();
            }
        });
    }

    private void selectImage() {
        // Open the gallery to select an image
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }

    private void uploadImageToFirebase() {
        if (filePath != null) {
            // Prepare a unique path for the image in Firebase Storage
            String uid = auth.getCurrentUser().getUid();
            StorageReference ref = storageReference.child("ktp_images/" + uid + ".jpg");

            // Convert the selected image to byte array
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                // Upload to Firebase Storage
                ref.putBytes(data)
                        .addOnSuccessListener(taskSnapshot -> Toast.makeText(ProfilePage.this, "KTP photo uploaded!", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(ProfilePage.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadKtpPhoto(String uid) {
        // Reference to the stored KTP photo
        StorageReference ref = storageReference.child("ktp_images/" + uid + ".jpg");

        // Retrieve and load the image into the ImageView
        ref.getDownloadUrl().addOnSuccessListener(uri -> {
            // Load the image into the ImageView using an image loading library (e.g., Glide, Picasso)
            Glide.with(this).load(uri).into(ktpImageView);
        }).addOnFailureListener(e -> {
            // Handle the error if the download fails
            Toast.makeText(ProfilePage.this, "Failed to load KTP photo: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
