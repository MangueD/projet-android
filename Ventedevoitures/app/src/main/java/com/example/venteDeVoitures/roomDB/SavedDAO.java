package com.example.venteDeVoitures.roomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.venteDeVoitures.roomDB.entities.Saved;

import java.util.List;

@Dao
public interface SavedDAO {
    @Insert
    void insertSaved(Saved r);

    @Query("SELECT * FROM Saved WHERE id_client=(:userID)")
    List<Saved> getUserSaved(int userID);

    @Delete
    void deleteSaved(Saved r);

    @Query("SELECT * FROM Saved WHERE type=(:type) AND id_client=(:userID) AND id_advert=(:id)")
    Saved saveExist(String type, int userID, int id);

}