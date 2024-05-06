package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PresidentCandidate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_candidate);

        Button view_profile = findViewById(R.id.view_button);

        view_profile.setOnClickListener(v -> startActivity(new Intent(PresidentCandidate.this, ConfirmVote.class)));
    }
}