package com.example.fptest;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class successVote extends AppCompatActivity {

    private static final int VOTING_START_HOUR = 8;
//    private static final int VOTING_END_HOUR = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_vote);

        ImageButton Home_Btn = findViewById(R.id.home_btn);
        ImageButton Back_Btn = findViewById(R.id.back_btn);
        Button Poll_Btn = findViewById(R.id.goto_poll_btn);

        Home_Btn.setOnClickListener(v -> startActivity(new Intent(successVote.this, HomePage.class)));
        Back_Btn.setOnClickListener(v -> startActivity(new Intent(successVote.this, OnGoingElections.class)));

        Poll_Btn.setOnClickListener(v -> {
            if (isVotingTime()) {
                startActivity(new Intent(successVote.this, ViewLivePolling.class));
            } else {
                showVotingTimePopup();
            }
        });
    }

    private boolean isVotingTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= VOTING_START_HOUR; //&& hour < VOTING_END_HOUR;
    }

    private void showVotingTimePopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Voting Session Information");
        builder.setMessage("Voting will be available based on the specified time. Live polling results will be available soon.");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}

































//package com.example.fptest;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageButton;
//import android.widget.Button;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class successVote extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_success_vote);
//
//        ImageButton Home_Btn = findViewById(R.id.home_btn);
//        Button Poll_Btn = findViewById(R.id.goto_poll_btn);
//
//        Home_Btn.setOnClickListener(v -> startActivity(new Intent(successVote.this, HomePage.class)));
//        Poll_Btn.setOnClickListener(v -> startActivity(new Intent(successVote.this, ViewLivePolling.class)));
//    }
//}

