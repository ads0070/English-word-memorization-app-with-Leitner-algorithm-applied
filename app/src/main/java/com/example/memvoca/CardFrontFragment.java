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

        if(getArguments() != null) {
            String id = getArguments().getString("id");
            String word = getArguments().getString("word");
            String pronunciation = getArguments().getString("pronunciation");

            TextView pageTv = rootView.findViewById(R.id.page);
            TextView wordTv = rootView.findViewById(R.id.word);
            TextView pronTv = rootView.findViewById(R.id.pronunciation);

            pageTv.setText(id);
            wordTv.setText(word);
            pronTv.setText(pronunciation);
        }

        return rootView;
    }
}
