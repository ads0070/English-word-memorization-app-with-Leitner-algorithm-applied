package com.example.memvoca;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StudyAlarmActivity extends AppCompatActivity {
    private Context mContext;
    private static final int INTERVAL_TWENTY_MINUTES = 1000 * 60 * 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.test_study_alarm);

        Button test_btn = (Button) findViewById(R.id.test_alarm);
        Button check_btn = (Button) findViewById(R.id.test_check);
        TextView tv = (TextView) findViewById(R.id.test_alarm_tv);

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.MINUTE, 1);

                Date date_now = new Date(System.currentTimeMillis());
                Date next_time = new Date((long)calendar.getTimeInMillis());
                tv.setText("현재 시간 : " + date_now +
                        "\n다음 알람 시간 : " + next_time);
                PreferenceManager.setInt(mContext, "study_alarm_count",0);
                PreferenceManager.setLong(mContext, "nextStudyTime", (long)calendar.getTimeInMillis());
                diaryNotification(calendar);
            }
        });

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long time = PreferenceManager.getLong(mContext, "nextStudyTime");
                Date date = new Date(time);
                tv.setText("다음 알람 시간 : " + date +
                        "\n울린 알람 횟수 : " + PreferenceManager.getNum(mContext, "study_alarm_count"));
            }
        });
    }

    void diaryNotification(Calendar calendar)
    {
        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, StudyAlarmBootReceiver.class);
        Intent alarmIntent = new Intent(this, StudyAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if (alarmManager != null) {
            // 20분마다 알람 반복
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    INTERVAL_TWENTY_MINUTES, pendingIntent);

            //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
