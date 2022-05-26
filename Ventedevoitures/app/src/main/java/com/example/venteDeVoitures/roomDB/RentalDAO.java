package com.example.venteDeVoitures.roomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.venteDeVoitures.roomDB.entities.Rental;
import com.example.venteDeVoitures.roomDB.entities.Sell;

import java.util.List;

@Dao
public interface RentalDAO {
    @Insert
    void insertRental(Rental r);

    @Query("SELECT * FROM Rental WHERE id_owner=(:userID)")
    List<Rental> getRentalsByUserId(int userID);

    @Query("SELECT * FROM Rental WHERE brand=(:brand) OR model=(:model)")
    List<Rental> getRentals(String brand, String model);

    @Delete
    void deleteRental(Rental r);

    @Query("SELECT * FROM Rental WHERE id=(:id)")
    Rental getRentalById(int id);

    @Query("SELECT * FROM Rental WHERE time_stamp>(:time)")
    List<Rental> getRentalsAtLeast(long time);


    @Query("DELETE FROM Rental WHERE id=(:userId)")
    void deleteRentalById(long userId);
}
