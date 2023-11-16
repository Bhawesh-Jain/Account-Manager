package com.abmtech.myapplication.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.abmtech.myapplication.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("Select * from user where uid is (:userId)")
    User loadByID(int userId);

    @Query("Select * from user where User.phone is (:phone)")
    User loadByPhone(String phone);


    @Query("Update user Set pin = (:pin) where User.uid is (:userId)")
    void UpdateUserPin(int userId, String pin);

    @Query("Delete from user where uid is (:userId)")
    void deleteByUid(int userId);


    @Insert
    void insertUser(User user);

    @Delete
    void delete(User user);
}