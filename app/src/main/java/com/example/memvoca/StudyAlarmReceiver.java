package com.example.memvoca;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class StudyAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingI = PendingIntent.getActivity(context, 1,
                notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "study");

        //OREO API 26 이상에서는 채널 필요
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);

        String channelName ="공부 알림 채널";
        String description = "반복 단어 학습 채널";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel channel = new NotificationChannel("study", channelName, importance);
        channel.setDescription(description);

        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }

        String name = PreferenceManager.getString(context, "user_name");

        builder.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("{Time to watch some cool stuff!}")
                .setContentTitle(name + "님 학습 시간입니다!" + PreferenceManager.getNum(context,"study_alarm_count"))
                .setContentText("오늘 외울 단어를 학습해 주세요.")
                .setContentInfo("INFO")
                .setContentIntent(pendingI);

        if (notificationManager != null) {

            int alarm_count = PreferenceManager.getNum(context, "study_alarm_count");

            if(alarm_count >= 2) {
                AlarmManager alarmManager = (AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);

                if (alarmManager != null) {
                    alarmManager.cancel(pendingI);
                }

                PackageManager pm = context.getPackageManager();
                ComponentName receiver = new ComponentName(context, StudyAlarmBootReceiver.class);
                pm.setComponentEnabledSetting(receiver,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
                //PreferenceManager.removeKey(context, "study_alarm_count");
                PreferenceManager.removeKey(context, "nextStudyTime");
                notificationManager.cancel(2222);

            } else {
                notificationManager.notify(2222, builder.build());

                Calendar nextNotifyTime = Calendar.getInstance();
                nextNotifyTime.add(Calendar.MINUTE, 1);

                PreferenceManager.setLong(context, "nextStudyTime", nextNotifyTime.getTimeInMillis());

                PreferenceManager.setInt(context, "study_alarm_count", alarm_count+1);
            }
        }
    }
}