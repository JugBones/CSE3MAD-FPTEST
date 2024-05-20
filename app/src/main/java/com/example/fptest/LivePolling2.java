package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LivePolling2 extends AppCompatActivity {

    // TextViews to display the votes and percentages for each candidate
    private TextView candidate1TextView, candidate2TextView, candidate3TextView, candidate4TextView, candidate5TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_polling2);

        // Initialize TextViews
        candidate1TextView = findViewById(R.id.dpr1TextView);
        candidate2TextView = findViewById(R.id.dpr2TextView);
        candidate3TextView = findViewById(R.id.dpr3TextView);
        candidate4TextView = findViewById(R.id.dpr4TextView);
        candidate5TextView = findViewById(R.id.dpr5TextView);
        ImageButton home_Btn = findViewById(R.id.home_btn);

        home_Btn.setOnClickListener(v -> startActivity(new Intent(LivePolling2.this, HomePage.class)));

        // Reference to the "election/candidates" node in Firebase
        DatabaseReference candidatesRef = FirebaseDatabase.getInstance().getReference("candidates/DprCandidate");

        // Attach a ValueEventListener to the database reference
        candidatesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Get the votes for each candidate directly from the snapshot
                Integer candidate1Votes = snapshot.child("dpr1/votes").getValue(Integer.class);
                Integer candidate2Votes = snapshot.child("dpr2/votes").getValue(Integer.class);
                Integer candidate3Votes = snapshot.child("dpr3/votes").getValue(Integer.class);
                Integer candidate4Votes = snapshot.child("dpr4/votes").getValue(Integer.class);
                Integer candidate5Votes = snapshot.child("dpr5/votes").getValue(Integer.class);

                // Ensure no null values are returned
                candidate1Votes = (candidate1Votes != null) ? candidate1Votes : 0;
                candidate2Votes = (candidate2Votes != null) ? candidate2Votes : 0;
                candidate3Votes = (candidate3Votes != null) ? candidate3Votes : 0;
                candidate4Votes = (candidate4Votes != null) ? candidate4Votes : 0;
                candidate5Votes = (candidate5Votes != null) ? candidate5Votes : 0;

                // Calculate the total votes
                int totalVotes = candidate1Votes + candidate2Votes + candidate3Votes + candidate4Votes + candidate5Votes;

                // Avoid division by zero
                double percentageCandidate1 = totalVotes == 0 ? 0 : (double) candidate1Votes / totalVotes * 100;
                double percentageCandidate2 = totalVotes == 0 ? 0 : (double) candidate2Votes / totalVotes * 100;
                double percentageCandidate3 = totalVotes == 0 ? 0 : (double) candidate3Votes / totalVotes * 100;
                double percentageCandidate4 = totalVotes == 0 ? 0 : (double) candidate4Votes / totalVotes * 100;
                double percentageCandidate5 = totalVotes == 0 ? 0 : (double) candidate5Votes / totalVotes * 100;


                // Update the TextViews with votes and percentages
                candidate1TextView.setText(String.format("Candidate 1: %d votes (%.2f%%)", candidate1Votes, percentageCandidate1));
                candidate2TextView.setText(String.format("Candidate 2: %d votes (%.2f%%)", candidate2Votes, percentageCandidate2));
                candidate3TextView.setText(String.format("Candidate 3: %d votes (%.2f%%)", candidate3Votes, percentageCandidate3));
                candidate4TextView.setText(String.format("Candidate 4: %d votes (%.2f%%)", candidate4Votes, percentageCandidate4));
                candidate5TextView.setText(String.format("Candidate 5: %d votes (%.2f%%)", candidate5Votes, percentageCandidate5));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle potential errors (e.g., logging the error)
            }
        });
    }
}