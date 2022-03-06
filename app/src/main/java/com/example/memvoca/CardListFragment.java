package com.example.memvoca;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CardListFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView;
        CardListAdapter adapter;

        adapter = new CardListAdapter();

        listView = (ListView) getActivity().findViewById(R.id.card_list); // 미완성
        listView.setAdapter(adapter);

        adapter.addItem(1, "test", "테스트");
    }
}
