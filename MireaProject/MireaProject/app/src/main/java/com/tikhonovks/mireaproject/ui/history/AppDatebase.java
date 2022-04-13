package com.tikhonovks.mireaproject.ui.history;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tikhonovks.mireaproject.ui.ducks.Duck;
import com.tikhonovks.mireaproject.ui.ducks.DuckDao;

@Database(entities = {Item.class, Duck.class}, version = 2)
public abstract class AppDatebase extends RoomDatabase {
    public abstract ItemDao itemDao();
    public abstract DuckDao duckDao();
}
