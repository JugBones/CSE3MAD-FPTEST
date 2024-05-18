package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ConfirmVote3 extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote3);

        // Set up cancel button
        Button cancel_btn = findViewById(R.id.cancels_button);
        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVote3.this, PresidentCandidate.class)));

        // Set up the descriptions
        TextView descriptionGanjar = findViewById(R.id.description_ganjar);
        TextView descriptionMahfud = findViewById(R.id.description_mahfud);

        descriptionGanjar.setText("Ganjar Pranowo is an Indonesian politician and the current governor of Central Java.");
        descriptionMahfud.setText("Mahfud MD is an Indonesian politician and former Chief Justice of the Constitutional Court of Indonesia.");

        // Set up ViewPager2 for the image carousel
        viewPager = findViewById(R.id.viewPager3);
        adapter = new ViewPagerAdapter(getImageList());
        viewPager.setAdapter(adapter);
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.Ganjar1);
        imageList.add(R.drawable.Ganjar2);
        imageList.add(R.drawable.Ganjar3);
        return imageList;
    }
}
