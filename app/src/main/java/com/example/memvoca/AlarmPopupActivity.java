package com.example.memvoca;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AlarmPopupActivity extends Activity {
    private Context mContext;
    private TimePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_alarm);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int w = (int)(dm.widthPixels * 0.8);
        int h = (int)(dm.heightPixels * 0.4);

        getWindow().getAttributes().width = w;
        getWindow().getAttributes().height = h;

        picker = (TimePicker) findViewById(R.id.timePicker);
        picker.setIs24HourView(false);

        long millis = PreferenceManager.getLong(mContext, "nextNotifyTime");

        Calendar nextNotifyTime = new GregorianCalendar();
        nextNotifyTime.setTimeInMillis(millis);

        // 이전 설정값으로 TimePicker 초기화
        Date currentTime = nextNotifyTime.getTime();
        SimpleDateFormat HourFormat = new SimpleDateFormat("kk", Locale.getDefault());
        SimpleDateFormat MinuteFormat = new SimpleDateFormat("mm", Locale.getDefault());

        int pre_hour = Integer.parseInt(HourFormat.format(currentTime));
        int pre_minute = Integer.parseInt(MinuteFormat.format(currentTime));

        picker.setHour(pre_hour);
        picker.setMinute(pre_minute);
    }

    public void onTouchConfirm(View v) {
        int hour_24, minute;
        hour_24 = picker.getHour();
        minute = picker.getMinute();

        // 현재 지정된 시간으로 알람 시간 설정
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour_24);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        calendar.add(Calendar.DATE, 1); // 다음 날 같은 시간에 시험 보도록 설정

        Intent intent = new Intent();
        intent.putExtra("calendar", (long)calendar.getTimeInMillis());

        setResult(RESULT_OK, intent);
        finish();
    }

    public void onTouchCancel(View v) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }
}
