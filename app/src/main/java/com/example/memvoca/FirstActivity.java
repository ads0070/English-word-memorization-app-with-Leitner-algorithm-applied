package com.example.memvoca;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    private Context mContext;

    private EditText mUserName;
    private Button mConfirmBtn;
    private EditText mWordTarget;
    private EditText mDayTarget1;
    private EditText mDayTarget2;
    private EditText mDayTarget3;
    private EditText mDayTarget4;
    private EditText mDayTarget5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);
        mContext = this;

        mUserName=(EditText)findViewById(R.id.user_name) ;
        mConfirmBtn=(Button)findViewById(R.id.btn_confirm);
        mWordTarget=(EditText)findViewById(R.id.target_word_count);
        mDayTarget1=(EditText)findViewById(R.id.target_day1);
        mDayTarget2=(EditText)findViewById(R.id.target_day2);
        mDayTarget3=(EditText)findViewById(R.id.target_day3);
        mDayTarget4=(EditText)findViewById(R.id.target_day4);
        mDayTarget5=(EditText)findViewById(R.id.target_day5);

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

                if (mName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요.", Toast.LENGTH_LONG).show();
                    mUserName.requestFocus();
                } else {
                    if (mWord < 20 || 100 < mWord) {
                        Toast.makeText(getApplicationContext(), "20 ~ 100개 사이로 정해주세요.", Toast.LENGTH_LONG).show();
                        mWordTarget.requestFocus();
                        mWordTarget.selectAll();
                    } else {
                        PreferenceManager.setString(mContext, "user_name", mName);
                        PreferenceManager.setInt(mContext, "word_target_setting", mWord);
                        PreferenceManager.setInt(mContext, "day1_target_setting", Integer.parseInt(mDayTarget1.getText().toString()));
                        PreferenceManager.setInt(mContext, "day2_target_setting", Integer.parseInt(mDayTarget2.getText().toString()));
                        PreferenceManager.setInt(mContext, "day3_target_setting", Integer.parseInt(mDayTarget3.getText().toString()));
                        PreferenceManager.setInt(mContext, "day4_target_setting", Integer.parseInt(mDayTarget4.getText().toString()));
                        PreferenceManager.setInt(mContext, "day5_target_setting", Integer.parseInt(mDayTarget5.getText().toString()));

                        Intent intent = new Intent(FirstActivity.this, FunctionActivity.class);
                        intent.putExtra("title","테스트");
                        intent.putExtra("type","test");
                        startActivity(intent);
                    }
                }
            }
        });

    }
}
