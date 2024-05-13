package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class PresidentCandidate extends AppCompatActivity {

    // Database references for each candidate's votes
    private DatabaseReference candidate1Votes;
    private DatabaseReference candidate2Votes;
    private DatabaseReference candidate3Votes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_candidate);

        // Initialize database references for each candidate
        candidate1Votes = FirebaseDatabase.getInstance().getReference("candidates/PresCandidates/candidate1/votes");
        candidate2Votes = FirebaseDatabase.getInstance().getReference("candidates/PresCandidates/candidate2/votes");
        candidate3Votes = FirebaseDatabase.getInstance().getReference("candidates/PresCandidates/candidate3/votes");

        // UI Button references
        Button view_profile = findViewById(R.id.view_button);
        Button vote_candidate1 = findViewById(R.id.vote_button);
        Button vote_candidate2 = findViewById(R.id.vote2_button);
        Button vote_candidate3 = findViewById(R.id.vote3_button);
        ImageButton home_Btn = findViewById(R.id.home_btn);
        ImageButton back_Btn = findViewById(R.id.back_btn);

        // Set up view profile button
        view_profile.setOnClickListener(v -> startActivity(new Intent(PresidentCandidate.this, ConfirmVote.class)));

        // Set up vote buttons for each candidate
        vote_candidate1.setOnClickListener(v -> {
            incrementVote(candidate1Votes);
            startActivity(new Intent(PresidentCandidate.this, successVote.class));
        });

        vote_candidate2.setOnClickListener(v -> {
            incrementVote(candidate2Votes);
            startActivity(new Intent(PresidentCandidate.this, successVote.class));
        });

        vote_candidate3.setOnClickListener(v -> {
            incrementVote(candidate3Votes);
            startActivity(new Intent(PresidentCandidate.this, successVote.class));
        });

        // Set up home and back buttons
        home_Btn.setOnClickListener(v -> startActivity(new Intent(PresidentCandidate.this, HomePage.class)));
        back_Btn.setOnClickListener(v -> startActivity(new Intent(PresidentCandidate.this, OnGoingElections.class)));
    }

    // Helper function to increment votes
    private void incrementVote(DatabaseReference candidateVotes) {
        candidateVotes.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                Integer currentVotes = currentData.getValue(Integer.class);
                if (currentVotes == null) {
                    currentVotes = 1;
                } else {
                    currentVotes++;
                }
                currentData.setValue(currentVotes);
                return Transaction.success(currentData);
            }


            @Override
            public void onComplete(DatabaseError error, boolean committed, DataSnapshot currentData) {
                if (error != null) {
                    System.err.println("Database Error: " + error.getMessage());
                } else {
                    System.out.println("Transaction completed successfully, committed: " + committed);
                }
            }
        });
    }
}
