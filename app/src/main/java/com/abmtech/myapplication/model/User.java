package com.abmtech.myapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.abmtech.myapplication.database.DatabaseFormat;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = DatabaseFormat.Users.COLUMN_BIO)
    private String bio;

    @ColumnInfo(name = DatabaseFormat.Users.COLUMN_PHONE)
    private String phone;

    @ColumnInfo(name = DatabaseFormat.Users.COLUMN_USER_NAME)
    private String name;

    @ColumnInfo(name = DatabaseFormat.Users.COLUMN_PIN)
    private String pin;

    @ColumnInfo(name = DatabaseFormat.Users.COLUMN_CREATE_DATE)
    private String date;

    public User() {

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User(String bio, String phone, String name, String pin, String date) {
        this.bio = bio;
        this.phone = phone;
        this.name = name;
        this.pin = pin;
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", bio='" + bio + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", pin='" + pin + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}