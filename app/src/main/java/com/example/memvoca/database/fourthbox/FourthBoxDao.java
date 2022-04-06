package com.example.memvoca.database.fourthbox;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FourthBoxDao {
    @Query("SELECT * FROM FourthBox")
    LiveData<List<FourthBox>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FourthBox fourthBox);

    @Delete
    void delete(FourthBox fourthBox);

    @Query("DELETE FROM FourthBox")
    void deleteAll();
}
