package com.example.venteDeVoitures.roomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.venteDeVoitures.roomDB.entities.Advertiser;
import com.example.venteDeVoitures.roomDB.entities.Client;

@Dao
public interface ClientDAO {
    @Query("SELECT * FROM client WHERE username=(:username)")
    Client getClientByUsername(String username);

    @Query("SELECT * FROM client WHERE username=(:username) AND password=(:password)")
    Client getClientByUsernamePassword(String username, String password);

    @Query("SELECT * FROM client WHERE id=(:id)")
    Client getClientById(int id);

    @Insert
    void insertNewUser(Client user);
}
