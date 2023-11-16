package com.abmtech.myapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.abmtech.myapplication.ui.PinActivity;


public class Session extends Object {
    private static final String TAG = Session.class.getSimpleName();
    private static final String PREF_NAME = "main";
    private Context _context;
    public SharedPreferences Rapidine_pref;
    private final SharedPreferences.Editor editor;
    private final String LOGGED_IN = "LOGGED_IN";
    private final String MY_PIN = "MY_PIN";
    private final String USER_ID = "USER_ID";
    private final String USER_NAME = "USER_NAME";
    private final String USER_MOBILE = "USER_MOBILE";
    private final String USER_EMAIL = "USER_EMAIL";
    private final String USER_PROFILE = "USER_PROFILE";

    public Session(Context context) {
        this._context = context;
        Rapidine_pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = Rapidine_pref.edit();
        editor.apply();
    }

    public void setMyPin(String myPin) {
        editor.putString(MY_PIN, myPin);
        editor.apply();
    }

    public String getMyPin() {
        return Rapidine_pref.getString(MY_PIN, "");
    }

    public void setUserId(String userId) {
        editor.putString(USER_ID, userId);
        editor.apply();
    }

    public String getUserId() {
        return Rapidine_pref.getString(USER_ID, "0");
    }

    public void setUserName(String userName) {
        editor.putString(USER_NAME, userName);
        editor.apply();
    }

    public String getUserName() {
        return Rapidine_pref.getString(USER_NAME, "");
    }

    public void setUserMobile(String userMobile) {
        editor.putString(USER_MOBILE, userMobile);
        editor.apply();
    }

    public String getUserMobile() {
        return Rapidine_pref.getString(USER_MOBILE, "");
    }

    public void setUserEmail(String userEmail) {
        editor.putString(USER_EMAIL, userEmail);
        editor.apply();
    }

    public String getUserEmail() {
        if (Rapidine_pref.getString(USER_EMAIL, "").isEmpty())
            return "Not Found!";
        return Rapidine_pref.getString(USER_EMAIL, "");
    }

    public void setProfileImage(String userProfile) {
        editor.putString(USER_PROFILE, userProfile);
        editor.apply();
    }

    public String getProfileImage() {
        return Rapidine_pref.getString(USER_PROFILE, "");
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return Rapidine_pref.getBoolean(LOGGED_IN, false);
    }

    public void logout() {
        Intent showLogin = new Intent(_context, PinActivity.class);
        showLogin.putExtra("mode", "");
        showLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        showLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(showLogin);
    }

}