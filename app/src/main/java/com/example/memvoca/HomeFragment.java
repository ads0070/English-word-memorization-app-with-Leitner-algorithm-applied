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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private static final long ONE_DAY = 24*60*60*1000;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout box_layout = (LinearLayout)v.findViewById(R.id.box_layout);
        LinearLayout test_layout = (LinearLayout)v.findViewById(R.id.test_layout);
        LinearLayout setting_layout = (LinearLayout)v.findViewById(R.id.setting_layout);
        TextView progress = (TextView) v.findViewById(R.id.nick_name);

        String name = PreferenceManager.getString(getContext(), "user_name");
        String progress_text = "현재 " + name + "님의 진척도는?";
        progress.setText(progress_text);

        box_layout.setOnClickListener(this);
        test_layout.setOnClickListener(this);
        setting_layout.setOnClickListener(this);

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudyAlarmActivity.class);
                startActivity(intent);
            }
        });

        // 어플 최초 시작날짜 불러오기
        String firstDay = PreferenceManager.getFirstDay(mContext, "first_day");
        // 오늘 날짜 지정
        Calendar toDay = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        toDay.setTime(new Date());
        // 어플 시작날짜(String) 설정 되어있으면 Calendar(yyyy-MM-dd) 전환
        if (!firstDay.equals("None")){
            Calendar targetDay = Calendar.getInstance();
            try {
                targetDay.setTime(df.parse(firstDay));
            }catch (ParseException e){
                e.printStackTrace();
            }
            // 밀리초로 변환 -> 차이 계산 -> 정수로 변환
            long dday = toDay.getTimeInMillis() - targetDay.getTimeInMillis();
            long Dday = (dday/ONE_DAY)+1;

            TextView tv_Dday = v.findViewById(R.id.tv_Dday);
            tv_Dday.setText("Day "+Dday);
            // D-day 계산 후 PM에 저장 및 최신화
            PreferenceManager.setInt(mContext, "D-day", Long.valueOf(Dday).intValue());
        }

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
                intent.putExtra("title","테스트 목록");
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