package com.example.venteDeVoitures.roomDB.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Advertiser {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "password")
    public String password;
    @ColumnInfo(name = "professional")
    public boolean professional;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "phone")
    public String phone;

    public Advertiser(){};
    public Advertiser(String usr, String psw, boolean pro, String eml, String phn){
        username = usr;
        password = psw;
        professional = pro;
        email = eml;
        phone = phn;
    }
}
