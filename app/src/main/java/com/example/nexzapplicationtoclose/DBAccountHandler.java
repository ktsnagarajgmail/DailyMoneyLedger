package com.example.nexzapplicationtoclose;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DBAccountHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "MLedger";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "dailyLedgerAccount";
    private static final String NAME_COL = "name";
    private static final String USERNAME_COL = "username";
    private static final String MOBILE_COL = "mobile";
    private static final String EMAIL_COL = "email";

    public DBAccountHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + NAME_COL + " TEXT, "
                + USERNAME_COL + " TEXT PRIMARY KEY,"
                + MOBILE_COL + " TEXT,"
                + EMAIL_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addAccountData(String name, String userName, String mobile, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(USERNAME_COL, userName);
        values.put(MOBILE_COL, mobile);
        values.put(EMAIL_COL, email);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList readAccountData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList accountDataArrayList
                = new ArrayList<>();
        cursorCourses.moveToFirst();
        accountDataArrayList.add(cursorCourses.getString(1));
        accountDataArrayList.add(cursorCourses.getString(2));
        accountDataArrayList.add(cursorCourses.getString(3));
        accountDataArrayList.add(cursorCourses.getString(4));
        cursorCourses.close();
        return accountDataArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}