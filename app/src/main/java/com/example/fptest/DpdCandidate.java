package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class DpdCandidate extends AppCompatActivity {

    private DatabaseReference candidate1Votes, candidate2Votes, candidate3Votes, candidate4Votes, candidate5Votes;
    private DatabaseReference userVotes;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpd_candidate);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userVotes = FirebaseDatabase.getInstance().getReference("UserVotes");
        candidate1Votes = FirebaseDatabase.getInstance().getReference("candidates/DpdCandidate/dpd1/votes");
        candidate2Votes = FirebaseDatabase.getInstance().getReference("candidates/DpdCandidate/dpd2/votes");
        candidate3Votes = FirebaseDatabase.getInstance().getReference("candidates/DpdCandidate/dpd3/votes");
        candidate4Votes = FirebaseDatabase.getInstance().getReference("candidates/DpdCandidate/dpd4/votes");
        candidate5Votes = FirebaseDatabase.getInstance().getReference("candidates/DpdCandidate/dpd5/votes");

        Button view_button = findViewById(R.id.view_button);
        Button vote_button = findViewById(R.id.vote_button);
        Button view2_button = findViewById(R.id.view2_button);
        Button vote2_button = findViewById(R.id.vote2_button);
        Button view3_button = findViewById(R.id.view3_button);
        Button vote3_button = findViewById(R.id.vote3_button);
        Button view4_button = findViewById(R.id.view4_button);
        Button vote4_button = findViewById(R.id.vote4_button);
        Button view5_button = findViewById(R.id.view5_button);
        Button vote5_button = findViewById(R.id.vote5_button);
        ImageButton home_Btn = findViewById(R.id.home_btn);
        ImageButton back_Btn = findViewById(R.id.back_btn);

        view_button.setOnClickListener(v -> startActivity(new Intent(DpdCandidate.this, ConfirmVoteDPD.class)));
        view2_button.setOnClickListener(v -> startActivity(new Intent(DpdCandidate.this, ConfirmVoteDPD2.class)));
        view3_button.setOnClickListener(v -> startActivity(new Intent(DpdCandidate.this, ConfirmVoteDPD3.class)));
        view4_button.setOnClickListener(v -> startActivity(new Intent(DpdCandidate.this, ConfirmVoteDPD4.class)));
        view5_button.setOnClickListener(v -> startActivity(new Intent(DpdCandidate.this, ConfirmVoteDPD5.class)));

        vote_button.setOnClickListener(v -> showConfirmationDialog(candidate1Votes, "DPD"));
        vote2_button.setOnClickListener(v -> showConfirmationDialog(candidate2Votes, "DPD"));
        vote3_button.setOnClickListener(v -> showConfirmationDialog(candidate3Votes, "DPD"));
        vote4_button.setOnClickListener(v -> showConfirmationDialog(candidate4Votes, "DPD"));
        vote5_button.setOnClickListener(v -> showConfirmationDialog(candidate5Votes, "DPD"));

        home_Btn.setOnClickListener(v -> startActivity(new Intent(DpdCandidate.this, HomePage.class)));
        back_Btn.setOnClickListener(v -> startActivity(new Intent(DpdCandidate.this, OnGoingElections.class)));
    }

    private void showConfirmationDialog(DatabaseReference candidateVotes, String category) {
        new AlertDialog.Builder(this)
                .setTitle("Vote Confirmation")
                .setMessage("Are you sure you want to vote for this candidate?")
                .setPositiveButton("Yes", (dialog, which) -> castVote(candidateVotes, category))
                .setNegativeButton("No", null)
                .show();
    }

    private void castVote(DatabaseReference candidateVotes, String category) {
        if (currentUser == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = currentUser.getUid();

        userVotes.child(userId + "/" + category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(DpdCandidate.this, "You have already voted", Toast.LENGTH_SHORT).show();
                } else {
                    incrementVote(candidateVotes);
                    userVotes.child(userId + "/" + category).setValue(true);
                    startActivity(new Intent(DpdCandidate.this, successVote.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DpdCandidate.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

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
