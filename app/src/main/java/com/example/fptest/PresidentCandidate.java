package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PresidentCandidate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_candidate);

        Button view_profile = findViewById(R.id.view_button);
        Button vote_candidate1 = findViewById(R.id.vote_button);
        Button vote_candidate2 = findViewById(R.id.vote2_button);
        Button vote_candidate3 = findViewById(R.id.vote3_button);
        ImageButton Home_Btn = findViewById(R.id.home_btn);

        view_profile.setOnClickListener(v -> startActivity(new Intent(PresidentCandidate.this, ConfirmVote.class)));
        vote_candidate1.setOnClickListener(v -> startActivity(new Intent(PresidentCandidate.this, successVote.class)));
        vote_candidate2.setOnClickListener(v -> startActivity(new Intent(PresidentCandidate.this, successVote.class)));
        vote_candidate3.setOnClickListener(v -> startActivity(new Intent(PresidentCandidate.this, successVote.class)));
        Home_Btn.setOnClickListener(v -> startActivity(new Intent(PresidentCandidate.this, HomePage.class)));
    }
}