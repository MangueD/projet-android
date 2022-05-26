package com.example.venteDeVoitures.roomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.venteDeVoitures.roomDB.entities.Rental;
import com.example.venteDeVoitures.roomDB.entities.Sell;

import java.util.List;

@Dao
public interface SellDAO {
    @Insert
    void insertSell(Sell s);

    @Query("SELECT * FROM Sell WHERE id_owner=(:userID)")
    List<Sell> getSellsByUserId(int userID);

    @Query("SELECT * FROM Sell WHERE brand=(:brand) OR model=(:model)")
    List<Sell> getSells(String brand, String model);

    @Query("SELECT * FROM Sell WHERE id=(:id)")
    Sell getSellById(int id);


    @Delete
    void deleteSell(Sell s);

    @Query("SELECT * FROM Sell WHERE time_stamp>(:time)")
    List<Sell> getSellsAtLeast(long time);

    @Query("DELETE FROM Sell WHERE id=(:userId)")
    void deleteSellById(long userId);
}
