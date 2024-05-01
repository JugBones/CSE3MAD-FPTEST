package com.example.fptest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/** @noinspection ALL*/
public class successPasscode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_passcode);


    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}