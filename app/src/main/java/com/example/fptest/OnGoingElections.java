package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class OnGoingElections extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_going_elections);

        Button Pres_Btn = findViewById(R.id.presidential_button);
        Button Dpr_Btn = findViewById(R.id.dpr_ri_button);
        Button Dpd_Btn = findViewById(R.id.dpd_button);
        ImageButton Home_Btn = findViewById(R.id.home_btn);
        ImageButton Back_Btn = findViewById(R.id.back_btn);

        Pres_Btn.setOnClickListener(v -> startActivity(new Intent(OnGoingElections.this, PresidentCandidate.class)));
        Dpr_Btn.setOnClickListener(v -> startActivity(new Intent(OnGoingElections.this, DprCandidate.class)));
        Dpd_Btn.setOnClickListener(v -> startActivity(new Intent(OnGoingElections.this, DpdCandidate.class)));
        Home_Btn.setOnClickListener(v -> startActivity(new Intent(OnGoingElections.this, HomePage.class)));
        Back_Btn.setOnClickListener(v -> startActivity(new Intent(OnGoingElections.this, HomePage.class)));
    }
}