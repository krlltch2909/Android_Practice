package com.tikhonovks.mireaproject.ui.ducks;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Duck {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String learned_phrase;
    public String age;

}