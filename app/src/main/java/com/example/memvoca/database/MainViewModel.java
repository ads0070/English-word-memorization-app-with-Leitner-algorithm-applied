package com.example.memvoca.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.memvoca.database.fifthbox.FifthBox;
import com.example.memvoca.database.fifthbox.FifthBoxDao;
import com.example.memvoca.database.finishbox.FinishBox;
import com.example.memvoca.database.finishbox.FinishBoxDao;
import com.example.memvoca.database.firstbox.FirstBox;
import com.example.memvoca.database.firstbox.FirstBoxDao;
import com.example.memvoca.database.fourthbox.FourthBox;
import com.example.memvoca.database.fourthbox.FourthBoxDao;
import com.example.memvoca.database.secondbox.SecondBox;
import com.example.memvoca.database.secondbox.SecondBoxDao;
import com.example.memvoca.database.thirdbox.ThirdBox;
import com.example.memvoca.database.thirdbox.ThirdBoxDao;
import com.example.memvoca.database.vocabulary.Vocabulary;
import com.example.memvoca.database.vocabulary.VocabularyDao;
import com.example.memvoca.database.zerobox.ZeroBox;
import com.example.memvoca.database.zerobox.ZeroBoxDao;

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
     * deleteAllZeroBox() 전체 삭제
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

    public void deleteAllZeroBox(){
        new DeleteAllZeroBAsyncTask(vocabularyDatabase.getZeroBoxDao()).execute();
    }
    private static class DeleteAllZeroBAsyncTask extends AsyncTask<ZeroBox,Void, Void> {
        private ZeroBoxDao zeroBoxDao;

        public DeleteAllZeroBAsyncTask(ZeroBoxDao zeroBoxDao) {
            this.zeroBoxDao = zeroBoxDao;
        }

        @Override
        protected Void doInBackground(ZeroBox... zeroBoxes) {
            zeroBoxDao.deleteAll();
            return null;
        }
    }

    /***
     * FirstBox
     * getAllFirstBox() 전체 검색
     * insertIntoFirstBox() 삽입
     * deleteAllFirstBox() 전체 삭제
     */
    public LiveData<List<FirstBox>> getAllFirstBox(){
        return vocabularyDatabase.getFirstBoxDao().getAll();
    }

    public void insertIntoFirstBox(FirstBox firstBox){
        new InsertFirstBAsyncTask(vocabularyDatabase.getFirstBoxDao()).execute(firstBox);
    }
    private static class InsertFirstBAsyncTask extends AsyncTask<FirstBox,Void, Void> {
        private FirstBoxDao firstBoxDao;

        public InsertFirstBAsyncTask(FirstBoxDao firstBoxDao) {
            this.firstBoxDao = firstBoxDao;
        }

        @Override
        protected Void doInBackground(FirstBox... firstBoxes) {
            firstBoxDao.insert(firstBoxes[0]);
            return null;
        }
    }

    public void deleteAllFirstBox(){
        new DeleteAllFirstBAsyncTask(vocabularyDatabase.getFirstBoxDao()).execute();
    }
    private static class DeleteAllFirstBAsyncTask extends AsyncTask<FirstBox,Void, Void> {
        private FirstBoxDao firstBoxDao;

        public DeleteAllFirstBAsyncTask(FirstBoxDao firstBoxDao) {
            this.firstBoxDao = firstBoxDao;
        }

        @Override
        protected Void doInBackground(FirstBox... firstBoxes) {
            firstBoxDao.deleteAll();
            return null;
        }
    }

    /***
     * SecondBox
     * getAllSecondBox() 전체 검색
     * insertIntoSecondBox() 삽입
     * deleteAllSecondBox() 전체 삭제
     */
    public LiveData<List<SecondBox>> getAllSecondBox(){
        return vocabularyDatabase.getSecondBoxDao().getAll();
    }

    public void insertIntoSecondBox(SecondBox secondBox){
        new InsertSecondBAsyncTask(vocabularyDatabase.getSecondBoxDao()).execute(secondBox);
    }
    private static class InsertSecondBAsyncTask extends AsyncTask<SecondBox,Void, Void> {
        private SecondBoxDao secondBoxDao;

        public InsertSecondBAsyncTask(SecondBoxDao secondBoxDao) {
            this.secondBoxDao = secondBoxDao;
        }

        @Override
        protected Void doInBackground(SecondBox... secondBoxes) {
            secondBoxDao.insert(secondBoxes[0]);
            return null;
        }
    }

    public void deleteAllSecondBox(){
        new DeleteAllSecondBAsyncTask(vocabularyDatabase.getSecondBoxDao()).execute();
    }
    private static class DeleteAllSecondBAsyncTask extends AsyncTask<SecondBox,Void, Void> {
        private SecondBoxDao secondBoxDao;

        public DeleteAllSecondBAsyncTask(SecondBoxDao secondBoxDao) {
            this.secondBoxDao = secondBoxDao;
        }

        @Override
        protected Void doInBackground(SecondBox... secondBoxes) {
            secondBoxDao.deleteAll();
            return null;
        }
    }

    /***
     * ThirdBox
     * getAllThirdBox() 전체 검색
     * insertIntoThirdBox() 삽입
     * deleteAllThirdBox() 전체 삭제
     */
    public LiveData<List<ThirdBox>> getAllThirdBox(){
        return vocabularyDatabase.getThirdBoxDao().getAll();
    }

    public void insertIntoThirdBox(ThirdBox thirdBox){
        new InsertThirdBAsyncTask(vocabularyDatabase.getThirdBoxDao()).execute(thirdBox);
    }
    private static class InsertThirdBAsyncTask extends AsyncTask<ThirdBox,Void, Void> {
        private ThirdBoxDao thirdBoxDao;

        public InsertThirdBAsyncTask(ThirdBoxDao thirdBoxDao) {
            this.thirdBoxDao = thirdBoxDao;
        }

        @Override
        protected Void doInBackground(ThirdBox... thirdBoxes) {
            thirdBoxDao.insert(thirdBoxes[0]);
            return null;
        }
    }

    public void deleteAllThirdBox(){
        new DeleteAllThirdBAsyncTask(vocabularyDatabase.getThirdBoxDao()).execute();
    }
    private static class DeleteAllThirdBAsyncTask extends AsyncTask<ThirdBox,Void, Void> {
        private ThirdBoxDao thirdBoxDao;

        public DeleteAllThirdBAsyncTask(ThirdBoxDao thirdBoxDao) {
            this.thirdBoxDao = thirdBoxDao;
        }

        @Override
        protected Void doInBackground(ThirdBox... thirdBoxes) {
            thirdBoxDao.deleteAll();
            return null;
        }
    }

    /***
     * FourthBox
     * getAllFourthBox() 전체 검색
     * insertIntoFourthBox() 삽입
     * deleteAllFourthBox() 전체 삭제
     */
    public LiveData<List<FourthBox>> getAllFourthBox(){
        return vocabularyDatabase.getFourthBoxDao().getAll();
    }

    public void insertIntoFourthBox(FourthBox fourthBox){
        new InsertFourthBAsyncTask(vocabularyDatabase.getFourthBoxDao()).execute(fourthBox);
    }
    private static class InsertFourthBAsyncTask extends AsyncTask<FourthBox,Void, Void> {
        private FourthBoxDao fourthBoxDao;

        public InsertFourthBAsyncTask(FourthBoxDao fourthBoxDao) {
            this.fourthBoxDao = fourthBoxDao;
        }

        @Override
        protected Void doInBackground(FourthBox... fourthBoxes) {
            fourthBoxDao.insert(fourthBoxes[0]);
            return null;
        }
    }

    public void deleteAllFourthBox(){
        new DeleteAllFourthBAsyncTask(vocabularyDatabase.getFourthBoxDao()).execute();
    }
    private static class DeleteAllFourthBAsyncTask extends AsyncTask<FourthBox,Void, Void> {
        private FourthBoxDao fourthBoxDao;

        public DeleteAllFourthBAsyncTask(FourthBoxDao fourthBoxDao) {
            this.fourthBoxDao = fourthBoxDao;
        }

        @Override
        protected Void doInBackground(FourthBox... fourthBoxes) {
            fourthBoxDao.deleteAll();
            return null;
        }
    }

    /***
     * FifthBox
     * getAllFifthBox() 전체 검색
     * insertIntoFifthBox() 삽입
     * deleteAllFifthBox() 전체 삭제
     */
    public LiveData<List<FifthBox>> getAllFifthBox(){
        return vocabularyDatabase.getFifthBoxDao().getAll();
    }

    public void insertIntoFifthBox(FifthBox fifthBox){
        new InsertFifthBAsyncTask(vocabularyDatabase.getFifthBoxDao()).execute(fifthBox);
    }
    private static class InsertFifthBAsyncTask extends AsyncTask<FifthBox,Void, Void> {
        private FifthBoxDao fifthBoxDao;

        public InsertFifthBAsyncTask(FifthBoxDao fifthBoxDao) {
            this.fifthBoxDao = fifthBoxDao;
        }

        @Override
        protected Void doInBackground(FifthBox... fifthBoxes) {
            fifthBoxDao.insert(fifthBoxes[0]);
            return null;
        }
    }

    public void deleteAllFifthBox(){
        new DeleteAllFifthBAsyncTask(vocabularyDatabase.getFifthBoxDao()).execute();
    }
    private static class DeleteAllFifthBAsyncTask extends AsyncTask<FifthBox,Void, Void> {
        private FifthBoxDao fifthBoxDao;

        public DeleteAllFifthBAsyncTask(FifthBoxDao fifthBoxDao) {
            this.fifthBoxDao = fifthBoxDao;
        }

        @Override
        protected Void doInBackground(FifthBox... fifthBoxes) {
            fifthBoxDao.deleteAll();
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
