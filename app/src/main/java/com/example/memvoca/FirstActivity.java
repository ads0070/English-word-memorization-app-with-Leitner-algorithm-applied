package com.example.memvoca;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.DateFormat;


public class FirstActivity extends AppCompatActivity {

    private Context mContext;
    private long lastTimeBackPressed = 0;

    private EditText mUserName;
    private Button mConfirmBtn;
    private EditText mWordTarget;
    private Spinner mSpinner;
    private TextView tv_cycle;
    private TextView tv_time;
    private int[] Cycle;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);
        mContext = this;

        mUserName=(EditText)findViewById(R.id.user_name) ;
        mConfirmBtn=(Button)findViewById(R.id.btn_confirm);
        mWordTarget=(EditText)findViewById(R.id.target_word_count);
        mSpinner = (Spinner) findViewById(R.id.memory_cycle);
        tv_cycle = (TextView) findViewById(R.id.tv_memory_cycle);
        tv_time = (TextView) findViewById(R.id.test_time);
        mConfirmBtn.setEnabled(false);

        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, TestAlarmPopupActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        tv_cycle.setText("");
                        mConfirmBtn.setEnabled(false);
                        mConfirmBtn.setBackgroundResource(R.drawable.btn_disabled_confirm);
                        mConfirmBtn.setTextColor(Color.parseColor("#B6BACB"));
                        break;
                    case 1:
                        tv_cycle.setText("\t1일\t\t3일\t\t5일\t\t13일\t\t28일\t");
                        Cycle = new int[]{5, 13, 28};
                        mConfirmBtn.setEnabled(true);
                        mConfirmBtn.setBackgroundResource(R.drawable.btn_activated_confirm);
                        mConfirmBtn.setTextColor(Color.WHITE);
                        break;
                    case 2:
                        tv_cycle.setText("\t1일\t\t3일\t\t7일\t\t15일\t\t30일\t");
                        Cycle = new int[]{7, 15, 30};
                        mConfirmBtn.setEnabled(true);
                        mConfirmBtn.setBackgroundResource(R.drawable.btn_activated_confirm);
                        mConfirmBtn.setTextColor(Color.WHITE);
                        break;
                    case 3:
                        tv_cycle.setText("\t1일\t\t3일\t\t10일\t\t17일\t\t35일\t");
                        Cycle = new int[]{10, 17, 35};
                        mConfirmBtn.setEnabled(true);
                        mConfirmBtn.setBackgroundResource(R.drawable.btn_activated_confirm);
                        mConfirmBtn.setTextColor(Color.WHITE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mConfirmBtn.setEnabled(false);
                mConfirmBtn.setBackgroundResource(R.drawable.btn_disabled_confirm);
                mConfirmBtn.setTextColor(Color.parseColor("#B6BACB"));
            }
        });

        mWordTarget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count <= 0) {
                    mConfirmBtn.setEnabled(false);
                    mConfirmBtn.setBackgroundResource(R.drawable.btn_disabled_confirm);
                    mConfirmBtn.setTextColor(Color.parseColor("#B6BACB"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mWord = mWordTarget.getText().toString();
                String mName = mUserName.getText().toString();
                String mCycle = tv_cycle.getText().toString();
                String mTestTime = tv_time.getText().toString();
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());

                //날짜 형식 지정
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String firstDay = df.format(cal.getTime());


                if (mName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "닉네임을 입력해 주세요.", Toast.LENGTH_LONG).show();
                    mUserName.requestFocus();
                } else {
                    if (mWord.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "목표치를 입력해 주세요.", Toast.LENGTH_LONG).show();
                        mWordTarget.requestFocus();
                        mWordTarget.selectAll();
                    } else {
                        if (Integer.parseInt(mWord) < 20 || 100 < Integer.parseInt(mWord)) {
                            Toast.makeText(getApplicationContext(), "20 ~ 100개 사이로 정해주세요.", Toast.LENGTH_LONG).show();
                            mWordTarget.requestFocus();
                            mWordTarget.selectAll();
                        } else {
                            if (mTestTime.equals("")) {
                                Toast.makeText(getApplicationContext(), "테스트 알림 시간을 설정해 주세요.", Toast.LENGTH_LONG).show();
                            } else {
                                if (mCycle.equals("")) {
                                    Toast.makeText(getApplicationContext(), "주기를 선택해 주세요.", Toast.LENGTH_LONG).show();
                                }else{
                                    PreferenceManager.setString(mContext, "user_name", mName);
                                    PreferenceManager.setInt(mContext, "word_target_setting", Integer.parseInt(mWord));
                                    PreferenceManager.setInt(mContext, "memory_cycle_1", Cycle[0]);
                                    PreferenceManager.setInt(mContext, "memory_cycle_2", Cycle[1]);
                                    PreferenceManager.setInt(mContext, "memory_cycle_3", Cycle[2]);
                                    PreferenceManager.setLong(mContext, "nextNotifyTime", (long)calendar.getTimeInMillis());
                                    PreferenceManager.setString(mContext, "first_day", firstDay);

                                    diaryNotification(calendar);

                                    Intent intent = new Intent(FirstActivity.this, FunctionActivity.class);
                                    intent.putExtra("title","테스트");
                                    intent.putExtra("sub_title","First Test");
                                    intent.putExtra("type","box_test");
                                    startActivity(intent);
                                }
                            }

                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() > lastTimeBackPressed + 1500) {
            lastTimeBackPressed = System.currentTimeMillis();
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        if(System.currentTimeMillis() <= lastTimeBackPressed + 1500) {
            finishAffinity(); //루트 액티비티 종료
            System.runFinalization(); //구동중인 쓰레드가 다 종료되면 종료
            System.exit(0); //현재 액티비티 종료
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                calendar = new GregorianCalendar();
                long millis = data.getExtras().getLong("calendar");
                calendar.setTimeInMillis(millis);

                Date date = calendar.getTime();
                String date_text = new SimpleDateFormat("a hh시 mm분 ", Locale.getDefault()).format(date);
                tv_time.setText(date_text);
            }
        }
    }

    void diaryNotification(Calendar calendar)
    {
        boolean dailyNotify = true; // 무조건 알람을 사용

        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, TestAlarmBootReceiver.class);
        Intent alarmIntent = new Intent(this, TestAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // 사용자가 매일 알람을 허용했다면
        if (alarmManager != null) {

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);

            //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        // 부팅 후 실행되는 리시버 사용가능하게 설정
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
