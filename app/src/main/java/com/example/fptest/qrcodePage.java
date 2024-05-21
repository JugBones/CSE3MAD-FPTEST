package com.example.fptest;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;


public class qrcodePage extends AppCompatActivity {

    private static final int VOTING_START_HOUR = 10;
    private static final int VOTING_END_HOUR = 11;
    Button scanner_btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_page);

        scanner_btn = findViewById(R.id.scanner);
        textView = findViewById(R.id.textscanner);
        ImageButton Home_Btn = findViewById(R.id.home_btn);

        Home_Btn.setOnClickListener(v -> startActivity(new Intent(qrcodePage.this, HomePage.class)));
        scanner_btn.setOnClickListener(v -> {
//            IntentIntegrator intentIntegrator = new IntentIntegrator(qrcodePage.this);
//            intentIntegrator.setOrientationLocked(true);
//            intentIntegrator.setPrompt("Scan a QR Code");
//            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
//            intentIntegrator.initiateScan();
            if (isVotingSession()) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(qrcodePage.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("Scan a QR Code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            } else {
                showVoteSession();
            }
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

    private boolean isVotingSession() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= VOTING_START_HOUR; //&& hour < VOTING_END_HOUR;
    }

    private void showVoteSession() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Voting Session Information");
        builder.setMessage("Voting will be available based on the specified time. Live polling results will be available soon.");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}