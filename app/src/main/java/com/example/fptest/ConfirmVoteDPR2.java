package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ConfirmVoteDPR2 extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote_dpr2);

        // Set up cancel button
        Button cancel_btn = findViewById(R.id.cancels_button);
        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVoteDPR2.this, PresidentCandidate.class)));

        // Set up the descriptions
        JustifiedTextView descriptionParty = findViewById(R.id.description_party);
        TextView notableCandidatesTitle = findViewById(R.id.notable_candidates_title);
        JustifiedTextView notableCandidates = findViewById(R.id.notable_candidates);
        TextView trackRecordTitle = findViewById(R.id.track_record_title);
        JustifiedTextView trackRecord = findViewById(R.id.track_record);

        descriptionParty.setText("The Great Indonesia Movement Party (Gerindra) is a political party in Indonesia established in 2008 by Prabowo Subianto. Gerindra focuses on nationalism, economic independence, and social justice. The party aims to strengthen Indonesia's sovereignty and promote the welfare of the Indonesian people through sustainable development and equitable distribution of resources.");
        notableCandidatesTitle.setText("Notable DPR Candidates:");
        notableCandidates.setText("1. Prabowo Subianto:\nChairman of Gerindra and former Lieutenant General, known for his strong stance on national defense and economic independence.\n2. Ahmad Muzani: \nSecretary-General of Gerindra and Deputy Speaker of the DPR, advocating for social justice and rural development.\n3. Fadli Zon: \nVice Chairman of Gerindra, actively promoting democracy and human rights.");
        trackRecordTitle.setText("Track Record:");
        trackRecord.setText("1. National Defense: \nGerindra has been vocal about strengthening Indonesia's military capabilities and ensuring national security.\n2. Economic Independence: \nThe party emphasizes reducing dependence on foreign aid and promoting local industries.\n3. Social Justice: \nGerindra advocates for policies that reduce income inequality and improve the welfare of marginalized communities.");

        // Set up ViewPager2 for the image carousel
        viewPager = findViewById(R.id.viewPagerParty);
        adapter = new ViewPagerAdapter(getImageList());
        viewPager.setAdapter(adapter);
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.gerindra1); // Add your actual drawable resource names
        imageList.add(R.drawable.gerindra2);
        imageList.add(R.drawable.gerindra3);
        imageList.add(R.drawable.gerindra4);
        return imageList;
    }
}
