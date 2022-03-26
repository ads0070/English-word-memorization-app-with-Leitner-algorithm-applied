package com.example.memvoca;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewpager2.widget.ViewPager2;

import com.example.memvoca.card.CardAdapter;
import com.example.memvoca.card.CardBackFragment;
import com.example.memvoca.card.CardFrontFragment;
import com.example.memvoca.database.MainViewModel;

import java.util.ArrayList;

public class StudyFragment extends Fragment implements ViewModelStoreOwner {

    private ViewGroup viewGroup;
    private CardAdapter cardAdapter;
    private ToggleButton button;
    private Context mContext;

    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private ViewModelStore viewModelStore = new ViewModelStore();
    private MainViewModel viewModel;

    private int count = 0;
    ArrayList<ArrayList<String>> voca = new ArrayList<ArrayList<String>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_study, container, false);

        mContext = getContext();
        TextView tv_day = viewGroup.findViewById(R.id.day_count);
        tv_day.setText("Day "+PreferenceManager.getInt(mContext, "D-day")+" 공부");

        if(viewModelFactory == null){
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());
        }
        viewModel = new ViewModelProvider(this,viewModelFactory).get(MainViewModel.class);

        viewModel.getAllZeroBox().observe(getViewLifecycleOwner(), words -> {
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

            pagerInit();
        });

        click();

        return viewGroup;
    }

    private void pagerInit(){
        ViewPager2 viewPager = viewGroup.findViewById(R.id.card_frameLayout);


        cardAdapter = new CardAdapter(voca);
        viewPager.setAdapter(cardAdapter);

        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(0);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                count = position;
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

        FrameLayout frameLayout = (FrameLayout) viewGroup.findViewById(R.id.card_frame);
        ViewPager2 viewPager = (ViewPager2) viewGroup.findViewById(R.id.card_frameLayout);

        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    changeToBack();
                    frameLayout.setVisibility(View.VISIBLE);
                    viewPager.setUserInputEnabled(false);
                }
                else {
                    changeToFront();
                    frameLayout.setVisibility(View.INVISIBLE);
                    viewPager.setUserInputEnabled(true);
                }
            }
        });
    }

    public void changeToFront() {
        Bundle bundle = new Bundle();
        bundle.putString("id", voca.get(0).get(count));
        bundle.putString("word", voca.get(1).get(count));
        bundle.putString("pronunciation", voca.get(2).get(count));

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        CardFrontFragment frontCard = new CardFrontFragment();
        frontCard.setArguments(bundle);
        fragmentTransaction.replace(R.id.card_frame, frontCard);
        fragmentTransaction.commit();
    }

    public void changeToBack() {
        Bundle bundle = new Bundle();
        bundle.putString("meaning", voca.get(3).get(count));
        bundle.putString("etymology", voca.get(4).get(count));
        bundle.putString("sod", voca.get(5).get(count));

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        CardBackFragment backCard = new CardBackFragment();
        backCard.setArguments(bundle);
        fragmentTransaction.replace(R.id.card_frame, backCard);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModelStore.clear();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore(){
        return viewModelStore;
    }
}
