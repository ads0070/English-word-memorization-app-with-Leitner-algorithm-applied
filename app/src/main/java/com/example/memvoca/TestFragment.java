package com.example.memvoca;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestFragment extends Fragment implements View.OnClickListener, ViewModelStoreOwner {
    private View view;
    private boolean isFront = true;

    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private ViewModelStore viewModelStore = new ViewModelStore();
    private MainViewModel viewModel;
    private Context mContext;

    private int count = 0;
    ArrayList<ArrayList<String>> voca = new ArrayList<ArrayList<String>>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test, container, false);
        mContext = getContext();

        FrameLayout frameLayout = view.findViewById(R.id.test_card_frame);
        frameLayout.setOnClickListener(this);

        Button btn_unknown = view.findViewById(R.id.btn_unknown);
        Button btn_know = view.findViewById(R.id.btn_know);
        TextView unknown_word_count = view.findViewById(R.id.unknown_word_count);
        TextView know_word_count = view.findViewById(R.id.already_know_word_count);

        if(viewModelFactory == null){
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());
        }
        viewModel = new ViewModelProvider(this,viewModelFactory).get(MainViewModel.class);

        viewModel.getAll().observe(getViewLifecycleOwner(), words -> {
            voca.clear();
            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> word = new ArrayList<>();
            ArrayList<String> pronunciation = new ArrayList<>();
            ArrayList<String> meaning = new ArrayList<>();
            ArrayList<String> etymology = new ArrayList<>();
            ArrayList<String> sod = new ArrayList<>();

            words.forEach(s -> id.add(String.valueOf(s.getId())));
            words.forEach(s -> word.add(s.getWord()));
            words.forEach(s -> pronunciation.add(s.getPronunciation()));
            words.forEach(s -> meaning.add(s.getMeaning()));
            words.forEach(s -> etymology.add(s.getEtymology()));
            words.forEach(s -> sod.add(s.getSod()));

            voca.add(id);
            voca.add(word);
            voca.add(pronunciation);
            voca.add(meaning);
            voca.add(etymology);
            voca.add(sod);
            changeToFront();
        });

        int wordTarget = PreferenceManager.getInt(mContext, "word_target_setting");

        btn_unknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unknown_word_count.setText(String.valueOf(Integer.parseInt(unknown_word_count.getText().toString()) + 1));
                count++;
                changeToFront();

                if(Integer.parseInt(unknown_word_count.getText().toString())==wordTarget) {
                    Intent intent = new Intent(getActivity(), EndPopupActivity.class);
                    startActivity(intent);
                    //Intent intent = new Intent(getActivity(), MainActivity.class);
                    //startActivity(intent);
                }
            }
        });

        btn_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                know_word_count.setText(String.valueOf(Integer.parseInt(know_word_count.getText().toString()) + 1));
                count++;
                changeToFront();
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test_card_frame:
                if(isFront) {
                    changeToBack();
                    isFront = false;
                } else {
                    changeToFront();
                    isFront = true;
                }
                break;
            case R.id.reset_target:
                break;
        }
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore(){
        return viewModelStore;
    }

    public void changeToFront() {
        Bundle bundle = new Bundle();
        bundle.putString("id", voca.get(0).get(count));
        bundle.putString("word", voca.get(1).get(count));
        bundle.putString("pronunciation", voca.get(2).get(count));

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        CardFrontFragment cardFrontFragment = new CardFrontFragment();
        cardFrontFragment.setArguments(bundle);
        transaction.replace(R.id.test_card_frame, cardFrontFragment);
        transaction.commit();
    }

    public void changeToBack() {
        Bundle bundle = new Bundle();
        bundle.putString("meaning", voca.get(3).get(count));
        bundle.putString("etymology", voca.get(4).get(count));
        bundle.putString("sod", voca.get(5).get(count));

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        CardBackFragment cardBackFragment = new CardBackFragment();
        cardBackFragment.setArguments(bundle);
        transaction.replace(R.id.test_card_frame, cardBackFragment);
        transaction.commit();
    }
}
