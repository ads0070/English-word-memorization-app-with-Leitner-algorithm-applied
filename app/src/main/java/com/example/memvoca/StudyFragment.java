package com.example.memvoca;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

public class StudyFragment extends Fragment {

    private ViewGroup viewGroup;
    private CardAdapter cardAdapter;
    private ToggleButton button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_study, container, false);

        pagerInit();

        click();

        return viewGroup;
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
                System.out.println(position);
            }
        });
        viewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

            }
        });
    }

    private void click(){
        button = (ToggleButton) viewGroup.findViewById(R.id.reverse_btn);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    FrameLayout frameLayout = (FrameLayout) getActivity().findViewById(R.id.card_frame);
                    ViewPager2 viewPager = (ViewPager2) getActivity().findViewById(R.id.card_frameLayout);

                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    inflater.inflate(R.layout.fragment_card_back, frameLayout, true);

                    frameLayout.setVisibility(View.VISIBLE);

                    viewPager.setUserInputEnabled(false);
                }else{
                    ViewPager2 viewPager = (ViewPager2) getActivity().findViewById(R.id.card_frameLayout);
                    FrameLayout frameLayout = (FrameLayout) getActivity().findViewById(R.id.card_frame);

                    frameLayout.setVisibility(View.INVISIBLE);

                    viewPager.setUserInputEnabled(true);
                }
            }
        });
    }
}
