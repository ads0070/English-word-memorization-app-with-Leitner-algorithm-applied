package com.example.memvoca;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FinishBoxDao {
    @Query("SELECT * FROM FinishBox")
    LiveData<List<FinishBox>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FinishBox finishBox);

    @Delete
    void delete(FinishBox finishBox);
}
