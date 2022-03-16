package com.example.memvoca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CardBackFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card_back, container, false);

        if(getArguments() != null) {
            String meaning = getArguments().getString("meaning");
            String etymology = getArguments().getString("etymology");
            String sod = getArguments().getString("sod");

            TextView meaningTv = rootView.findViewById(R.id.mean);
            TextView etymologyTv = rootView.findViewById(R.id.radix);
            TextView sodTv = rootView.findViewById(R.id.antonyms);

            meaningTv.setText(meaning);
            etymologyTv.setText(etymology);
            sodTv.setText(sod);
        }

        return rootView;
    }
}