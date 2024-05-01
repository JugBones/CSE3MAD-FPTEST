package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hanks.passcodeview.PasscodeView;

public class passcode extends AppCompatActivity {

    PasscodeView passcodeView; // Initialize variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        //Assign Variable
        passcodeView = findViewById(R.id.passcodeView);

        passcodeView.setPasscodeLength(5)
                .setLocalPasscode("12345")
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        Toast.makeText(passcode.this, "Password is Incorrect", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        startActivity(new Intent(passcode.this, successPasscode.class));

                    }
                });
    }
}