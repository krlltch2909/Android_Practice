package com.tikhonovks.mireaproject.ui.history;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)

    public long id;
    public String number;
    public String ip;
    public String mac;


}
