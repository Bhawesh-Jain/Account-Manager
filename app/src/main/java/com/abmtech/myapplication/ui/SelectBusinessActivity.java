package com.abmtech.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.abmtech.myapplication.databinding.ActivitySelectBusinessBinding;

public class SelectBusinessActivity extends AppCompatActivity {
    private Activity activity;
    private ActivitySelectBusinessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectBusinessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
    }
}