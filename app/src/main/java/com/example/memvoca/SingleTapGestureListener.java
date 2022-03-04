package com.example.memvoca;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SingleTapGestureListener extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if( velocityX < 0 ) {
                // 뷰페이저 오른쪽으로 이동
                System.out.println("test1");
            }else {
                System.out.println("test2");
                // 뷰페이저 왼쪽으로 이동

            }
        }catch ( Exception e ) {}
        return true;
    }

    /*@Override
    public boolean onSingleTapUp(MotionEvent event) {
        try {
            // 클릭 리스너 동작
            System.out.println("test3");
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            CardBackFragment cardBackFragment = new CardBackFragment();
            transaction.replace(R.id.card_frameLayout, cardBackFragment);
            transaction.commit();
        }catch ( Exception e ) {}
        return true;
    }*/
}
