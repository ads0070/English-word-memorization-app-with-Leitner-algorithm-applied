package com.example.memvoca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class FunctionActivity extends AppCompatActivity {

    private TextView titleTv;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        Intent intent = getIntent();

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(FunctionActivity.this, MainActivity.class);
                startActivity(mainIntent);
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
                TestFragment testFragment= new TestFragment();
                transaction.replace(R.id.func_frame, testFragment);
                break;
            case "setting":
                SettingFragment settingFragment= new SettingFragment();
                transaction.replace(R.id.func_frame, settingFragment);
                break;
        }
        transaction.commit();
    }
}
