package com.example.memvoca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TestListFragment extends Fragment implements View.OnClickListener{
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

        int Dday = PreferenceManager.getInt(mContext, "D-day");
        int cycle1 = PreferenceManager.getInt(mContext, "memory_cycle_1");
        int cycle2 = PreferenceManager.getInt(mContext, "memory_cycle_2");
        int cycle3 = PreferenceManager.getInt(mContext, "memory_cycle_3");

        LinearLayout box1 = (LinearLayout)v.findViewById(R.id.box1);
        LinearLayout box2 = (LinearLayout)v.findViewById(R.id.box2);
        LinearLayout box3 = (LinearLayout)v.findViewById(R.id.box3);
        LinearLayout box4 = (LinearLayout)v.findViewById(R.id.box4);
        LinearLayout box5 = (LinearLayout)v.findViewById(R.id.box5);
        TextView box3_cycle = (TextView) v.findViewById(R.id.box3_cycle);
        TextView box4_cycle = (TextView) v.findViewById(R.id.box4_cycle);
        TextView box5_cycle = (TextView) v.findViewById(R.id.box5_cycle);

        box3_cycle.setText(Integer.toString(PreferenceManager.getInt(mContext, "memory_cycle_1"))+" 일");
        box4_cycle.setText(Integer.toString(PreferenceManager.getInt(mContext, "memory_cycle_2"))+" 일");
        box5_cycle.setText(Integer.toString(PreferenceManager.getInt(mContext, "memory_cycle_3"))+" 일");

        boolean box1_flag = PreferenceManager.getBoolean(mContext, "BOX_1_TEST");
        boolean box2_flag = PreferenceManager.getBoolean(mContext, "BOX_2_TEST");
        boolean box3_flag = PreferenceManager.getBoolean(mContext, "BOX_3_TEST");
        boolean box4_flag = PreferenceManager.getBoolean(mContext, "BOX_4_TEST");
        boolean box5_flag = PreferenceManager.getBoolean(mContext, "BOX_5_TEST");

        if (Dday > 1 && !box1_flag){
            box1.setOnClickListener(this);
            box1.setBackground(mContext.getResources().getDrawable(R.drawable.testable));
        }
        if ((Dday%3) == 0 && !box2_flag){
            box2.setOnClickListener(this);
            box2.setBackground(mContext.getResources().getDrawable(R.drawable.testable));
        }
        if ((Dday%cycle1) == 0 && !box3_flag){
            box3.setOnClickListener(this);
            box3.setBackground(mContext.getResources().getDrawable(R.drawable.testable));
        }
        if ((Dday%cycle2) == 0 && !box4_flag){
            box4.setOnClickListener(this);
            box4.setBackground(mContext.getResources().getDrawable(R.drawable.testable));
        }
        if ((Dday%cycle3) == 0 && !box5_flag){
            box5.setOnClickListener(this);
            box5.setBackground(mContext.getResources().getDrawable(R.drawable.testable));
        }

        return v;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), FunctionActivity.class);

        switch (view.getId()) {
            case R.id.box1:
                intent.putExtra("title", "테스트");
                intent.putExtra("sub_title", "BOX 1");
                intent.putExtra("type", "box_test");
                startActivity(intent);
                break;
            case R.id.box2:
                intent.putExtra("title", "테스트");
                intent.putExtra("sub_title", "BOX 2");
                intent.putExtra("type", "box_test");
                startActivity(intent);
                break;
            case R.id.box3:
                intent.putExtra("title", "테스트");
                intent.putExtra("sub_title", "BOX 3");
                intent.putExtra("type", "box_test");
                startActivity(intent);
                break;
            case R.id.box4:
                intent.putExtra("title", "테스트");
                intent.putExtra("sub_title", "BOX 4");
                intent.putExtra("type", "box_test");
                startActivity(intent);
                break;
            case R.id.box5:intent.putExtra("title", "테스트");
                intent.putExtra("sub_title", "BOX 5");
                intent.putExtra("type", "box_test");
                startActivity(intent);
                break;
        }
    }
}
