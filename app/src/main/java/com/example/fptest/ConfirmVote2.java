package com.example.fptest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class ConfirmVote2 extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_vote2);

        // Set up cancel button
        Button cancel_btn = findViewById(R.id.cancels_button);
        cancel_btn.setOnClickListener(v -> startActivity(new Intent(ConfirmVote2.this, PresidentCandidate.class)));

        // Set up the descriptions
        TextView descriptionPrabowo = findViewById(R.id.description_prabowo);
        TextView descriptionGibran = findViewById(R.id.description_gibran);

        descriptionPrabowo.setText("Prabowo Subianto is an Indonesian politician, businessman, and former lieutenant general in the Indonesian National Armed Forces.");
        descriptionGibran.setText("Gibran Rakabuming Raka is an Indonesian politician and the son of the current President of Indonesia, Joko Widodo. He is also the current mayor of Solo.");

        // Set up ViewPager2 for the image carousel
        viewPager = findViewById(R.id.viewPager2);
        adapter = new ViewPagerAdapter(getImageList());
        viewPager.setAdapter(adapter);
    }

    private List<Integer> getImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.prabowo1);
        imageList.add(R.drawable.prabowo2);
        imageList.add(R.drawable.prabowo3);
        return imageList;
    }
}
