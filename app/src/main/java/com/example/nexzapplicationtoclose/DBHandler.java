package com.example.nexzapplicationtoclose;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "MLedger";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "dailyLedger";
    private static final String ID_COL = "id";
    private static final String TYPE_COL = "type";
    private static final String AMOUNT_COL = "amount";
    private static final String DESC_COL = "description";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TYPE_COL + " TEXT,"
                + AMOUNT_COL + " TEXT,"
                + DESC_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addNewCourse(String type, String amount, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TYPE_COL, type);
        values.put(AMOUNT_COL, amount);
        values.put(DESC_COL, desc);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<AccountData> readCourses()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<AccountData> accountDataArrayList
                = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                accountDataArrayList.add(new AccountData(
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return accountDataArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}