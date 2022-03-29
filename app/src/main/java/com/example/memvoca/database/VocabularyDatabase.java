package com.example.memvoca.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Vocabulary.class, ZeroBox.class, FirstBox.class, SecondBox.class, ThirdBox.class, FourthBox.class, FifthBox.class ,FinishBox.class}, version = 1)
public abstract class VocabularyDatabase extends RoomDatabase {

    public abstract VocabularyDao getVocabularyDao();
    public abstract ZeroBoxDao getZeroBoxDao();
    public abstract FirstBoxDao getFirstBoxDao();
    public abstract SecondBoxDao getSecondBoxDao();
    public abstract ThirdBoxDao getThirdBoxDao();
    public abstract FourthBoxDao getFourthBoxDao();
    public abstract FifthBoxDao getFifthBoxDao();
    public abstract FinishBoxDao getFinishBoxDao();
    private static volatile VocabularyDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static VocabularyDatabase getDatabase(Context context) {  // Singleton 패턴 구현
        if (INSTANCE == null) {
            synchronized (VocabularyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VocabularyDatabase.class, "Vocabulary.db")
                            .createFromAsset("database/Vocabulary.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
