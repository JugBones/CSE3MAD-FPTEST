package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnGoingElections extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_going_elections);

        Button Pres_Btn = findViewById(R.id.presidential_button);

        Pres_Btn.setOnClickListener(v -> startActivity(new Intent(OnGoingElections.this, PresidentCandidate.class)));
    }
}