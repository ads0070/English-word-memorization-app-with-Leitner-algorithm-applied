package com.example.memvoca.database.vocabulary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VocabularyDao {
    @Query("SELECT * FROM Vocabulary")
    LiveData<List<Vocabulary>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Vocabulary vocabulary);

    @Delete
    void delete(Vocabulary vocabulary);
}
