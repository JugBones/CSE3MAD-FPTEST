package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize Firebase authentication
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        // Find the ImageButtons
        ImageButton profileButton = findViewById(R.id.prof_btn);
        ImageButton scanButton = findViewById(R.id.scan_btn);
        ImageButton livepolButton = findViewById(R.id.livepol_btn);

        // Set click listeners
        profileButton.setOnClickListener(v -> {
            if (currentUser != null) {
                // If the user is authenticated, navigate to the profile page
                Intent profileIntent = new Intent(HomePage.this, ProfilePage.class);
                startActivity(profileIntent);
            } else {
                // If the user is not authenticated, navigate to the login page
                Intent loginIntent = new Intent(HomePage.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        scanButton.setOnClickListener(v -> {
            // Start the QRCodeActivity when the scan button is clicked
            Intent scanIntent = new Intent(HomePage.this, qrcodePage.class);
            startActivity(scanIntent);
        });

        livepolButton.setOnClickListener(v -> {
            // Start the QRCodeActivity when the scan button is clicked
            Intent scanIntent = new Intent(HomePage.this, LivePolling.class);
            startActivity(scanIntent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Refresh the current user state to ensure it's up-to-date
        currentUser = auth.getCurrentUser();
    }
}
