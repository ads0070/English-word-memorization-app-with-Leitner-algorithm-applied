package com.example.memvoca.database.zerobox;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ZeroBoxDao {
    @Query("SELECT * FROM ZeroBox")
    LiveData<List<ZeroBox>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ZeroBox zeroBox);

    @Delete
    void delete(ZeroBox zeroBox);

    @Query("DELETE FROM ZeroBox")
    void deleteAll();
}
