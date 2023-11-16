package com.abmtech.myapplication.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.abmtech.myapplication.database.DatabaseFormat;

@Entity
public class Business {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = DatabaseFormat.Business.COLUMN_NAME)
    private String name;

    @ColumnInfo(name = DatabaseFormat.Business.COLUMN_CREATE_DATE)
    private String date;

    @ColumnInfo(name = DatabaseFormat.Business.COLUMN_USER_ID)
    private String userId;

    @ColumnInfo(name = DatabaseFormat.Business.COLUMN_SELECTED)
    private String selected;

    public Business(String name, String date, String userId, String selected) {
        this.name = name;
        this.date = date;
        this.userId = userId;
        this.selected = selected;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public Business() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Business{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}