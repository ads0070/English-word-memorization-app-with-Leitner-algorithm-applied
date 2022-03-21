package com.example.memvoca;

import android.content.Context;
import android.content.Intent;
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

import java.lang.reflect.Array;

public class FirstActivity extends AppCompatActivity {

    private Context mContext;
    private long lastTimeBackPressed = 0;

    private EditText mUserName;
    private Button mConfirmBtn;
    private EditText mWordTarget;
    private Spinner mSpinner;
    private TextView tv_cycle;
    private int[] Cycle;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        tv_cycle.setText("");
                        break;
                    case 1:
                        tv_cycle.setText("\t1일\t\t3일\t\t5일\t\t13일\t\t28일\t");
                        Cycle = new int[]{5, 13, 28};
                        break;
                    case 2:
                        tv_cycle.setText("\t1일\t\t3일\t\t7일\t\t15일\t\t30일\t");
                        Cycle = new int[]{7, 15, 30};
                        break;
                    case 3:
                        tv_cycle.setText("\t1일\t\t3일\t\t10일\t\t17일\t\t35일\t");
                        Cycle = new int[]{10, 17, 35};
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mConfirmBtn.setClickable(false);
                mConfirmBtn.setBackgroundResource(R.drawable.btn_disabled_confirm);
                mConfirmBtn.setTextColor(Color.parseColor("#B6BACB"));
            }
        });

        mWordTarget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count > 0) {
                    mConfirmBtn.setClickable(true);
                    mConfirmBtn.setBackgroundResource(R.drawable.btn_activated_confirm);
                    mConfirmBtn.setTextColor(Color.WHITE);
                } else {
                    mConfirmBtn.setClickable(false);
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
                int mWord = Integer.parseInt(mWordTarget.getText().toString());
                String mName = mUserName.getText().toString();
                String mCycle = tv_cycle.getText().toString();

                if (mName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요.", Toast.LENGTH_LONG).show();
                    mUserName.requestFocus();
                } else {
                    if (mWord < 20 || 100 < mWord) {
                        Toast.makeText(getApplicationContext(), "20 ~ 100개 사이로 정해주세요.", Toast.LENGTH_LONG).show();
                        mWordTarget.requestFocus();
                        mWordTarget.selectAll();
                    } else {
                        if (mCycle.equals("")) {
                            Toast.makeText(getApplicationContext(), "주기를 선택해 주세요.", Toast.LENGTH_LONG).show();
                            mWordTarget.requestFocus();
                        }else{
                            PreferenceManager.setString(mContext, "user_name", mName);
                            PreferenceManager.setInt(mContext, "word_target_setting", mWord);
                            PreferenceManager.setInt(mContext, "memory_cycle_1", Cycle[0]);
                            PreferenceManager.setInt(mContext, "memory_cycle_2", Cycle[1]);
                            PreferenceManager.setInt(mContext, "memory_cycle_3", Cycle[2]);

                        Intent intent = new Intent(FirstActivity.this, FunctionActivity.class);
                        intent.putExtra("title","테스트");
                        intent.putExtra("type","test");
                        startActivity(intent);
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
}
