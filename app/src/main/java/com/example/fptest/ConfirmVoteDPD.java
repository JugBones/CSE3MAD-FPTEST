package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ConfirmVoteDPD extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote_dpd);

        // Set up cancel button
        Button cancel_btn = findViewById(R.id.cancels_button);
        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVoteDPD.this, DpdCandidate.class)));

        // Set up ViewPager2 for the image carousel
        viewPager = findViewById(R.id.viewPagerParty);
        adapter = new ViewPagerAdapter(getImageList());
        viewPager.setAdapter(adapter);
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.ardi1);
        imageList.add(R.drawable.ardi2);
        return imageList;
    }
}
