package com.abmtech.myapplication.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.abmtech.myapplication.model.Account;
import com.abmtech.myapplication.model.Business;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BusinessDao {
    @Query("SELECT * FROM business")
    List<Business> getAllBusiness();

    @Query("Select * from business where uid is (:userId)")
    List<Business> getBusinessByUid(int userId);

    @Query("Select * from business where uid is (:userId) AND selected = '1'")
    Business getSelectedBusinessByUid(int userId);

    @Query("Update business Set selected = '0' where uid is (:businessId)")
    void unSelectBusinessByUid(int businessId);

    @Query("Update business Set selected = '1' where uid is (:businessId)")
    void selectBusinessByUid(int businessId);

    @Query("Delete from business where uid is (:userId)")
    void deleteBusinessByUid(int userId);

    @Query("SELECT COUNT(*) FROM business")
    int getBusinessCount();

    @Insert
    void insertBusiness(Business business);

    @Delete
    void delete(Business business);

}