package com.example.venteDeVoitures.advertiser;

import androidx.lifecycle.ViewModel;
import androidx.room.ColumnInfo;

import com.example.venteDeVoitures.roomDB.VenteDeVoitureDatabase;
import com.example.venteDeVoitures.roomDB.entities.Rental;
import com.example.venteDeVoitures.roomDB.entities.Sell;

import java.util.Calendar;
import java.util.List;

public class AdvertiserViewModel extends ViewModel {
    private int userID;
    private VenteDeVoitureDatabase db;
    private static AdvertiserViewModel instance;

    public boolean isProfessional(){
        return db.advertiserDAO().getAvertiserById(userID).professional;
    }

    public void setArgs(VenteDeVoitureDatabase d, int userID){
        if(instance == null){
            this.db = d;
            this.userID = userID;
            this.instance = this;
        }
    }

    public boolean createRental(String brand, String model, String year, String distance, String price, boolean viaEmail, boolean viaPhone, byte[] image1, byte[] image2, byte[] image3, byte[] image4){
        if(db.advertiserDAO().getAvertiserById(userID) != null){
            Rental rental = new Rental(userID, brand, model, year, distance, price);
            rental.email = viaEmail;
            rental.phone = viaPhone;
            rental.image1 = image1;
            rental.image2 = image2;
            rental.image3 = image3;
            rental.image4 = image4;
            rental.timeStamp = Calendar.getInstance().getTimeInMillis();
            db.rentalDAO().insertRental(rental);
            return true;
        }
        return false;
    }

    public boolean createSell(String brand, String model, String year, String distance, String price, boolean viaEmail, boolean viaPhone, byte[] image1, byte[] image2, byte[] image3, byte[] image4){
        if(db.advertiserDAO().getAvertiserById(userID) != null){
            Sell sell = new Sell(userID, brand, model, year, distance, price);
            sell.email = viaEmail;
            sell.phone = viaPhone;
            sell.image1 = image1;
            sell.image2 = image2;
            sell.image3 = image3;
            sell.image4 = image4;
            sell.timeStamp = Calendar.getInstance().getTimeInMillis();
            db.sellDAO().insertSell(sell);
            return true;
        }
        return false;
    }

    public List<Rental> getUserRentals(){
        if(db.advertiserDAO().getAvertiserById(userID) != null){
            return db.rentalDAO().getRentalsByUserId(userID);
        }
        return null;
    }

    public List<Sell> getUserSells(){
        if(db.advertiserDAO().getAvertiserById(userID) != null){
            return db.sellDAO().getSellsByUserId(userID);
        }
        return null;
    }

    public List<Sell> getSearchSells(String model, String brand){
        return db.sellDAO().getSells(model, brand);
    }

    public List<Rental> getSearchRentals(String model, String brand){
        return db.rentalDAO().getRentals(model, brand);
    }

    public void editRental(int id, String brand, String model, String year, String distance, String price, byte[] image1, byte[] image2, byte[] image3, byte[] image4){
        db.rentalDAO().deleteRentalById(id);

        Rental rental = new Rental(userID, brand, model, year, distance, price);
        rental.id = id;
        rental.image1 = image1;
        rental.image2 = image2;
        rental.image3 = image3;
        rental.image4 = image4;
        db.rentalDAO().insertRental(rental);
    }

    public void editSell(int id, String brand, String model, String year, String distance, String price, byte[] image1, byte[] image2, byte[] image3, byte[] image4){
        db.sellDAO().deleteSellById(id);

        Sell sell = new Sell(userID, brand, model, year, distance, price);
        sell.id = id;
        sell.image1 = image1;
        sell.image2 = image2;
        sell.image3 = image3;
        sell.image4 = image4;
        db.sellDAO().insertSell(sell);
    }


    public void deleteRental(Rental r){
        db.rentalDAO().deleteRental(r);
    }

    public void deleteSell(Sell s){
        db.sellDAO().deleteSell(s);
    }

    public static AdvertiserViewModel getInstance(){
        return instance;
    }
}
