package com.example.venteDeVoitures.client;

import androidx.lifecycle.ViewModel;

import com.example.venteDeVoitures.roomDB.VenteDeVoitureDatabase;
import com.example.venteDeVoitures.roomDB.entities.Advertiser;
import com.example.venteDeVoitures.roomDB.entities.Client;
import com.example.venteDeVoitures.roomDB.entities.Rental;
import com.example.venteDeVoitures.roomDB.entities.Saved;
import com.example.venteDeVoitures.roomDB.entities.Sell;

import java.util.Calendar;
import java.util.List;

public class ClientViewModel extends ViewModel {
    private int userID;
    private String username;
    private String password;
    private VenteDeVoitureDatabase db;
    private static ClientViewModel instance;

    public void setArgs(VenteDeVoitureDatabase d){
        if(instance == null){
            this.db = d;
            this.instance = this;
        }
    }

    public boolean isConnected(){
        if(db.clientDAO().getClientByUsernamePassword(username, password) != null)
            return true;
        return false;
    }

    public boolean connect(String un, String pw){
        if(db.clientDAO().getClientByUsernamePassword(un, pw) != null){
            username = un;
            password = pw;
            return true;
        }
        return false;
    }

    public List<Saved> getUserSaved(){
        if(isConnected()){
            return db.savedDAO().getUserSaved(db.clientDAO().getClientByUsernamePassword(username,password).id);
        }
        return null;
    }

    public List<Rental> getAdvertiserRentals(int id){
        if(db.advertiserDAO().getAvertiserById(id) != null){
            return db.rentalDAO().getRentalsByUserId(id);
        }
        return null;
    }

    public List<Sell> getAdvertiserSells(int id){
        if(db.advertiserDAO().getAvertiserById(id) != null){
            return db.sellDAO().getSellsByUserId(id);
        }
        return null;
    }

    public boolean createUser(String un, String pw){
        if(db.clientDAO().getClientByUsernamePassword(un, pw) != null){
            return false;
        }
        Client client = new Client();
        client.username = un;
        client.password = pw;
        db.clientDAO().insertNewUser(client);
        return true;
    }

    public void disconnect(){
        username = null;
        password = null;
    }

    public List<Sell> getSearchSells(String model, String brand){
        return db.sellDAO().getSells(model, brand);
    }

    public List<Rental> getSearchRentals(String model, String brand){
        return db.rentalDAO().getRentals(model, brand);
    }

    public Rental getRentalById(int id){
        return db.rentalDAO().getRentalById(id);
    }

    public Sell getSellById(int id){
        return  db.sellDAO().getSellById(id);

    }

    public boolean savedExist(String type, int id){
        userID = db.clientDAO().getClientByUsernamePassword(username,password).id;
        if(db.savedDAO().saveExist(type, userID, id) == null){
            Saved saved = new Saved();
            saved.advertType = type;
            saved.advertID = id;
            saved.clientID = userID;
            db.savedDAO().insertSaved(saved);
            return true;
        }
        return false;
    }

    public void deleteSaved(Saved s){
        db.savedDAO().deleteSaved(s);
    }

    public Advertiser getAdvertiser(int id){
        return db.advertiserDAO().getAvertiserById(id);
    }

    public List<Sell> getNewSells(){
        return db.sellDAO().getSellsAtLeast(Calendar.getInstance().getTimeInMillis() - 24*60*60*1000);
    }

    public List<Rental> getNewRentals(){
        return db.rentalDAO().getRentalsAtLeast(Calendar.getInstance().getTimeInMillis() - 24*60*60*1000);
    }


    public static ClientViewModel getInstance(){
        return instance;
    }
}
