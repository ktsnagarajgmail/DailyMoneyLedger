package com.example.nexzapplicationtoclose;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "MLedger";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "dailyLedgerab";
    private static final String ID_COL = "id";
    private static final String TYPE_COL = "type";
    private static final String AMOUNT_COL = "amount";
    private static final String DESC_COL = "description";
    double crdValue, dbValue, abValue;

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //Toast.makeText(context.getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TYPE_COL + " TEXT,"
                + AMOUNT_COL + " INTEGER,"
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

    public ArrayList<AccountData> readTopCourses()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE type = 'Debit(-)' ORDER BY amount DESC LIMIT 5", null);
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

    @SuppressLint("Range")
    public String readDebitValue()
    {
        /*int s = 0;
        double x = 0.00;*/
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses
                = db.rawQuery("SELECT SUM(amount) FROM " + TABLE_NAME +" WHERE type = 'Debit(-)'", null);
        /*cursorCourses.moveToFirst();
        s = cursorCourses.getInt(cursorCourses.getColumnIndex("SUM(amount)"));
        cursorCourses.close();
        return s;*/
        cursorCourses.moveToFirst();
        String str = cursorCourses.getString(0);
        dbValue = cursorCourses.getInt(cursorCourses.getColumnIndex("SUM(amount)"));
        double damt = Double.parseDouble(str);
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        String strFormatted =decimalFormat.format(damt);
        cursorCourses.close();
        return strFormatted;
    }

    @SuppressLint("Range")
    public String readCreditValue()
    {
       // int s = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses
                = db.rawQuery("SELECT SUM(amount) FROM " + TABLE_NAME +" WHERE type = 'Credit(+)'", null);
        /*cursorCourses.moveToFirst();
        s = cursorCourses.getInt(cursorCourses.getColumnIndex("SUM(amount)"));
        cursorCourses.close();
        return s;*/

        cursorCourses.moveToFirst();
        String strs = cursorCourses.getString(0);
        crdValue = cursorCourses.getInt(cursorCourses.getColumnIndex("SUM(amount)"));
        double damts = Double.parseDouble(strs);
        DecimalFormat decimalFormats = new DecimalFormat("#,###.00");
        String strFormatteds =decimalFormats.format(damts);
        cursorCourses.close();
        return strFormatteds;
    }

    @SuppressLint("Range")
    public String abValue()
    {
        // int s = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        abValue = crdValue-dbValue;
        String sr = decimalFormat.format(abValue);
        return sr;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}