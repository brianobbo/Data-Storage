package com.example.datastorage.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    ///create Database Name and Table
    public static final String DATABASE_NAME = "Student_db";
    public static final String TABLE_NAME = "Student_table";

    //Define your columns
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
///query to create table
        db.execSQL("CREATE TABLE " +TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//query to drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
    }

    //insert method to insert values in the database
    public boolean insertData(String name, String surName, String marks){
        //create the database using getWritable() method
        SQLiteDatabase db = this.getWritableDatabase();

        //create an instance of contentValues and use put method to store values in the object
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surName);
        contentValues.put(COL_4,marks);

        //insert data using in th table using the method of the SQLiteDataBase instance
        //null is for in case of empty row
        long results = db.insert(TABLE_NAME,null,contentValues);

        //check if data is inserted in the database
if (results == -1){
    return false;
}else
    return true;
    }

    //read method to read data with return type cursor
    public Cursor getAllData(){
        //open the data for reading data in the table using getWritableData
        SQLiteDatabase db = this.getWritableDatabase();
        //query for reading values
        Cursor cursor =  db.rawQuery("SELECT * FROM " +TABLE_NAME,null);
        return cursor;
    }
}
