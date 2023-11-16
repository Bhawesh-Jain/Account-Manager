package com.abmtech.myapplication.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.abmtech.myapplication.database.DatabaseFormat;

@Entity
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int accountId;

    @ColumnInfo(name = DatabaseFormat.Account.COLUMN_ACCOUNT_NAME)
    private String name;

    @ColumnInfo(name = DatabaseFormat.Account.COLUMN_CREATE_DATE)
    private String createDate;

    @ColumnInfo(name = DatabaseFormat.Account.COLUMN_UPDATE_DATE)
    private String updateDate;


    @ColumnInfo(name = DatabaseFormat.Account.COLUMN_DEBIT_BALANCE)
    private String debitBalance;


    @ColumnInfo(name = DatabaseFormat.Account.COLUMN_CREDIT_BALANCE)
    private String creditBalance;

    @ColumnInfo(name = DatabaseFormat.Account.COLUMN_USER_ID)
    private String userId;

    @ColumnInfo(name = DatabaseFormat.Account.COLUMN_BUSINESS_ID)
    private String businessId;

    @ColumnInfo(name = DatabaseFormat.Account.COLUMN_BOOK_ID)
    private String bookId;

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;

    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(String debitBalance) {
        this.debitBalance = debitBalance;
    }

    public String getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(String creditBalance) {
        this.creditBalance = creditBalance;
    }

    public Account(String name, String createDate, String updateDate, String debitBalance, String creditBalance, String userId, String businessId, String bookId) {
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.debitBalance = debitBalance;
        this.creditBalance = creditBalance;
        this.userId = userId;
        this.businessId = businessId;
        this.bookId = bookId;
    }
}