package com.example.venteDeVoitures.roomDB.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Saved {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "id_client")
    public int clientID;
    @ColumnInfo(name = "id_advert")
    public int advertID;
    @ColumnInfo(name = "type")
    public String advertType;
}
