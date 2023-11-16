package com.abmtech.myapplication.adapters;

import static com.abmtech.myapplication.utils.Const.getTimeAgo;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abmtech.myapplication.databinding.ItemAccountLayBinding;
import com.abmtech.myapplication.model.Account;
import com.google.type.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {
    private final Context context;
    private final List<Account> data;

    public AccountsAdapter(Context context, List<Account> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemAccountLayBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (data.get(position) != null) {
            Account account = data.get(position);

            holder.binding.textName.setText(account.getName());
            holder.binding.textUpdateDate.setText("Updated " + getTimeAgo(account.getUpdateDate()));
            holder.binding.textCreditBal.setText("₹ " + account.getCreditBalance());
            holder.binding.textDebitBal.setText("₹ " + account.getDebitBalance());

            String bal = String.valueOf(Double.parseDouble(account.getDebitBalance()) - Double.parseDouble(account.getCreditBalance()));
            holder.binding.textBalance.setText("₹ " + bal);

        }
    }

    public void insertAccount(Account account) {
        data.add(account);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemAccountLayBinding binding;

        public ViewHolder(@NonNull ItemAccountLayBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
