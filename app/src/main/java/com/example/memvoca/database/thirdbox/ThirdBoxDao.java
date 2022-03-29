package com.example.memvoca.database.thirdbox;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ThirdBoxDao {
    @Query("SELECT * FROM ThirdBox")
    LiveData<List<ThirdBox>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ThirdBox thirdBox);

    @Delete
    void delete(ThirdBox thirdBox);
}
