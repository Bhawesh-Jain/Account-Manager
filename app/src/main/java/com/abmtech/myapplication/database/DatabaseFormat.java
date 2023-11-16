package com.abmtech.myapplication.database;

import android.provider.BaseColumns;

public final class DatabaseFormat {
    public static final String DB_NAME= "account_db";
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseFormat() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "user_table";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_BIO = "bio";
        public static final String COLUMN_CREATE_DATE = "create_date";
        public static final String COLUMN_PIN = "pin";
    }

    public static class Business implements BaseColumns {
        public static final String TABLE_NAME = "business";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_SELECTED = "selected";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CREATE_DATE = "create_date";
    }

    public static class Book implements BaseColumns {
        public static final String TABLE_NAME = "book";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_BUSINESS_ID = "business_id";
        public static final String COLUMN_ACCOUNT_NAME = "name";
        public static final String COLUMN_CREATE_DATE = "create_date";
    }

    public static class Account implements BaseColumns {
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_BOOK_ID = "book_id";
        public static final String COLUMN_BUSINESS_ID = "business_id";
        public static final String COLUMN_ACCOUNT_NAME = "name";
        public static final String COLUMN_CREATE_DATE = "create_date";
        public static final String COLUMN_UPDATE_DATE = "update_date";
        public static final String COLUMN_DEBIT_BALANCE = "debit_bal";
        public static final String COLUMN_CREDIT_BALANCE = "credit_bal";
    }

    public static class Transaction implements BaseColumns {
        public static final String TABLE_NAME = "transaction";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_BOOK_ID = "book_id";
        public static final String COLUMN_ACCOUNT_ID = "account_id";
        public static final String COLUMN_BUSINESS_ID = "business_id";
        public static final String COLUMN_PARTICULAR = "particular";
        public static final String COLUMN_CREDIT = "credit";
        public static final String COLUMN_DEBIT = "debit";
        public static final String COLUMN_TYPE = "type";
    }
}
