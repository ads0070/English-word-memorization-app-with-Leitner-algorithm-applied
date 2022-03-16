package com.example.memvoca;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class MainActivityTestDB extends AppCompatActivity implements ViewModelStoreOwner {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private ViewModelStore viewModelStore = new ViewModelStore();
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_database);

        mTodoEditText = findViewById(R.id.testEdit);
        mResultTextView = findViewById(R.id.testTv);

        if(viewModelFactory == null){
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }
        viewModel = new ViewModelProvider(this,viewModelFactory).get(MainViewModel.class);

        // UI 갱신
        viewModel.getAllVocabulary().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        // DB에 데이터 삽입 코드
        /*findViewById(R.id.testBtn).setOnClickListener(v -> {
            viewModel.insert(new Vocabulary(99999,mTodoEditText.getText().toString(),"test1","test2","test3","test4"));
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModelStore.clear();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore(){
        return viewModelStore;
    }
}
