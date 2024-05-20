package com.example.fptest;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class HomePage extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    // Constants for live polling time window
    private static final int LIVE_POLLING_START_HOUR = 8;
    private static final int LIVE_POLLING_END_HOUR = 13;

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
            if (isLivePollingTime()) {
                // If the current time is within the live polling time window, navigate to the live polling page
                Intent livepollIntent = new Intent(HomePage.this, ViewLivePolling.class);
                startActivity(livepollIntent);
            } else {
                // Show a pop-up message that the live poll is not yet open
                showLivePollNotOpenPopup();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Refresh the current user state to ensure it's up-to-date
        currentUser = auth.getCurrentUser();
    }

    private boolean isLivePollingTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= LIVE_POLLING_START_HOUR && hour < LIVE_POLLING_END_HOUR;
    }

    private void showLivePollNotOpenPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Live Polling Information");
        builder.setMessage("Live polling will be available after voting session (13.00). Please wait until the specified time.");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}


























//package com.example.fptest;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageButton;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class HomePage extends AppCompatActivity {
//    private FirebaseAuth auth;
//    private FirebaseUser currentUser;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_page);
//
//        // Initialize Firebase authentication
//        auth = FirebaseAuth.getInstance();
//        currentUser = auth.getCurrentUser();
//
//        // Find the ImageButtons
//        ImageButton profileButton = findViewById(R.id.prof_btn);
//        ImageButton scanButton = findViewById(R.id.scan_btn);
//        ImageButton livepolButton = findViewById(R.id.livepol_btn);
//
//        // Set click listeners
//        profileButton.setOnClickListener(v -> {
//            if (currentUser != null) {
//                // If the user is authenticated, navigate to the profile page
//                Intent profileIntent = new Intent(HomePage.this, ProfilePage.class);
//                startActivity(profileIntent);
//            } else {
//                // If the user is not authenticated, navigate to the login page
//                Intent loginIntent = new Intent(HomePage.this, LoginActivity.class);
//                startActivity(loginIntent);
//            }
//        });
//
//        scanButton.setOnClickListener(v -> {
//            // Start the QRCodeActivity when the scan button is clicked
//            Intent scanIntent = new Intent(HomePage.this, qrcodePage.class);
//            startActivity(scanIntent);
//        });
//
//        livepolButton.setOnClickListener(v -> {
//            // Start the QRCodeActivity when the scan button is clicked
//            Intent scanIntent = new Intent(HomePage.this, ViewLivePolling.class);
//            startActivity(scanIntent);
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // Refresh the current user state to ensure it's up-to-date
//        currentUser = auth.getCurrentUser();
//    }
//}
