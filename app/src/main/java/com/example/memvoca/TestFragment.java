package com.example.memvoca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment implements View.OnClickListener {
    private View view;
    private boolean isFront = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test, container, false);

        getFragmentManager().beginTransaction().add(R.id.test_card_frame, new CardFrontFragment()).commit();

        FrameLayout frameLayout = view.findViewById(R.id.test_card_frame);
        frameLayout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test_card_frame:
                if(isFront) {
                    getFragmentManager().beginTransaction().add(R.id.test_card_frame, new CardBackFragment()).commit();
                    isFront = false;
                } else {
                    getFragmentManager().beginTransaction().add(R.id.test_card_frame, new CardFrontFragment()).commit();
                    isFront = true;
                }
                break;
            case R.id.reset_target:
                break;
        }
    }
}
