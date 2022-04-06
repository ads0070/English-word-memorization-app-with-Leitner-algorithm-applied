package com.example.memvoca.database.fifthbox;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FifthBoxDao {
    @Query("SELECT * FROM FifthBox")
    LiveData<List<FifthBox>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FifthBox fifthBox);

    @Delete
    void delete(FifthBox fifthBox);

    @Query("DELETE FROM FifthBox")
    void deleteAll();
}