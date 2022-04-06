package com.example.memvoca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class EndPopupActivity extends Activity {
    private String title;
    private String sub_title;
    private String type;
    private TextView endTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_end);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int w = (int)(dm.widthPixels * 0.7);
        int h = (int)(dm.heightPixels * 0.3);

        getWindow().getAttributes().width = w;
        getWindow().getAttributes().height = h;

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            TextView tv_Text = findViewById(R.id.reset_context);
            tv_Text.setText(intent.getExtras().getString("text"));
        }

        Intent get_it = getIntent();
        title = get_it.getStringExtra("title");
        sub_title = get_it.getStringExtra("sub_title");
        type = get_it.getStringExtra("type");

        endTv = (TextView) findViewById(R.id.end_test_tv);

        if (sub_title!=null) {
            endTv.setText("테스트가 종료되었습니다.\n목표 단어 개수를 채우기 위해\n추가 테스트를 진행합니다.");
        }
    }

    public void onTouchConfirm(View v) {

        Intent intent;
        if(sub_title!=null) {
            intent = new Intent(this, FunctionActivity.class);
            intent.putExtra("title",title);
            intent.putExtra("sub_title",sub_title);
            intent.putExtra("type",type);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }
}
