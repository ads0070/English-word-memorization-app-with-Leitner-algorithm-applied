package com.example.memvoca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class EndPopupActivity extends Activity {

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
    }

    public void onTouchConfirm(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }
}
