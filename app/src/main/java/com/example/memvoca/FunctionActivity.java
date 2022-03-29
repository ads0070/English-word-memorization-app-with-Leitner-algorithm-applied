package com.example.memvoca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.memvoca.word.WordListFragment;

public class FunctionActivity extends AppCompatActivity {

    private TextView titleTv;
    private ImageButton backBtn;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        mContext = this;

        Intent intent = getIntent();

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        titleTv = findViewById(R.id.title);
        titleTv.setText(intent.getExtras().getString("title"));
        String type = intent.getExtras().getString("type");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch(type) {
            case "box":
                BoxListFragment boxListFragment= new BoxListFragment();
                transaction.replace(R.id.func_frame, boxListFragment);
                break;
            case "test":
                TestListFragment testListFragment= new TestListFragment();
                transaction.replace(R.id.func_frame, testListFragment);
//                backBtn.setVisibility(View.INVISIBLE);
                break;
            case "setting":
                SettingFragment settingFragment= new SettingFragment();
                transaction.replace(R.id.func_frame, settingFragment);
                break;
            case "box_list":
                Bundle bundle = new Bundle();
                bundle.putString("box_type", intent.getExtras().getString("title"));
                WordListFragment wordListFragment = new WordListFragment();
                wordListFragment.setArguments(bundle);
                transaction.replace(R.id.func_frame, wordListFragment);
                break;
            case "box_test":
                backBtn.setVisibility(View.INVISIBLE);
                Bundle bundle1 = new Bundle();
                bundle1.putString("box_num", intent.getExtras().getString("sub_title"));
                TestFragment testFragment = new TestFragment();
                testFragment.setArguments(bundle1);
                transaction.replace(R.id.func_frame, testFragment);
                break;
        }
        transaction.commit();
    }
}
