package com.abmtech.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abmtech.myapplication.adapters.AccountsAdapter;
import com.abmtech.myapplication.databinding.ActivityDashboardBinding;
import com.abmtech.myapplication.databinding.AddAccountBottomSheetBinding;
import com.abmtech.myapplication.model.Account;
import com.abmtech.myapplication.model.Business;
import com.abmtech.myapplication.room.AccountDao;
import com.abmtech.myapplication.room.AppDatabase;
import com.abmtech.myapplication.room.BusinessDao;
import com.abmtech.myapplication.utils.Const;
import com.abmtech.myapplication.utils.Session;
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private Activity activity;
    private ActivityDashboardBinding binding;
    private Session session;
    private BusinessDao businessDao;
    private AccountDao accountDao;
    private int businessId = -1;
    private AccountsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        session = new Session(activity);
        businessDao = AppDatabase.getInstance(activity).businessDao();
        accountDao = AppDatabase.getInstance(activity).accountDao();

        new Thread(() -> {
            Business business = businessDao.getSelectedBusinessByUid(Integer.parseInt(session.getUserId()));
            businessId = business.getUid();

            runOnUiThread((() -> binding.textHeader.setText(business.getName())));

            List<Account> accounts = accountDao.getAccountsByBusinessId(businessId);

            runOnUiThread((() -> {
                binding.progressbar.setVisibility(View.GONE);

                if (accounts != null && accounts.size() > 0) {
                    setAccountRecycler(accounts);
                } else {
                    binding.textNoData.setVisibility(View.VISIBLE);
                }
            }));

        }).start();

        binding.add.setOnClickListener(view -> openDialog());
    }

    private void openDialog() {

        RoundedBottomSheetDialog mBottomSheetDialog = new RoundedBottomSheetDialog(this);

        AddAccountBottomSheetBinding bottomBinding = AddAccountBottomSheetBinding.inflate(getLayoutInflater());

        mBottomSheetDialog.setContentView(bottomBinding.getRoot());

        bottomBinding.textContinue.setOnClickListener(view -> {
            String name = bottomBinding.edtMobile.getText().toString();
            String date = Const.getCurrentTime();

            Account account = new Account(name, date, date, "0", "0", session.getUserId(), String.valueOf(businessId), "");

            new Thread(() -> {
                accountDao.insertAccount(account);

                runOnUiThread(() -> {
                    if (adapter != null)
                        adapter.insertAccount(account);
                    else {
                        List<Account> accountList = new ArrayList<>();
                        accountList.add(account);

                        setAccountRecycler(accountList);
                    }
                });
            }).start();
            mBottomSheetDialog.dismiss();
        });

        mBottomSheetDialog.show();
    }

    private void setAccountRecycler(List<Account> accounts) {
        adapter = new AccountsAdapter(activity, accounts);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
//        binding.recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        binding.textNoData.setVisibility(View.GONE);
    }
}