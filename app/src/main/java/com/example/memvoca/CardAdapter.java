package com.example.memvoca;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class CardAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<String> wordList;
    private ArrayList<String> pronunciationList;
    private GestureDetector gestureDetector = null;

    public CardAdapter(Context context, ArrayList<String> wordList, ArrayList<String> pronunciationList)
    {
        this.mContext = context;
        this.wordList = wordList;
        this.pronunciationList = pronunciationList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_card_front, null);

        gestureDetector = new GestureDetector( mContext, new SingleTapGestureListener() );
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        TextView tv_card = view.findViewById(R.id.word);
        TextView tv_pronunciation = view.findViewById(R.id.pronunciation);
        
        tv_card.setText(wordList.get(position));
        tv_pronunciation.setText(pronunciationList.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View) object);
    }
}
