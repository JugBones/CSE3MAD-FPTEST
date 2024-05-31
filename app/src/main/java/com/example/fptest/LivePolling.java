package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LivePolling extends AppCompatActivity {

    private TextView candidate1TextView, candidate2TextView, candidate3TextView;
    private PieChart pieChart;
    private BarChart barChart;

    // Different colors for each candidates
    private int[] candidateColors = {0xFFE57373, 0xFF81C784, 0xFF64B5F6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_polling);

        candidate1TextView = findViewById(R.id.candidate1TextView);
        candidate2TextView = findViewById(R.id.candidate2TextView);
        candidate3TextView = findViewById(R.id.candidate3TextView);
        ImageButton home_Btn = findViewById(R.id.home_btn);
        pieChart = findViewById(R.id.pieChart);
        barChart = findViewById(R.id.barChart);
        Spinner chartTypeSpinner = findViewById(R.id.chartTypeSpinner);

        home_Btn.setOnClickListener(v -> startActivity(new Intent(LivePolling.this, HomePage.class)));

        DatabaseReference candidatesRef = FirebaseDatabase.getInstance().getReference("candidates/PresCandidates");

        candidatesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Integer candidate1Votes = snapshot.child("candidate1/votes").getValue(Integer.class);
                Integer candidate2Votes = snapshot.child("candidate2/votes").getValue(Integer.class);
                Integer candidate3Votes = snapshot.child("candidate3/votes").getValue(Integer.class);

                candidate1Votes = (candidate1Votes != null) ? candidate1Votes : 0;
                candidate2Votes = (candidate2Votes != null) ? candidate2Votes : 0;
                candidate3Votes = (candidate3Votes != null) ? candidate3Votes : 0;

                int totalVotes = candidate1Votes + candidate2Votes + candidate3Votes;
                double percentageCandidate1 = totalVotes == 0 ? 0 : (double) candidate1Votes / totalVotes * 100;
                double percentageCandidate2 = totalVotes == 0 ? 0 : (double) candidate2Votes / totalVotes * 100;
                double percentageCandidate3 = totalVotes == 0 ? 0 : (double) candidate3Votes / totalVotes * 100;

                candidate1TextView.setText(String.format("Candidate 1: %d votes (%.0f%%)", candidate1Votes, percentageCandidate1));
                candidate2TextView.setText(String.format("Candidate 2: %d votes (%.0f%%)", candidate2Votes, percentageCandidate2));
                candidate3TextView.setText(String.format("Candidate 3: %d votes (%.0f%%)", candidate3Votes, percentageCandidate3));

                updateCharts(candidate1Votes, candidate2Votes, candidate3Votes, percentageCandidate1, percentageCandidate2, percentageCandidate3);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle potential errors (e.g., logging the error)
            }
        });

        chartTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Pie Chart
                        pieChart.setVisibility(View.VISIBLE);
                        barChart.setVisibility(View.GONE);
                        break;
                    case 1: // Bar Chart
                        pieChart.setVisibility(View.GONE);
                        barChart.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });
    }

    private void updateCharts(int candidate1Votes, int candidate2Votes, int candidate3Votes, double percentageCandidate1, double percentageCandidate2, double percentageCandidate3) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry((float) percentageCandidate1, "Candidate 1"));
        pieEntries.add(new PieEntry((float) percentageCandidate2, "Candidate 2"));
        pieEntries.add(new PieEntry((float) percentageCandidate3, "Candidate 3"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Votes");
        pieDataSet.setColors(candidateColors);
        pieDataSet.setValueTextColor(0xFFFFFFFF); // White text color for values
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.setEntryLabelColor(0xFFFFFFFF); // White text color for entry labels
        pieChart.getDescription().setEnabled(false);
        Legend pieLegend = pieChart.getLegend();
        pieLegend.setTextColor(0xFFFFFFFF); // White text color for legend
        pieChart.invalidate();

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, candidate1Votes));
        barEntries.add(new BarEntry(2, candidate2Votes));
        barEntries.add(new BarEntry(3, candidate3Votes));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Votes");
        barDataSet.setColors(candidateColors);
        BarData barData = new BarData(barDataSet);
        barData.setValueTextSize(12f);
        barData.setValueTextColor(0xFFFFFFFF); // White text color for values
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        Legend barLegend = barChart.getLegend();
        barLegend.setTextColor(0xFFFFFFFF); // White text color for legend
        barChart.invalidate();
    }
}
