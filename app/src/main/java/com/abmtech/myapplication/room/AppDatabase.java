package com.abmtech.myapplication.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abmtech.myapplication.database.DatabaseFormat;
import com.abmtech.myapplication.model.Account;
import com.abmtech.myapplication.model.Business;
import com.abmtech.myapplication.model.User;

@Database(entities = {User.class, Business.class, Account.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract BusinessDao businessDao();

    public abstract AccountDao accountDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, DatabaseFormat.DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
