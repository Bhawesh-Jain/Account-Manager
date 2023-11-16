package com.abmtech.myapplication.ui;

import static com.abmtech.myapplication.utils.Const.getCurrentTime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.abmtech.myapplication.databinding.ActivitySignupBinding;
import com.abmtech.myapplication.model.Business;
import com.abmtech.myapplication.room.AppDatabase;
import com.abmtech.myapplication.model.User;
import com.abmtech.myapplication.room.BusinessDao;
import com.abmtech.myapplication.room.UserDao;
import com.abmtech.myapplication.utils.ProgressDialog;

public class SignupActivity extends AppCompatActivity {
    private Activity activity;
    private ActivitySignupBinding binding;
    private UserDao dao;
    private BusinessDao businessDao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        progressDialog = new ProgressDialog(activity);

        dao = AppDatabase.getInstance(activity).userDao();
        businessDao = AppDatabase.getInstance(activity).businessDao();


        String phone = getIntent().getStringExtra("phone");
        binding.edtMobile.setText(phone);

        binding.icBack.setOnClickListener(view -> onBackPressed());

        binding.textSignup.setOnClickListener(view -> {
            String createDate = getCurrentTime();

            User model = new User();

            model.setName(binding.edtName.getText().toString().trim());
            model.setPhone(binding.edtMobile.getText().toString().trim());
            model.setPin("");
            model.setDate(createDate);
            model.setBio("false");

            new Thread(() -> {
                dao.insertUser(model);

                User user = dao.loadByPhone(phone);

                businessDao.insertBusiness(new Business(binding.edtBusinessName.getText().toString(), user.getDate(), String.valueOf(user.getUid()), "1"));

                runOnUiThread(() -> {
                    progressDialog.dismiss();

                    finish();
                });
            }).start();
        });
    }

}