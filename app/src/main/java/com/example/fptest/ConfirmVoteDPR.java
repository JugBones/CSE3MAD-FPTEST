package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ConfirmVoteDPR extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote_dpr);

        // Set up cancel button
        Button cancel_btn = findViewById(R.id.cancels_button);
        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVoteDPR.this, PresidentCandidate.class)));

        // Set up the descriptions
        JustifiedTextView descriptionParty = findViewById(R.id.description_party);
        TextView notableCandidatesTitle = findViewById(R.id.notable_candidates_title);
        JustifiedTextView notableCandidates = findViewById(R.id.notable_candidates);
        TextView trackRecordTitle = findViewById(R.id.track_record_title);
        JustifiedTextView trackRecord = findViewById(R.id.track_record);

        descriptionParty.setText("The National Awakening Party (PKB) is a political party in Indonesia founded in 1998, largely representing the Nahdlatul Ulama (NU), the country's largest Islamic organization. PKB aims to uphold the values of Islam and promote social justice, democracy, and pluralism. The party has been a significant player in Indonesian politics, advocating for policies that support education, rural development, and religious harmony.");
        notableCandidatesTitle.setText("Notable DPR Candidates:");
        notableCandidates.setText("1. Abdul Muhaimin Iskandar:\nChairman of PKB and former Deputy Speaker of the DPR, known for his advocacy on labor rights and economic policies.\n2. Marwan Jafar: \nFormer Minister of Villages, Development of Disadvantaged Regions, and Transmigration, focusing on rural development and empowerment.\n3. Jazilul Fawaid: \nDeputy Speaker of the MPR, actively promoting religious tolerance and education reforms.");
        trackRecordTitle.setText("Track Record:");
        trackRecord.setText("1. Education Reforms: \nPKB has been instrumental in pushing for higher budget allocations for education and improving access to quality education for all Indonesians.\n2. Rural Development: \nThe party has implemented numerous programs aimed at empowering rural communities through infrastructure development and economic support.\n3. Religious Harmony: \nPKB consistently promotes policies that foster interfaith dialogue and cooperation, ensuring a peaceful coexistence among diverse religious groups.");

        // Set up ViewPager2 for the image carousel
        viewPager = findViewById(R.id.viewPagerParty);
        adapter = new ViewPagerAdapter(getImageList());
        viewPager.setAdapter(adapter);
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.pkb1); // Add your actual drawable resource names
        imageList.add(R.drawable.pkb2);
        imageList.add(R.drawable.pkb3);
        imageList.add(R.drawable.pkb4);
        return imageList;
    }
}
