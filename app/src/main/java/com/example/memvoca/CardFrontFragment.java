package com.example.memvoca;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

public class CardFrontFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_card_front, container, false);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout frameLayout = (FrameLayout) getActivity().findViewById(R.id.card_frame);
                ViewPager2 viewPager = (ViewPager2) getActivity().findViewById(R.id.card_frameLayout);

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.fragment_card_back, frameLayout, true);


                viewPager.setUserInputEnabled(false);

                ViewGroup contain = (ViewGroup) view.getParent();
                contain.setEnabled(false);
            }
        });
        return rootView;
    }
}


//카드 포지션 확인해서 해당 포지션의 프래그먼트를 카드 뒷면으로 바꿔치기하기 시도를 내일 해봅시다.