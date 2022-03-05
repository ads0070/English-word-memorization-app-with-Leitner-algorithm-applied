package com.example.memvoca;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FunctionActivity extends AppCompatActivity {

    private  BoxListFragment boxListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);


        FragmentManager fragmentManager = getSupportFragmentManager();

        boxListFragment = new BoxListFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.func_frame, boxListFragment).commit();
    }

}
