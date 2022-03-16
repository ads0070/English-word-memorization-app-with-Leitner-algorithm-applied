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

    private VocabularyDatabase vocabularyDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        vocabularyDatabase = VocabularyDatabase.getDatabase(application);
    }

    /***
     * Vocabulary
     * getAllVocabulary() 전체 검색
     * insertIntoVocabulary() 삽입
     */
    public LiveData<List<Vocabulary>> getAllVocabulary(){
        return vocabularyDatabase.getVocabularyDao().getAll();
    }

    public void insertIntoVocabulary(Vocabulary vocabulary){
        new InsertVAsyncTask(vocabularyDatabase.getVocabularyDao()).execute(vocabulary);
    }

    private static class InsertVAsyncTask extends AsyncTask<Vocabulary,Void, Void> {
        private VocabularyDao vocabularyDao;

        public InsertVAsyncTask(VocabularyDao vocabularyDao) {
            this.vocabularyDao = vocabularyDao;
        }

        @Override
        protected Void doInBackground(Vocabulary... vocabularies) {
            vocabularyDao.insert(vocabularies[0]);
            return null;
        }
    }

    /***
     * ZeroBox
     * getAllZeroBox() 전체 검색
     * insertIntoZeroBox() 삽입
     */
    public LiveData<List<ZeroBox>> getAllZeroBox(){
        return vocabularyDatabase.getZeroBoxDao().getAll();
    }

    public void insertIntoZeroBox(ZeroBox zeroBox){
        new InsertZBAsyncTask(vocabularyDatabase.getZeroBoxDao()).execute(zeroBox);
    }
    private static class InsertZBAsyncTask extends AsyncTask<ZeroBox,Void, Void> {
        private ZeroBoxDao zeroBoxDao;

        public InsertZBAsyncTask(ZeroBoxDao zeroBoxDao) {
            this.zeroBoxDao = zeroBoxDao;
        }

        @Override
        protected Void doInBackground(ZeroBox... zeroBoxes) {
            zeroBoxDao.insert(zeroBoxes[0]);
            return null;
        }
    }

    /***
     * FinishBox
     * getAllFinishBox() 전체 검색
     * insertIntoFinishBox() 삽입
     */
    public LiveData<List<FinishBox>> getAllFinishBox(){
        return vocabularyDatabase.getFinishBoxDao().getAll();
    }

    public void insertIntoFinishBox(FinishBox finishBox){
        new InsertFBAsyncTask(vocabularyDatabase.getFinishBoxDao()).execute(finishBox);
    }
    private static class InsertFBAsyncTask extends AsyncTask<FinishBox,Void, Void> {
        private FinishBoxDao finishBoxDao;

        public InsertFBAsyncTask(FinishBoxDao finishBoxDao) {
            this.finishBoxDao = finishBoxDao;
        }

        @Override
        protected Void doInBackground(FinishBox... finishBoxes) {
            finishBoxDao.insert(finishBoxes[0]);
            return null;
        }
    }
}
