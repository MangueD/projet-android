package com.example.venteDeVoitures.roomDB.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Rental {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ForeignKey(entity = Advertiser.class,
                parentColumns = "id",
                childColumns = "id_owner",
                onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "id_owner")
    public int id_owner;
    @ColumnInfo(name = "brand")
    public String brand;
    @ColumnInfo(name = "model")
    public String model;
    @ColumnInfo(name = "year")
    public String year;
    @ColumnInfo(name = "distance")
    public String distance;
    @ColumnInfo(name = "price")
    public String price;
    @ColumnInfo(name = "image1")
    public byte[] image1;
    @ColumnInfo(name = "image2")
    public byte[] image2;
    @ColumnInfo(name = "image3")
    public byte[] image3;
    @ColumnInfo(name = "image4")
    public byte[] image4;
    @ColumnInfo(name = "time_stamp")
    public long timeStamp;
    @ColumnInfo(name = "via_email")
    public boolean email;
    @ColumnInfo(name = "via_phone")
    public boolean phone;

    public Rental(int ido, String bra, String mod, String yea, String dis, String pri){
        id_owner = ido;
        brand = bra;
        model = mod;
        year = yea;
        distance = dis;
        price = pri;
    }

    public Rental(){}
}