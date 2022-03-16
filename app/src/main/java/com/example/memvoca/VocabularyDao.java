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

    @Query("SELECT * FROM Vocabulary WHERE id = :num")
    LiveData<List<Vocabulary>> whereId(int num);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Vocabulary vocabulary);
}
