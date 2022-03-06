package com.example.memvoca;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends FragmentStateAdapter {

    public CardFrontFragment cardFrontFragment;
    public boolean flag = false;

    public CardAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment fragment1 = new CardFrontFragment();
        Fragment fragment2 = new CardBackFragment();

//        ((CardFrontFragment) fragment1).click();

        if(flag){
            return fragment2;
        }else{
            return fragment1;
        }
    }

    @Override
    public int getItemCount() {
        return 1000;
    }


    public void pointOut(){

    }

}
