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
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ArrayList<ArrayList<String>> voca;

    public CardAdapter(ArrayList<ArrayList<String>> voca) {
        this.voca = voca;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card_front, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setTv(voca, position);
    }

    @Override
    public int getItemCount() {
        return 1000;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView pageTv;
        TextView wordTv;
        TextView pronTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pageTv = itemView.findViewById(R.id.page);
            wordTv = itemView.findViewById(R.id.word);
            pronTv = itemView.findViewById(R.id.pronunciation);
        }
        public void setTv(ArrayList<ArrayList<String>> voca, int position){
            wordTv.setText(voca.get(1).get(position));
            pronTv.setText(voca.get(2).get(position));
        }
    }
}
