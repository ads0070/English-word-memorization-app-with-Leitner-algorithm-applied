package com.example.memvoca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BoxListFragment extends Fragment implements View.OnClickListener {
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_box_list, container, false);
        mContext = getContext();

        LinearLayout box1 = (LinearLayout)v.findViewById(R.id.box1);
        LinearLayout box2 = (LinearLayout)v.findViewById(R.id.box2);
        LinearLayout box3 = (LinearLayout)v.findViewById(R.id.box3);
        LinearLayout box4 = (LinearLayout)v.findViewById(R.id.box4);
        LinearLayout box5 = (LinearLayout)v.findViewById(R.id.box5);
        LinearLayout finish = (LinearLayout)v.findViewById(R.id.finish);
        TextView box3_cycle = (TextView) v.findViewById(R.id.box3_cycle);
        TextView box4_cycle = (TextView) v.findViewById(R.id.box4_cycle);
        TextView box5_cycle = (TextView) v.findViewById(R.id.box5_cycle);

        box3_cycle.setText(Integer.toString(PreferenceManager.getInt(mContext, "memory_cycle_1"))+" 일");
        box4_cycle.setText(Integer.toString(PreferenceManager.getInt(mContext, "memory_cycle_2"))+" 일");
        box5_cycle.setText(Integer.toString(PreferenceManager.getInt(mContext, "memory_cycle_3"))+" 일");

        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        box4.setOnClickListener(this);
        box5.setOnClickListener(this);
        finish.setOnClickListener(this);

        return v;
    }

    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), FunctionActivity.class);

        switch (view.getId()){
            case R.id.box1:
                intent.putExtra("title","BOX 1");
                intent.putExtra("type","box_list");
                startActivity(intent);
                break;
            case R.id.box2:
                intent.putExtra("title","BOX 2");
                intent.putExtra("type","box_list");
                startActivity(intent);
                break;
            case R.id.box3:
                intent.putExtra("title","BOX 3");
                intent.putExtra("type","box_list");
                startActivity(intent);
                break;
            case R.id.box4:
                intent.putExtra("title","BOX 4");
                intent.putExtra("type","box_list");
                startActivity(intent);
                break;
            case R.id.box5:
                intent.putExtra("title","BOX 5");
                intent.putExtra("type","box_list");
                startActivity(intent);
                break;
            case R.id.finish:
                intent.putExtra("title","BOX FINISH");
                intent.putExtra("type","box_list");
                startActivity(intent);
                break;
        }
    }
}