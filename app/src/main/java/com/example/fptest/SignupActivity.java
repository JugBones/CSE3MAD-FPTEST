package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private EditText signupKtp, signupEmail, signupPassword;
    private Button signupButton;
    private TextView loginRedirectText;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         // at least one digit
                    "(?=.*[a-z])" +         // at least one lower case letter
                    "(?=.*[A-Z])" +         // at least one upper case letter
                    "(?=.*[@#$%^&+=])" +    // at least one special character
                    "(?=\\S+$)" +           // no white spaces
                    ".{6,12}" +             // between 6 and 12 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        signupKtp = findViewById(R.id.signup_ktp);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupKtp.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        signupEmail.setInputType(EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        signupPassword.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);

        signupKtp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    signupEmail.requestFocus();
                    return true;
                }
                return false;
            }
        });

        signupEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    signupPassword.requestFocus();
                    return true;
                }
                return false;
            }
        });

        signupPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signupButton.performClick();
                    return true;
                }
                return false;
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ktp = signupKtp.getText().toString().trim();
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();

                if (ktp.isEmpty()) {
                    signupKtp.setError("KTP number cannot be empty");
                    return;
                }
                if (user.isEmpty()) {
                    signupEmail.setError("Email cannot be empty");
                    return;
                }
                if (pass.isEmpty()) {
                    signupPassword.setError("Password cannot be empty");
                    return;
                }
                if (!validatePassword(pass)) {
                    signupPassword.setError("Password must be 6-12 characters long, include at least one capital letter, one digit, and one special character");
                    return;
                }

                // Check if KTP number is in the allowed KTP collection
                firestore.collection("allowed_ktp_numbers")
                        .whereEqualTo("ktp", ktp)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (!task.getResult().isEmpty()) {
                                        // KTP number is allowed, proceed to check if it is already used
                                        checkKtpNumberUsage(ktp, user, pass);
                                    } else {
                                        signupKtp.setError("KTP number is not registered in the allowed list");
                                    }
                                } else {
                                    Log.e(TAG, "Error checking allowed KTP number: ", task.getException());
                                    Toast.makeText(SignupActivity.this, "Error checking allowed KTP number: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }

    private boolean validatePassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private void checkKtpNumberUsage(String ktp, String user, String pass) {
        firestore.collection("ktp_numbers")
                .whereEqualTo("ktp", ktp)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                // KTP number is not used, proceed with sign up
                                createFirebaseUser(ktp, user, pass);
                            } else {
                                signupKtp.setError("KTP number is already registered");
                            }
                        } else {
                            Log.e(TAG, "Error checking KTP number usage: ", task.getException());
                            Toast.makeText(SignupActivity.this, "Error checking KTP number usage: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void createFirebaseUser(String ktp, String user, String pass) {
        auth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Save KTP number to Firestore
                            firestore.collection("ktp_numbers")
                                    .add(new KTP(ktp))
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignupActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                            } else {
                                                Log.e(TAG, "Error saving KTP number: ", task.getException());
                                                Toast.makeText(SignupActivity.this, "Error saving KTP number: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Log.e(TAG, "SignUp Failed: ", task.getException());
                            Toast.makeText(SignupActivity.this, "SignUp Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

class KTP {
    private String ktp;

    public KTP(String ktp) {
        this.ktp = ktp;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }
}
