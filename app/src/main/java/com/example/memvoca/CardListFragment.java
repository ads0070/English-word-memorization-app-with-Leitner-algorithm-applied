package com.example.memvoca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CardListFragment extends Fragment {

    public ListView listView;
    public CardListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card_list, container, false);

        adapter = new CardListAdapter();

        listView = (ListView) v.findViewById(R.id.card_list); // 미완성

        CardListItem item = new CardListItem(1, "test", "test");

        adapter.addItem(item);

        listView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
