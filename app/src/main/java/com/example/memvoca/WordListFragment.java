package com.example.memvoca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import java.util.ArrayList;

public class WordListFragment extends Fragment {

    public ListView listView;
    public WordListAdapter adapter;
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private ViewModelStore viewModelStore = new ViewModelStore();
    private MainViewModel viewModel;
    private String box_type;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_word_list, container, false);

        if(getArguments() != null) {
            box_type = getArguments().getString("box_type");
        }

        if(viewModelFactory == null){
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());
        }
        viewModel = new ViewModelProvider(this,viewModelFactory).get(MainViewModel.class);

        adapter = new WordListAdapter();
        listView = (ListView) v.findViewById(R.id.card_list);

        switch (box_type) {
            case "BOX 1":
                break;
            case "BOX 2":
                break;
            case "BOX 3":
                break;
            case "BOX 4":
                break;
            case "BOX 5":
                break;
            case "BOX FINISH":
                viewModel.getAllFinishBox().observe(getViewLifecycleOwner(), words -> {
                    for(int i = 0; i < words.size(); i++) {
                        WordListItem item = new WordListItem(words.get(i).getId(),words.get(i).getWord(),words.get(i).getMeaning());
                        adapter.addItem(item);
                    }
                    listView.setAdapter(adapter);
                });
                break;
        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}