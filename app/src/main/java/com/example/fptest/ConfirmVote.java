package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ConfirmVote extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote);

        // Set up cancel button
        Button cancel_btn = findViewById(R.id.cancels_button);
        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVote.this, PresidentCandidate.class)));

        // Set up the descriptions
        TextView descriptionAnies = findViewById(R.id.description_anies);
        TextView descriptionMuhaimin = findViewById(R.id.description_muhaimin);

        descriptionAnies.setText("Anies Baswedan is the former Governor of Jakarta known for his contributions like creating Jakarta International Stadium (JIS) and the JakLingko transportation system.");
        descriptionMuhaimin.setText("Muhaimin Iskandar, also known as Cak Imin, is a prominent Indonesian politician and leader of the National Awakening Party (PKB).");

        // Set up ViewPager2 for the image carousel
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getImageList());
        viewPager.setAdapter(adapter);
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.masanies1);
        imageList.add(R.drawable.masanies2);
        imageList.add(R.drawable.masanies3);
        return imageList;
    }
}
