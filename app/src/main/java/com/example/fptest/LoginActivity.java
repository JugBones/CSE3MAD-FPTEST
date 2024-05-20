package com.example.fptest;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText;
    private Button loginButton;
    private FirebaseAuth auth;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signUpRedirectText);
        forgotPassword = findViewById(R.id.forgot_password);
        auth = FirebaseAuth.getInstance();

        loginEmail.setInputType(EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        loginPassword.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);

        loginEmail.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                loginPassword.requestFocus();
                return true;
            }
            return false;
        });

        loginPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginButton.performClick();
                return true;
            }
            return false;
        });

        loginButton.setOnClickListener(v -> {
            String email = loginEmail.getText().toString();
            String pass = loginPassword.getText().toString();
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (!pass.isEmpty()) {
                    auth.signInWithEmailAndPassword(email, pass)
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                showVotingTimePopup();
                                startActivity(new Intent(LoginActivity.this, HomePage.class));
                                finish();
                            }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show());
                } else {
                    loginPassword.setError("Empty fields are not allowed");
                }
            } else if (email.isEmpty()) {
                loginEmail.setError("Empty fields are not allowed");
            } else {
                loginEmail.setError("Please enter a correct email");
            }
        });

        signupRedirectText.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        forgotPassword.setOnClickListener(view -> {
            // Forgot password functionality
        });
    }

    private void showVotingTimePopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Voting Session Information");
        builder.setMessage("Voting will be available from 08:00 to 13:00. Live polling results will be available after 13:00.");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}

































//package com.example.fptest;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Patterns;
//import android.view.KeyEvent;
//import android.view.inputmethod.EditorInfo;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.auth.FirebaseAuth;
//
//public class LoginActivity extends AppCompatActivity {
//    private EditText loginEmail, loginPassword;
//    private TextView signupRedirectText;
//    private Button loginButton;
//    private FirebaseAuth auth;
//    private TextView forgotPassword;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        loginEmail = findViewById(R.id.login_email);
//        loginPassword = findViewById(R.id.login_password);
//        loginButton = findViewById(R.id.login_button);
//        signupRedirectText = findViewById(R.id.signUpRedirectText);
//        forgotPassword = findViewById(R.id.forgot_password);
//        auth = FirebaseAuth.getInstance();
//
//        loginEmail.setInputType(EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
//        loginPassword.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
//
//        loginEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_NEXT) {
//                    loginPassword.requestFocus();
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        loginPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    loginButton.performClick();
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        loginButton.setOnClickListener(v -> {
//            String email = loginEmail.getText().toString();
//            String pass = loginPassword.getText().toString();
//            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                if (!pass.isEmpty()) {
//                    auth.signInWithEmailAndPassword(email, pass)
//                            .addOnSuccessListener(authResult -> {
//                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(LoginActivity.this, HomePage.class));
//                                finish();
//                            }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show());
//                } else {
//                    loginPassword.setError("Empty fields are not allowed");
//                }
//            } else if (email.isEmpty()) {
//                loginEmail.setError("Empty fields are not allowed");
//            } else {
//                loginEmail.setError("Please enter a correct email");
//            }
//        });
//
//        signupRedirectText.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
//
//        forgotPassword.setOnClickListener(view -> {
//            // Forgot password functionality
//        });
//    }
//}
