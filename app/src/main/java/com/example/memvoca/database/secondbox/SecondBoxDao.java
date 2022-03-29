package com.example.memvoca.database.secondbox;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SecondBoxDao {
    @Query("SELECT * FROM SecondBox")
    LiveData<List<SecondBox>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SecondBox secondBox);

    @Delete
    void delete(SecondBox secondBox);
}
