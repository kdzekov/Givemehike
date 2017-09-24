package com.example.kdzekov.helloworld;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KDzekov on 9/10/2017.
 */

public class SqlDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GiveMeHikeDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_COUNTRIES =
            "CREATE TABLE " + TableCountries.CountryEntry.TABLE_NAME + " (" +
                    TableCountries.CountryEntry._ID + " INTEGER PRIMARY KEY," +
                    TableCountries.CountryEntry.CODE + " TEXT," +
                    TableCountries.CountryEntry.NAME + " TEXT)";


    private static final String SQL_CREATE_TABLE_MOUNTAINS =
            "CREATE TABLE " + TableMountains.MountainEntry.TABLE_NAME + " (" +
                    TableMountains.MountainEntry._ID + " INTEGER PRIMARY KEY," +
                    TableMountains.MountainEntry.COUNTRY_CODE + " TEXT," +
                    TableMountains.MountainEntry.CODE + " TEXT," +
                    TableMountains.MountainEntry.NAME + " TEXT)";


    private static final String SQL_DELETE_COUNTRIES =
            "DROP TABLE IF EXISTS " + TableCountries.CountryEntry.TABLE_NAME;


    private static final String SQL_DELETE_MOUNTAINS =
            "DROP TABLE IF EXISTS " + TableMountains.MountainEntry.TABLE_NAME;


    public SqlDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_COUNTRIES);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOUNTAINS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_MOUNTAINS);
        sqLiteDatabase.execSQL(SQL_DELETE_COUNTRIES);
        onCreate(sqLiteDatabase);
    }

    public long insertCountry(String code, String name){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableCountries.CountryEntry.CODE, code);
        values.put(TableCountries.CountryEntry.NAME, name);
        long newRowId = db.insert(TableCountries.CountryEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    public long insertMountains(String code, String ccode, String name){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableMountains.MountainEntry.CODE, code);
        values.put(TableMountains.MountainEntry.COUNTRY_CODE, ccode);
        values.put(TableMountains.MountainEntry.NAME, name);
        long newRowId = db.insert(TableMountains.MountainEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    /*public void getData() {
        public String[] getAppCategorydetail() {
            String Table_Name="cytaty";

            String selectQuery = "SELECT  * FROM " + Table_Name;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            String[] data = null;
            if (cursor.moveToFirst()) {
                do {
                    // get  the  data into array,or class variable
                } while (cursor.moveToNext());
            }
            db.close();
            return data;
        }

    }*/

    public List<String> getMountainList(String countryCode){
        //String[] mountainsArray = new String

        List<String> mountains = new ArrayList<String>();
        String selectQuery = "SELECT  name FROM " + TableMountains.MountainEntry.TABLE_NAME+" WHERE country_code='"+countryCode+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                mountains.add(cursor.getString(0));
                // get  the  data into array,or class variable
            } while (cursor.moveToNext());
        }
        db.close();
        return mountains;
    }
}
