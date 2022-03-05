package com.example.memvoca;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Vocabulary.class}, version = 1)
public abstract class VocabularyDatabase extends RoomDatabase {

    public abstract VocabularyDao getMyDao();   // DAO getter
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
