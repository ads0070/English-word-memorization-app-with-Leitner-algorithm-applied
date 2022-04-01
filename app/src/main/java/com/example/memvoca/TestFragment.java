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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.memvoca.card.CardBackFragment;
import com.example.memvoca.card.CardFrontFragment;
import com.example.memvoca.database.fifthbox.FifthBox;
import com.example.memvoca.database.finishbox.FinishBox;
import com.example.memvoca.database.MainViewModel;
import com.example.memvoca.database.fourthbox.FourthBox;
import com.example.memvoca.database.secondbox.SecondBox;
import com.example.memvoca.database.thirdbox.ThirdBox;
import com.example.memvoca.database.zerobox.ZeroBox;

import java.util.ArrayList;

public class TestFragment extends Fragment implements View.OnClickListener, ViewModelStoreOwner {
    private View view;
    private boolean isFront = true;

    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private ViewModelStore viewModelStore = new ViewModelStore();
    private MainViewModel viewModel;
    private Context mContext;
    private String box_num;
    private int count;
    private int box_word_count;
    private int box_word_index = 0;
    private ArrayList<ArrayList<String>> voca = new ArrayList<ArrayList<String>>();
    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> word = new ArrayList<>();
    private ArrayList<String> pronunciation = new ArrayList<>();
    private ArrayList<String> meaning = new ArrayList<>();
    private ArrayList<String> etymology = new ArrayList<>();
    private ArrayList<String> sod = new ArrayList<>();
    private int zero_box_size = 0;

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

        int wordTarget = PreferenceManager.getInt(mContext, "word_target_setting");
        count = PreferenceManager.getNum(mContext,"count");

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

                viewModel.getSizeZeroBox().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        zero_box_size = integer;
                        unknown_word_count.setText(String.valueOf(zero_box_size));
                    }
                });
                break;
            case "BOX 1":
                viewModel.getAllFirstBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    box_word_count=words.size();
                    if(words.isEmpty()) {
                        if(zero_box_size >= wordTarget) {
                            startActivity(intent);
                        } else {
                            intent.putExtra("title","테스트");
                            intent.putExtra("sub_title","TEST");
                            intent.putExtra("type","box_test");
                            startActivity(intent);
                        }
                    } else {
                        addVoca();
                        changeToFront();
                    }
                });

                viewModel.getSizeZeroBox().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        zero_box_size = integer;
                    }
                });
                break;
            case "BOX 2":
                viewModel.getAllSecondBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    box_word_count=words.size();
                    if(words.isEmpty()) {
                        startActivity(intent);
                    } else {
                        addVoca();
                        changeToFront();
                    }
                });
                break;
            case "BOX 3":
                viewModel.getAllThirdBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    box_word_count=words.size();
                    if(words.isEmpty()) {
                        startActivity(intent);
                    } else {
                        addVoca();
                        changeToFront();
                    }
                });
                break;
            case "BOX 4":
                viewModel.getAllFourthBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    box_word_count=words.size();
                    if(words.isEmpty()) {
                        startActivity(intent);
                    } else {
                        addVoca();
                        changeToFront();
                    }
                });
                break;
            case "BOX 5":
                viewModel.getAllFifthBox().observe(getViewLifecycleOwner(), words -> {
                    voca.clear();
                    words.forEach(s -> id.add(String.valueOf(s.getId())));
                    words.forEach(s -> word.add(s.getWord()));
                    words.forEach(s -> pronunciation.add(s.getPronunciation()));
                    words.forEach(s -> meaning.add(s.getMeaning()));
                    words.forEach(s -> etymology.add(s.getEtymology()));
                    words.forEach(s -> sod.add(s.getSod()));

                    box_word_count=words.size();
                    if(words.isEmpty()) {
                        startActivity(intent);
                    } else {
                        addVoca();
                        changeToFront();
                    }
                });
                break;
        }

        btn_unknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index_num = box_word_index;

                if ("TEST".equals(box_num)) {
                    index_num = count;
                }

                unknown_word_count.setText(String.valueOf(Integer.parseInt(unknown_word_count.getText().toString()) + 1));

                viewModel.insertIntoZeroBox(new ZeroBox(Integer.parseInt(voca.get(0).get(index_num)),
                        voca.get(1).get(index_num), voca.get(2).get(index_num), voca.get(3).get(index_num),
                        voca.get(4).get(index_num), voca.get(5).get(index_num)));

                switch (box_num) {
                    case "TEST":
                        PreferenceManager.setInt(mContext, "count", count++);

                        if (Integer.parseInt(unknown_word_count.getText().toString()) == wordTarget) {
                            PreferenceManager.setInt(mContext, "count", count);
                            startActivity(intent);
                        }

                        if (count >= PreferenceManager.getInt(mContext, "total_count")) {
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 1":
                        box_word_index++;
                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllFirstBox();

                            if(zero_box_size >= wordTarget) {
                                startActivity(intent);
                            } else {
                                intent.putExtra("title","테스트");
                                intent.putExtra("sub_title","TEST");
                                intent.putExtra("type","box_test");
                                startActivity(intent);
                                break;
                            }

                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 2":
                        box_word_index++;
                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllSecondBox();
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 3":
                        box_word_index++;
                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllThirdBox();
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 4":
                        box_word_index++;
                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllFourthBox();
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 5":
                        box_word_index++;
                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllFifthBox();
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
                }
            }
        });

        btn_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index_num = box_word_index;

                if ("TEST".equals(box_num)) {
                    index_num = count;
                }

                know_word_count.setText(String.valueOf(Integer.parseInt(know_word_count.getText().toString()) + 1));

                switch (box_num) {
                    case "TEST":
                        viewModel.insertIntoFinishBox(new FinishBox(Integer.parseInt(voca.get(0).get(index_num)),
                                voca.get(1).get(index_num), voca.get(2).get(index_num), voca.get(3).get(index_num),
                                voca.get(4).get(index_num), voca.get(5).get(index_num)));

                        PreferenceManager.setInt(mContext, "count", count++);

                        if (count >= PreferenceManager.getInt(mContext, "total_count")) {
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 1":
                        viewModel.insertIntoSecondBox(new SecondBox(Integer.parseInt(voca.get(0).get(index_num)),
                                voca.get(1).get(index_num), voca.get(2).get(index_num), voca.get(3).get(index_num),
                                voca.get(4).get(index_num), voca.get(5).get(index_num)));
                        box_word_index++;

                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllFirstBox();
                            if(zero_box_size >= wordTarget) {
                                startActivity(intent);
                            } else {
                                intent.putExtra("title","테스트");
                                intent.putExtra("sub_title","TEST");
                                intent.putExtra("type","box_test");
                                startActivity(intent);
                                break;
                            }
                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 2":
                        viewModel.insertIntoThirdBox(new ThirdBox(Integer.parseInt(voca.get(0).get(index_num)),
                                voca.get(1).get(index_num), voca.get(2).get(index_num), voca.get(3).get(index_num),
                                voca.get(4).get(index_num), voca.get(5).get(index_num)));
                        box_word_index++;

                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllSecondBox();
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 3":
                        viewModel.insertIntoFourthBox(new FourthBox(Integer.parseInt(voca.get(0).get(index_num)),
                                voca.get(1).get(index_num), voca.get(2).get(index_num), voca.get(3).get(index_num),
                                voca.get(4).get(index_num), voca.get(5).get(index_num)));
                        box_word_index++;

                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllThirdBox();
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 4":
                        viewModel.insertIntoFifthBox(new FifthBox(Integer.parseInt(voca.get(0).get(index_num)),
                                voca.get(1).get(index_num), voca.get(2).get(index_num), voca.get(3).get(index_num),
                                voca.get(4).get(index_num), voca.get(5).get(index_num)));
                        box_word_index++;

                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllFourthBox();
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
                    case "BOX 5":
                        viewModel.insertIntoFinishBox(new FinishBox(Integer.parseInt(voca.get(0).get(index_num)),
                                voca.get(1).get(index_num), voca.get(2).get(index_num), voca.get(3).get(index_num),
                                voca.get(4).get(index_num), voca.get(5).get(index_num)));
                        box_word_index++;

                        if (box_word_index >= box_word_count) {
                            viewModel.deleteAllFifthBox();
                            startActivity(intent);
                        } else {
                            changeToFront();
                        }
                        break;
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
        int index_num = box_word_index;

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
        int index_num = box_word_index;

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
