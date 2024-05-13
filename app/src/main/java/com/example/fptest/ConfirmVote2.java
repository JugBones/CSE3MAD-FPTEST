package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmVote2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote);

        Button cancel_btn = findViewById(R.id.cancels_button);

        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVote2.this, PresidentCandidate.class)));
    }
}