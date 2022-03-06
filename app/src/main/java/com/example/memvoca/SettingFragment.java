package com.example.memvoca;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment implements View.OnClickListener {

    private static final int RESULT_OK = -1;
    private View view;
    private Context mContext;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        mContext = getContext();

        TextView mem_info = (TextView)view.findViewById(R.id.edit_mem_info);
        TextView reset_target = (TextView)view.findViewById(R.id.reset_target);
        TextView cycle_reset = (TextView)view.findViewById(R.id.cycle_reset);

        mem_info.setOnClickListener(this);
        reset_target.setOnClickListener(this);
        cycle_reset.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), ResetPopupActivity.class);

        switch (view.getId()){
            case R.id.edit_mem_info:
                intent.putExtra("title","닉네임을");
                intent.putExtra("type","reset_name");
                startActivityForResult(intent, 1);
                break;
            case R.id.reset_target:
                intent.putExtra("title","목표치를");
                intent.putExtra("type","reset_word");
                startActivityForResult(intent, 1);
                break;
            case R.id.cycle_reset:
                intent.putExtra("title","주기를");
                intent.putExtra("type","reset_day");
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                String result = data.getStringExtra("data");
                String type = data.getStringExtra("type");

                switch (type) {
                    case "reset_name":
                        PreferenceManager.setString(mContext, "user_name", result+"님");
                        break;
                    case "reset_word":
                        PreferenceManager.setInt(mContext, "word_target_setting", Integer.parseInt(result));
                        break;
                    case "reset_day":
//                        PreferenceManager.setInt(mContext, "day1_target_setting", Integer.parseInt(result1));
//                        PreferenceManager.setInt(mContext, "day2_target_setting", Integer.parseInt(result2));
//                        PreferenceManager.setInt(mContext, "day3_target_setting", Integer.parseInt(result3));
//                        PreferenceManager.setInt(mContext, "day4_target_setting", Integer.parseInt(result4));
//                        PreferenceManager.setInt(mContext, "day5_target_setting", Integer.parseInt(result5));
                        break;
                }
            }
        }
    }
}
