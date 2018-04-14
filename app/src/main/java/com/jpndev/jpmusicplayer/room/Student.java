package com.jpndev.jpmusicplayer.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by JAIGISH on 12-04-2018.
 */

@Entity(tableName = "student")
public class Student {

    @PrimaryKey
    @NonNull
    int id;
    @ColumnInfo(name = "studentname")
    String name;
    @ColumnInfo(name = "studentgender")
    String gender;

  /*  @ColumnInfo(name = "studentimage")
    String imageurl;*/
    @ColumnInfo(name = "studentaddress")
    String address;
    @ColumnInfo(name = "studentlocation")
    String location;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @NonNull
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}