package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ConfirmVoteDPR5 extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote_dpr5);

        // Set up cancel button
        Button cancel_btn = findViewById(R.id.cancels_button);
        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVoteDPR5.this, PresidentCandidate.class)));

        // Set up the descriptions
        JustifiedTextView descriptionParty = findViewById(R.id.description_party);
        TextView notableCandidatesTitle = findViewById(R.id.notable_candidates_title);
        JustifiedTextView notableCandidates = findViewById(R.id.notable_candidates);
        TextView trackRecordTitle = findViewById(R.id.track_record_title);
        JustifiedTextView trackRecord = findViewById(R.id.track_record);

        descriptionParty.setText("The National Democratic Party (NasDem) is a political party in Indonesia founded in 2011 by Surya Paloh. NasDem focuses on restoring national values, unity, and democracy while promoting economic development and social justice.");
        notableCandidatesTitle.setText("Notable Candidates:");
        notableCandidates.setText("1. Surya Paloh:\nFounder and leader of NasDem, known for his efforts in promoting democracy and national unity.\n2. Rachmad Gobel: \nDeputy Speaker of the DPR, focusing on industrial development and innovation.\n3. Ahmad Sahroni: \nMember of DPR, advocating for anti-corruption measures and economic reforms.");
        trackRecordTitle.setText("Track Record:");
        trackRecord.setText("1. Economic Development: \nNasDem has been instrumental in promoting policies that encourage economic growth and development across Indonesia.\n2. Social Justice: \nThe party advocates for policies that support social justice and equal opportunities for all citizens.\n3. Anti-Corruption: \nNasDem consistently supports policies and initiatives aimed at combating corruption and promoting transparency in government.");

        // Set up ViewPager2 for the image carousel
        viewPager = findViewById(R.id.viewPagerParty);
        adapter = new ViewPagerAdapter(getImageList());
        viewPager.setAdapter(adapter);
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.nasdem1);
        imageList.add(R.drawable.nasdem2);
        imageList.add(R.drawable.nasdem3);
        imageList.add(R.drawable.nasdem4);
        return imageList;
    }
}
