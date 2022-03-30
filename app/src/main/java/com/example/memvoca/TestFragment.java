package com.example.memvoca;

import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.memvoca.card.CardBackFragment;
import com.example.memvoca.card.CardFrontFragment;
import com.example.memvoca.database.finishbox.FinishBox;
import com.example.memvoca.database.MainViewModel;
import com.example.memvoca.database.vocabulary.Vocabulary;
import com.example.memvoca.database.zerobox.ZeroBox;

import java.util.ArrayList;
import java.util.List;

public class TestFragment extends Fragment implements View.OnClickListener, ViewModelStoreOwner {
    private View view;
    private boolean isFront = true;

    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private ViewModelStore viewModelStore = new ViewModelStore();
    private MainViewModel viewModel;
    private Context mContext;
    private String box_num;
    private int count;
    private int num_of_words_remaining;
    private ArrayList<ArrayList<String>> voca = new ArrayList<ArrayList<String>>();
    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> word = new ArrayList<>();
    private ArrayList<String> pronunciation = new ArrayList<>();
    private ArrayList<String> meaning = new ArrayList<>();
    private ArrayList<String> etymology = new ArrayList<>();
    private ArrayList<String> sod = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test, container, false);
        mContext = getContext();

        if(getArguments() != null) {
            box_num = getArguments().getString("box_num");
        }

        TextView tv_subtitle = view.findViewById(R.id.day_count);
        tv_subtitle.setText(box_num);

        FrameLayout frameLayout = view.findViewById(R.id.test_card_frame);
        frameLayout.setOnClickListener(this);

        Button btn_unknown = view.findViewById(R.id.btn_unknown);
        Button btn_know = view.findViewById(R.id.btn_know);
        TextView unknown_word_count = view.findViewById(R.id.unknown_word_count);
        TextView know_word_count = view.findViewById(R.id.already_know_word_count);

        if(viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());
        }

        viewModel = new ViewModelProvider(this,viewModelFactory).get(MainViewModel.class);
        Intent intent = new Intent(getActivity(), EndPopupActivity.class);

        switch(box_num) {
            case "TEST":
                viewModel.getAllVocabulary().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    addVoca();
                    PreferenceManager.setInt(mContext,"total_count", voca.get(0).size());

                    if(PreferenceManager.getNum(mContext,"count") >= PreferenceManager.getInt(mContext, "total_count")) {
                        startActivity(intent);
                    } else {
                        changeToFront();
                    }
                });
                break;
            case "BOX 1":
                PreferenceManager.setBoolean(mContext, "BOX_1_TEST", true);
                viewModel.getAllFirstBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    addVoca();
                    num_of_words_remaining = voca.size();

                    if(num_of_words_remaining==0) {
                        startActivity(intent);
                    } else {
                        changeToFront();
                    }
                });
                break;
            case "BOX 2":
                PreferenceManager.setBoolean(mContext, "BOX_2_TEST", true);
                viewModel.getAllSecondBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    addVoca();
                    num_of_words_remaining = voca.size();

                    if(num_of_words_remaining==0) {
                        startActivity(intent);
                    } else {
                        changeToFront();
                    }
                });
                break;
            case "BOX 3":
                PreferenceManager.setBoolean(mContext, "BOX_3_TEST", true);
                viewModel.getAllThirdBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    addVoca();
                    num_of_words_remaining = voca.size();

                    if(num_of_words_remaining==0) {
                        startActivity(intent);
                    } else {
                        changeToFront();
                    }
                });
                break;
            case "BOX 4":
                PreferenceManager.setBoolean(mContext, "BOX_4_TEST", true);
                viewModel.getAllFourthBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    addVoca();
                    num_of_words_remaining = voca.size();

                    if(num_of_words_remaining==0) {
                        startActivity(intent);
                    } else {
                        changeToFront();
                    }
                });
                break;
            case "BOX 5":
                PreferenceManager.setBoolean(mContext, "BOX_5_TEST", true);
                viewModel.getAllFifthBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    addVoca();
                    num_of_words_remaining = voca.size();

                    if(num_of_words_remaining==0) {
                        startActivity(intent);
                    } else {
                        changeToFront();
                    }
                });
                break;
        }

        int wordTarget = PreferenceManager.getInt(mContext, "word_target_setting");
        count = PreferenceManager.getNum(mContext,"count");

        btn_unknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.insertIntoZeroBox(new ZeroBox(Integer.parseInt(voca.get(0).get(count)),
                        voca.get(1).get(count), voca.get(2).get(count), voca.get(3).get(count),
                        voca.get(4).get(count), voca.get(5).get(count)));

                unknown_word_count.setText(String.valueOf(Integer.parseInt(unknown_word_count.getText().toString()) + 1));
                PreferenceManager.setInt(mContext,"count", count++);

                if(Integer.parseInt(unknown_word_count.getText().toString())==wordTarget) {
                    PreferenceManager.setInt(mContext,"count", count);
                    startActivity(intent);
                }

                if(count >= PreferenceManager.getInt(mContext, "total_count")) {
                    startActivity(intent);
                } else {
                    changeToFront();
                }
            }
        });

        btn_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.insertIntoFinishBox(new FinishBox(Integer.parseInt(voca.get(0).get(count)),
                        voca.get(1).get(count), voca.get(2).get(count), voca.get(3).get(count),
                        voca.get(4).get(count), voca.get(5).get(count)));

                know_word_count.setText(String.valueOf(Integer.parseInt(know_word_count.getText().toString()) + 1));
                PreferenceManager.setInt(mContext,"count", count++);

                if(count >= PreferenceManager.getInt(mContext, "total_count")) {
                    startActivity(intent);
                } else {
                    changeToFront();
                }
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
        int index_num = 0;

        if ("TEST".equals(box_num)) {
            index_num = count;
        }

        Bundle bundle = new Bundle();
        bundle.putString("id", voca.get(0).get(index_num));
        bundle.putString("word", voca.get(1).get(index_num));
        bundle.putString("pronunciation", voca.get(2).get(index_num));

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        CardFrontFragment cardFrontFragment = new CardFrontFragment();
        cardFrontFragment.setArguments(bundle);
        transaction.replace(R.id.test_card_frame, cardFrontFragment);
        transaction.commit();
    }

    public void changeToBack() {
        int index_num = 0;

        if ("TEST".equals(box_num)) {
            index_num = count;
        }

        Bundle bundle = new Bundle();
        bundle.putString("meaning", voca.get(3).get(index_num));
        bundle.putString("etymology", voca.get(4).get(index_num));
        bundle.putString("sod", voca.get(5).get(index_num));


        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        CardBackFragment cardBackFragment = new CardBackFragment();
        cardBackFragment.setArguments(bundle);
        transaction.replace(R.id.test_card_frame, cardBackFragment);
        transaction.commit();
    }

    public void addVoca() {
        voca.add(id);
        voca.add(word);
        voca.add(pronunciation);
        voca.add(meaning);
        voca.add(etymology);
        voca.add(sod);
    }
}
