package com.example.venteDeVoitures.roomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.venteDeVoitures.roomDB.entities.Advertiser;

@Dao
public interface AdvertiserDAO {
    @Query("SELECT * FROM advertiser WHERE username=(:username)")
    Advertiser getAvertiserByUsername(String username);

    @Query("SELECT * FROM advertiser WHERE username=(:username) AND password=(:password)")
    Advertiser getAvertiserByUsernamePassword(String username, String password);

    @Query("SELECT * FROM advertiser WHERE id=(:id)")
    Advertiser getAvertiserById(int id);


    @Insert
    void insertNewUser(Advertiser user);
}
