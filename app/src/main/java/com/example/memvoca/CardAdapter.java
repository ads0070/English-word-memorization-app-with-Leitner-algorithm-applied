package com.example.memvoca;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class CardAdapter extends FragmentStateAdapter {

    private Fragment fragment = new StudyFragment();

    public CardAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new CardFrontFragment();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("test");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1000;
    }

//    public void clickCard(StudyFragment view){
//        View card = view.getActivity().findViewById(R.id.card_frameLayout);
//        card.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                System.out.println("test");
//            }
//        });
//    }
}
