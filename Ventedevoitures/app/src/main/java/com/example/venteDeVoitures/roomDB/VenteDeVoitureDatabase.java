package com.example.venteDeVoitures.roomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.venteDeVoitures.roomDB.entities.Advertiser;
import com.example.venteDeVoitures.roomDB.entities.Client;
import com.example.venteDeVoitures.roomDB.entities.Rental;
import com.example.venteDeVoitures.roomDB.entities.Saved;
import com.example.venteDeVoitures.roomDB.entities.Sell;

@Database(entities = {Advertiser.class, Client.class, Rental.class, Sell.class, Saved.class}, version = 1)
public abstract class VenteDeVoitureDatabase extends RoomDatabase {
    public abstract AdvertiserDAO advertiserDAO();
    public abstract ClientDAO clientDAO();
    public abstract RentalDAO rentalDAO();
    public abstract SellDAO sellDAO();
    public abstract SavedDAO savedDAO();
    private static VenteDeVoitureDatabase instance;
    private static String dbName = "vente_de_voiture_3.0";

    public static synchronized VenteDeVoitureDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), VenteDeVoitureDatabase.class, dbName).allowMainThreadQueries().build();
        }
        return instance;
    }
}
