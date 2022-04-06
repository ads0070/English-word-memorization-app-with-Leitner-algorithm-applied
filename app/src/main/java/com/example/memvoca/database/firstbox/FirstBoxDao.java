package com.example.memvoca.database.firstbox;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FirstBoxDao {
    @Query("SELECT * FROM FirstBox")
    LiveData<List<FirstBox>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FirstBox firstBox);

    @Delete
    void delete(FirstBox firstBox);

    @Query("DELETE FROM FirstBox")
    void deleteAll();
}