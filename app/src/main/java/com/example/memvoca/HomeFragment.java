package com.example.memvoca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout box_layout = (LinearLayout)v.findViewById(R.id.box_layout);
        LinearLayout test_layout = (LinearLayout)v.findViewById(R.id.test_layout);
        LinearLayout setting_layout = (LinearLayout)v.findViewById(R.id.setting_layout);

        box_layout.setOnClickListener(this);
        test_layout.setOnClickListener(this);
        setting_layout.setOnClickListener(this);

        return v;
    }

    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), FunctionActivity.class);

        switch (view.getId()){
            case R.id.box_layout:
                intent.putExtra("title","학습 박스");
                intent.putExtra("type","box");
                startActivity(intent);
                break;
            case R.id.test_layout:
                intent.putExtra("title","테스트");
                intent.putExtra("type","test");
                startActivity(intent);
                break;
            case R.id.setting_layout:
                String name = PreferenceManager.getString(getContext(), "user_name") + "님";
                intent.putExtra("title","설정");
                intent.putExtra("type","setting");
                startActivity(intent);
                break;
        }
    }
}