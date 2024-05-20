package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class ViewLivePolling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_live_polling);

        ImageButton Home_Btn = findViewById(R.id.home_btn);
        Button PresPol_Btn = findViewById(R.id.pres_polling);
        Button DprPol_Btn = findViewById(R.id.dpr_polling);
        Button DpdPol_Btn = findViewById(R.id.dpd_polling);

        Home_Btn.setOnClickListener(v -> startActivity(new Intent(ViewLivePolling.this, HomePage.class)));
        PresPol_Btn.setOnClickListener(v -> startActivity(new Intent(ViewLivePolling.this, LivePolling.class)));
        DprPol_Btn.setOnClickListener(v -> startActivity(new Intent(ViewLivePolling.this, LivePolling2.class)));
        DpdPol_Btn.setOnClickListener(v -> startActivity(new Intent(ViewLivePolling.this, LivePolling3.class)));
    }
}