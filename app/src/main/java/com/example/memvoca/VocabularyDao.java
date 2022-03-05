package com.example.memvoca;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VocabularyDao {
    @Query("SELECT * FROM Vocabulary")
    LiveData<List<Vocabulary>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Vocabulary vocabulary);
}
