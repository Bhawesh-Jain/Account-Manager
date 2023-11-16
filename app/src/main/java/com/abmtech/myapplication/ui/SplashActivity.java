package com.abmtech.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.abmtech.myapplication.R;
import com.abmtech.myapplication.utils.Session;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Activity activity = this;
        Session session = new Session(activity);

        new Handler().postDelayed(() -> {
            if (session.isLoggedIn())
                startActivity(new Intent(activity, CheckPinActivity.class));
            else
                startActivity(new Intent(activity, LoginActivity.class).putExtra("mode", "normal"));
            finish();
        }, 2000);
    }
}