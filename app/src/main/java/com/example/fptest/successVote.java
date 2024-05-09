package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class successVote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_vote);

        ImageButton Home_Btn = findViewById(R.id.home_btn);

        Home_Btn.setOnClickListener(v -> startActivity(new Intent(successVote.this, HomePage.class)));
    }
}