package com.example.memvoca;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.memvoca.Vocabulary;
import com.example.memvoca.VocabularyDao;
import com.example.memvoca.VocabularyDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private VocabularyDatabase db;

    public MainViewModel(@NonNull Application application) {
        super(application);
        db = VocabularyDatabase.getDatabase(application);
    }

    public LiveData<List<Vocabulary>> getAll(){
        return db.getMyDao().getAll();
    }

    public void insert(Vocabulary todo){
        new InsertAsyncTask(db.getMyDao()).execute(todo);
    }

    private static class InsertAsyncTask extends AsyncTask<Vocabulary,Void, Void> {
        private VocabularyDao vocabularyDao;

        public InsertAsyncTask(VocabularyDao vocabularyDao) {
            this.vocabularyDao = vocabularyDao;
        }

        @Override
        protected Void doInBackground(Vocabulary... vocabularies) {
            vocabularyDao.insert(vocabularies[0]);
            return null;
        }
    }
}
