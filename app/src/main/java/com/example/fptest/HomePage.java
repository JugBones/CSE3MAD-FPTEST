package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Find the ImageButtons
        ImageButton profileButton = findViewById(R.id.prof_btn);
        ImageButton scanButton = findViewById(R.id.scan_btn);

        // Set click listeners
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ProfileActivity when the profile button is clicked
                Intent profileIntent = new Intent(HomePage.this, ProfilePage.class);
                startActivity(profileIntent);
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the QRCodeActivity when the scan button is clicked
                Intent scanIntent = new Intent(HomePage.this, qrcodePage.class);
                startActivity(scanIntent);
            }
        });
    }
}
