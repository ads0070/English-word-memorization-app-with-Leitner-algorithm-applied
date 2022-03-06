package com.example.memvoca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetPopupActivity extends Activity {
    EditText contextEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_reset);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int w = (int)(dm.widthPixels * 0.8);
        int h = (int)(dm.heightPixels * 0.4);

        getWindow().getAttributes().width = w;
        getWindow().getAttributes().height = h;

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String title = "변경할 " + intent.getStringExtra("title") + " 입력해 주세요.";

        TextView titleTv = (TextView)findViewById(R.id.reset_title);
        titleTv.setText(title);

        contextEt = (EditText)findViewById(R.id.reset_context);

        switch (type){
            case "reset_name":
                contextEt.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                contextEt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
                break;
            case "reset_word":
                contextEt.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
                contextEt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3)});
                break;
            case "reset_day":
                contextEt.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
                contextEt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
                break;
        }
    }

    public void onTouchConfirm(View v) {
        Intent info = getIntent();
        String type = info.getStringExtra("type");

        Intent intent = new Intent();
        String new_data = contextEt.getText().toString();

        if(new_data.isEmpty()) {
            Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_LONG).show();
        } else {
            switch (type) {
                case "reset_word":
                    int num = Integer.parseInt(new_data);
                    if(num < 20 || 100 < num) {
                        Toast.makeText(getApplicationContext(), "20 ~ 100개 사이로 정해주세요.", Toast.LENGTH_LONG).show();
                        contextEt.requestFocus();
                        contextEt.selectAll();
                        return;
                    }
                    break;
                case "reset_day":
                    // 추가 예정
                    break;
            }
            intent.putExtra("data", new_data);
            intent.putExtra("type", type);


            setResult(RESULT_OK, intent);
            finish();
        }

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
