package com.example.fptest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LivePolling extends AppCompatActivity {

    // TextViews to display the votes and percentages for each candidate
    private TextView candidate1TextView, candidate2TextView, candidate3TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_polling);

        // Initialize TextViews
        candidate1TextView = findViewById(R.id.candidate1TextView);
        candidate2TextView = findViewById(R.id.candidate2TextView);
        candidate3TextView = findViewById(R.id.candidate3TextView);

        // Reference to the "election/candidates" node in Firebase
        DatabaseReference candidatesRef = FirebaseDatabase.getInstance().getReference("PresElection/candidates");

        // Attach a ValueEventListener to the database reference
        candidatesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int totalVotes = 0;
                // Update these variables to use Integer instead of int
                Integer candidate1Votes = snapshot.child("candidates/PresCandidates/candidate1/votes").getValue(Integer.class);
                Integer candidate2Votes = snapshot.child("candidates/PresCandidates/candidate2/votes").getValue(Integer.class);
                Integer candidate3Votes = snapshot.child("candidates/PresCandidates/candidate3/votes").getValue(Integer.class);

// Ensure no null values are returned
                candidate1Votes = (candidate1Votes != null) ? candidate1Votes : 0;
                candidate2Votes = (candidate2Votes != null) ? candidate2Votes : 0;
                candidate3Votes = (candidate3Votes != null) ? candidate3Votes : 0;

// Calculate the total votes
                totalVotes = candidate1Votes + candidate2Votes + candidate3Votes;

// Avoid division by zero
                double percentageCandidate1 = totalVotes == 0 ? 0 : (double) candidate1Votes / totalVotes * 100;
                double percentageCandidate2 = totalVotes == 0 ? 0 : (double) candidate2Votes / totalVotes * 100;
                double percentageCandidate3 = totalVotes == 0 ? 0 : (double) candidate3Votes / totalVotes * 100;

// Update the TextViews with votes and percentages
                candidate1TextView.setText(String.format("Candidate 1: %d votes (%.2f%%)", candidate1Votes, percentageCandidate1));
                candidate2TextView.setText(String.format("Candidate 2: %d votes (%.2f%%)", candidate2Votes, percentageCandidate2));
                candidate3TextView.setText(String.format("Candidate 3: %d votes (%.2f%%)", candidate3Votes, percentageCandidate3));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle potential errors (e.g., logging the error)
            }
        });
    }
}
