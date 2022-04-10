package com.tikhonovks.mireaproject.ui.history;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM item")
    List<Item> getAll();
    @Query("SELECT * FROM item WHERE id = :id")
    Item getById(long id);
    @Insert
    void insert(Item Item);
    @Update
    void update(Item Item);
    @Delete
    void delete(Item Item);
}
