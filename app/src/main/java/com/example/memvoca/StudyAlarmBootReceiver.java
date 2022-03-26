package com.example.memvoca;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class StudyAlarmBootReceiver extends BroadcastReceiver {
    private static final int INTERVAL_TWENTY_MINUTES = 1000 * 60 * 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            Intent alarmIntent = new Intent(context, StudyAlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, alarmIntent, PendingIntent.FLAG_IMMUTABLE);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            long millis = PreferenceManager.getLong(context, "nextStudyTime");

            Calendar current_calendar = Calendar.getInstance();
            Calendar nextStudyTime = new GregorianCalendar();
            nextStudyTime.setTimeInMillis(millis);

            // 부팅 후 공부 알림 시간이 지났다면
            // 현재 시간에 맞추어 알림 호출
            if (current_calendar.after(nextStudyTime)) {
                nextStudyTime.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
            }

            if (manager != null) {
                manager.setRepeating(AlarmManager.RTC_WAKEUP, nextStudyTime.getTimeInMillis(),
                        INTERVAL_TWENTY_MINUTES, pendingIntent);
            }
        }
    }
}