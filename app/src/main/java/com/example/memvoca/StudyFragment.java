package com.example.memvoca;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class StudyFragment extends AppCompatActivity {

    ViewPager viewPager;
    private ArrayList<String> wordList;
    private ArrayList<String> pronunciationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_study);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        CardFrontFragment card_fragment= new CardFrontFragment();
        transaction.replace(R.id.card_frameLayout, card_fragment);
        transaction.commit();

        viewPager = findViewById(R.id.card_frameLayout);
        this.initializeData();

        viewPager.setClipToPadding(false);

        viewPager.setPadding(100, 0, 100, 0);
        viewPager.setPageMargin(50);

        viewPager.setAdapter(new CardAdapter(this, wordList, pronunciationList));
    }

    public void initializeData()
    {
        wordList = new ArrayList<>();
        pronunciationList = new ArrayList<>();

        wordList.add("test1");
        wordList.add("test2");
        wordList.add("test3");

        pronunciationList.add("p_test1");
        pronunciationList.add("p_test2");
        pronunciationList.add("p_test3");
    }
}
