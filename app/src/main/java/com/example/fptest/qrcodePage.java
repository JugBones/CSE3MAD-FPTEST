package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class qrcodePage extends AppCompatActivity {

    Button scanner_btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_page);

        scanner_btn = findViewById(R.id.scanner);
        textView = findViewById(R.id.textscanner);

        scanner_btn.setOnClickListener(v -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(qrcodePage.this);
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setPrompt("Scan a QR Code");
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            intentIntegrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            String contents = intentResult.getContents();
            if (contents != null) {
                // Start the OnGoingElections activity with the scanned data
                Intent intent = new Intent(qrcodePage.this, OnGoingElections.class);
                intent.putExtra("QR_DATA", contents);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}