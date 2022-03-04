package com.example.memvoca;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class StudyFragment extends Fragment {

    private ViewGroup viewGroup;
    private CardAdapter cardAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_study, container, false);

        pagerInit();

        return viewGroup;
    }

    @Override
    public void onResume() {
        super.onResume();
//        cardAdapter.clickCard(this);

        View card = getActivity().findViewById(R.id.card_frameLayout);
        card.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("test");
            }
        });
    }

    private void pagerInit(){
        ViewPager2 viewPager = viewGroup.findViewById(R.id.card_frameLayout);

        cardAdapter = new CardAdapter(getActivity());
        viewPager.setAdapter(cardAdapter);

        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        viewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

            }
        });
    }
}
