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

public class LivePolling2 extends AppCompatActivity {

    private TextView candidate1TextView, candidate2TextView, candidate3TextView, candidate4TextView, candidate5TextView;
    private PieChart pieChart;
    private BarChart barChart;
    private int[] candidateColors = {0xFFE57373, 0xFF81C784, 0xFF64B5F6, 0xFFFFD54F, 0xFFBA68C8}; // Example colors for each candidate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_polling2);

        candidate1TextView = findViewById(R.id.dpr1TextView);
        candidate2TextView = findViewById(R.id.dpr2TextView);
        candidate3TextView = findViewById(R.id.dpr3TextView);
        candidate4TextView = findViewById(R.id.dpr4TextView);
        candidate5TextView = findViewById(R.id.dpr5TextView);
        ImageButton home_Btn = findViewById(R.id.home_btn);
        pieChart = findViewById(R.id.pieChart);
        barChart = findViewById(R.id.barChart);
        Spinner chartTypeSpinner = findViewById(R.id.chartTypeSpinner);

        home_Btn.setOnClickListener(v -> startActivity(new Intent(LivePolling2.this, HomePage.class)));

        DatabaseReference candidatesRef = FirebaseDatabase.getInstance().getReference("candidates/DprCandidate");

        candidatesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Integer candidate1Votes = snapshot.child("dpr1/votes").getValue(Integer.class);
                Integer candidate2Votes = snapshot.child("dpr2/votes").getValue(Integer.class);
                Integer candidate3Votes = snapshot.child("dpr3/votes").getValue(Integer.class);
                Integer candidate4Votes = snapshot.child("dpr4/votes").getValue(Integer.class);
                Integer candidate5Votes = snapshot.child("dpr5/votes").getValue(Integer.class);

                candidate1Votes = (candidate1Votes != null) ? candidate1Votes : 0;
                candidate2Votes = (candidate2Votes != null) ? candidate2Votes : 0;
                candidate3Votes = (candidate3Votes != null) ? candidate3Votes : 0;
                candidate4Votes = (candidate4Votes != null) ? candidate4Votes : 0;
                candidate5Votes = (candidate5Votes != null) ? candidate5Votes : 0;

                int totalVotes = candidate1Votes + candidate2Votes + candidate3Votes + candidate4Votes + candidate5Votes;
                double percentageCandidate1 = totalVotes == 0 ? 0 : (double) candidate1Votes / totalVotes * 100;
                double percentageCandidate2 = totalVotes == 0 ? 0 : (double) candidate2Votes / totalVotes * 100;
                double percentageCandidate3 = totalVotes == 0 ? 0 : (double) candidate3Votes / totalVotes * 100;
                double percentageCandidate4 = totalVotes == 0 ? 0 : (double) candidate4Votes / totalVotes * 100;
                double percentageCandidate5 = totalVotes == 0 ? 0 : (double) candidate5Votes / totalVotes * 100;

                candidate1TextView.setText(String.format("Candidate 1: %d votes (%.0f%%)", candidate1Votes, percentageCandidate1));
                candidate2TextView.setText(String.format("Candidate 2: %d votes (%.0f%%)", candidate2Votes, percentageCandidate2));
                candidate3TextView.setText(String.format("Candidate 3: %d votes (%.0f%%)", candidate3Votes, percentageCandidate3));
                candidate4TextView.setText(String.format("Candidate 4: %d votes (%.0f%%)", candidate4Votes, percentageCandidate4));
                candidate5TextView.setText(String.format("Candidate 5: %d votes (%.0f%%)", candidate5Votes, percentageCandidate5));

                updateCharts(candidate1Votes, candidate2Votes, candidate3Votes, candidate4Votes, candidate5Votes, percentageCandidate1, percentageCandidate2, percentageCandidate3, percentageCandidate4, percentageCandidate5);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle potential errors
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

    private void updateCharts(int candidate1Votes, int candidate2Votes, int candidate3Votes, int candidate4Votes, int candidate5Votes, double percentageCandidate1, double percentageCandidate2, double percentageCandidate3, double percentageCandidate4, double percentageCandidate5) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry((float) percentageCandidate1, "Candidate 1"));
        pieEntries.add(new PieEntry((float) percentageCandidate2, "Candidate 2"));
        pieEntries.add(new PieEntry((float) percentageCandidate3, "Candidate 3"));
        pieEntries.add(new PieEntry((float) percentageCandidate4, "Candidate 4"));
        pieEntries.add(new PieEntry((float) percentageCandidate5, "Candidate 5"));

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
        barEntries.add(new BarEntry(4, candidate4Votes));
        barEntries.add(new BarEntry(5, candidate5Votes));

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
