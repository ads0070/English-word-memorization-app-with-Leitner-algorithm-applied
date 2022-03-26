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
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private static final long ONE_DAY = 24*60*60*1000;

    private ArrayList<String> wise_saying_eng = new ArrayList<>(Arrays.asList(
            "The road to success and the road to failure are almost exactly the same.",
            "Imagination is more important than knowledge.",
            "A goal without a plan is just a wish.",
            "Do not turn back when you are just at the goal.",
            "A wise man will make more opportunities than he finds.",
            "Never leave that 'till tomorrow which you can do today.",
            "Everything comes to him who hustles while he waits.",
            "Everything in your world is created by what you think.",
            "Great minds have purposes, others have wishes."));
    private ArrayList<String> wise_saying_kr = new ArrayList<>(Arrays.asList(
            "성공으로 가는 길과 실패로 가는 길은 거의 같다.",
            "지식보다 중요한 것은 상상력이다.",
            "계획 없는 목표는 한낱 꿈에 불과하다.",
            "목표에 도달했을 때 돌아서지 마라.",
            "현명한 자라면 찾아낸 기회보다 더 많은 기회를 만들 것이다.",
            "오늘 할 수 있는 일을 내일로 미루지 마라.",
            "성공은 열심히 노력하며 기다리는 사람에게 찾아온다.",
            "세상 모든 일은 여러분이 무엇을 생각하느냐에 따라 일어납니다.",
            "위대한 이들은 목적을 갖고, 그 외의 사람들은 소원을 갖는다."));
    private ArrayList<String> wise_saying_name = new ArrayList<>(Arrays.asList(
            "Colin R.Davis",
            "Albert Einstein",
            "Antoine de Saint-Exupery",
            "Publilius Syrus",
            "Sir Francis Bacon",
            "Benjamin Franklin",
            "Thomas A. Edison",
            "Oprah Winfrey",
            "Washington Irving"));

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
        TextView wise_saying_eng_tv = (TextView) v.findViewById(R.id.wise_saying_eng);
        TextView wise_saying_kr_tv = (TextView) v.findViewById(R.id.wise_saying_kr);
        TextView wise_saying_name_tv = (TextView) v.findViewById(R.id.great_name);

        String name = PreferenceManager.getString(getContext(), "user_name");
        String progress_text = "현재 " + name + "님의 진척도는?";
        progress.setText(progress_text);

        double dValue = Math.random();
        int num = (int)(dValue*10);

        wise_saying_eng_tv.setText(wise_saying_eng.get(num));
        wise_saying_kr_tv.setText(wise_saying_kr.get(num));
        wise_saying_name_tv.setText("- " + wise_saying_name.get(num) + " -");

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


            //테스트 코드 추후 지우고 push 할 것
//            System.out.println("cal : "+df.format(targetDay.getTime()));
//            System.out.println("cal2 : "+df.format(toDay.getTime()));
//            System.out.println(Dday);
//            System.out.println(PreferenceManager.getInt(mContext, "D-day"));
//            System.out.println("test"+toDay.get(Calendar.DATE));
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