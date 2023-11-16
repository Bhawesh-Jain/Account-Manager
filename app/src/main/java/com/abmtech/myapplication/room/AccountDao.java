package com.abmtech.myapplication.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.abmtech.myapplication.model.Account;
import com.abmtech.myapplication.model.Business;

import java.util.List;

@Dao
public interface AccountDao {

    @Query("Select * from Account where business_id is (:businessId)")
    List<Account> getAccountsByBusinessId(int businessId);

    @Insert
    void insertAccount(Account account);

}