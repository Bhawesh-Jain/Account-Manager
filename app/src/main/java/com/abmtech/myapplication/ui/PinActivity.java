package com.abmtech.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.abmtech.myapplication.R;
import com.abmtech.myapplication.databinding.ActivityPinBinding;

public class PinActivity extends AppCompatActivity {
    private Activity activity;
    private ActivityPinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;


    }
}