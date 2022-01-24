package com.example.weatherapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "WeatherAppTest.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "cities";
    public static final String ID = "ID";
    public static final String COL_1 = "City";

//COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_1 + " TEXT)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertCity(String cityName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,cityName);
        long newRowId = db.insert(TABLE_NAME, null, contentValues);
        Log.i("insertionInfo", "insertCity: "+newRowId);
        if (newRowId == -1)
            return false;
        else
            return true;
    }

    public List getCities(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COL_1};

        String sortOrder = ID + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,         // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List cities = new ArrayList<>();
        while(cursor.moveToNext()) {
            String city = cursor.getString(cursor.getColumnIndexOrThrow(COL_1));
            cities.add(city);
        }
        cursor.close();
        return cities;
    }
}
