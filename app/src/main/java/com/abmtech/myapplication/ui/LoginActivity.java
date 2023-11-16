package com.abmtech.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.abmtech.myapplication.databinding.ActivityLoginBinding;
import com.abmtech.myapplication.model.User;
import com.abmtech.myapplication.room.AppDatabase;
import com.abmtech.myapplication.room.UserDao;
import com.abmtech.myapplication.utils.ProgressDialog;
import com.abmtech.myapplication.utils.Session;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {
    private Activity activity;
    private ActivityLoginBinding binding;
    private Session session;
    private UserDao userDao;
    private ProgressDialog progressDialog;
    private String mode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        progressDialog = new ProgressDialog(activity);

        session = new Session(activity);

        userDao = AppDatabase.getInstance(activity).userDao();

        mode = getIntent().getStringExtra("mode");

        binding.textLogin.setOnClickListener(view -> validate());

    }

    private void validate() {
        String phone = binding.edtMobile.getText().toString();
        if (phone.length() < 10) {
            FancyToast.makeText(this, "Invalid Phone Number!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        } else {
            progressDialog.show();

            new Thread(() -> {
                User entities = userDao.loadByPhone(phone);

                runOnUiThread(() -> {
                    progressDialog.dismiss();

                    if (entities != null) {
                        session.setUserId(String.valueOf(entities.getUid()));
                        session.setUserName(entities.getName());
                        session.setUserMobile(entities.getPhone());

                        if (entities.getPin().isEmpty())
                            startActivity(new Intent(activity, SetPinActivity.class));
                        else {
                            if (mode.equals("forgot"))
                                startActivity(new Intent(activity, SetPinActivity.class));
                            else
                                startActivity(new Intent(activity, DashboardActivity.class));
                        }
                    } else {
                        startActivity(new Intent(activity, SignupActivity.class).putExtra("phone", phone));
                    }
                });
            }).start();
        }
    }

}