package com.tikhonovks.mireaproject.ui.ducks;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tikhonovks.mireaproject.ui.history.Item;

import java.util.List;

@Dao
public interface DuckDao {
    @Query("SELECT * FROM Duck")
    LiveData<List<Duck>> getAllDuck();
    @Insert
    void insert(Duck duck);
    @Update
    void update(Duck duck);
    @Delete
    void delete(Duck duck);
}
