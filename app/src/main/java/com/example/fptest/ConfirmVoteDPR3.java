package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ConfirmVoteDPR3 extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote_dpr3);

        // Set up cancel button
        Button cancel_btn = findViewById(R.id.cancels_button);
        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVoteDPR3.this, DprCandidate.class)));

        // Set up the descriptions
        JustifiedTextView descriptionParty = findViewById(R.id.description_party);
        TextView notableCandidatesTitle = findViewById(R.id.notable_candidates_title);
        JustifiedTextView notableCandidates = findViewById(R.id.notable_candidates);
        TextView trackRecordTitle = findViewById(R.id.track_record_title);
        JustifiedTextView trackRecord = findViewById(R.id.track_record);

        descriptionParty.setText("The Indonesian Democratic Party of Struggle (PDIP) is a major political party in Indonesia founded in 1973 and re-established in 1998 under the leadership of Megawati Sukarnoputri. PDIP is known for its nationalist, populist, and social-democratic ideals, emphasizing the importance of national unity, economic sovereignty, and social justice.");
        notableCandidatesTitle.setText("Notable Candidates:");
        notableCandidates.setText("1. Ronny Talapessy:\nAn active member of PDIP known for his dedication to human rights and social justice.\n2. Puan Maharani: \nSpeaker of the DPR, focusing on educational reforms and women's empowerment.\n3. Ahmad Basarah: \nDeputy Speaker of the MPR, actively promoting national unity and legal reforms.");
        trackRecordTitle.setText("Track Record:");
        trackRecord.setText("1. Educational Reforms: \nPDIP has been a strong advocate for increasing educational budgets and improving access to quality education for all Indonesians.\n2. Women's Empowerment: \nThe party has implemented various programs aimed at promoting gender equality and empowering women in all sectors.\n3. Anti-Corruption: \nPDIP consistently supports policies and initiatives aimed at combating corruption and promoting transparency in government.");

        // Set up ViewPager2 for the image carousel
        viewPager = findViewById(R.id.viewPagerParty);
        adapter = new ViewPagerAdapter(getImageList());
        viewPager.setAdapter(adapter);
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.pdip1);
        imageList.add(R.drawable.pdip2);
        imageList.add(R.drawable.pdip3);
        imageList.add(R.drawable.pdip4);
        return imageList;
    }
}
