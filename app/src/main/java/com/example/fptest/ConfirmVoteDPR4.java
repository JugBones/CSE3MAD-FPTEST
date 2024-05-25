package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ConfirmVoteDPR4 extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote_dpr4);

        // Set up cancel button
        Button cancel_btn = findViewById(R.id.cancels_button);
        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVoteDPR4.this, PresidentCandidate.class)));

        // Set up the descriptions
        JustifiedTextView descriptionParty = findViewById(R.id.description_party);
        TextView notableCandidatesTitle = findViewById(R.id.notable_candidates_title);
        JustifiedTextView notableCandidates = findViewById(R.id.notable_candidates);
        TextView trackRecordTitle = findViewById(R.id.track_record_title);
        JustifiedTextView trackRecord = findViewById(R.id.track_record);

        descriptionParty.setText("The Golongan Karya (Golkar) Party is one of the oldest political parties in Indonesia, founded in 1964. Golkar is known for its centrist and developmentalist policies, focusing on economic growth, stability, and national unity.");
        notableCandidatesTitle.setText("Notable Candidates:");
        notableCandidates.setText("1. Airlangga Hartarto:\nChairman of Golkar and Coordinating Minister for Economic Affairs, known for his economic policies and leadership.\n2. Chong Sung Kim: \nAn active member of Golkar, focusing on legal reforms and anti-corruption measures.\n3. Hetifah Sjaifudian: \nDeputy Speaker of the DPR, advocating for women's rights and education reforms.");
        trackRecordTitle.setText("Track Record:");
        trackRecord.setText("1. Economic Growth: \nGolkar has been instrumental in promoting policies that drive economic growth and development across Indonesia.\n2. Legal Reforms: \nThe party has supported significant legal reforms aimed at improving governance and reducing corruption.\n3. Women's Rights: \nGolkar consistently advocates for policies that support gender equality and the empowerment of women in all sectors.");

        // Set up ViewPager2 for the image carousel
        viewPager = findViewById(R.id.viewPagerParty);
        adapter = new ViewPagerAdapter(getImageList());
        viewPager.setAdapter(adapter);
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.golkar1);
        imageList.add(R.drawable.golk2);
        imageList.add(R.drawable.golkar3);
        imageList.add(R.drawable.golkar4);
        return imageList;
    }
}
