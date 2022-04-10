package com.tikhonovks.mireaproject.ui.history;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatebase extends RoomDatabase {
    public abstract ItemDao itemDao();
}
