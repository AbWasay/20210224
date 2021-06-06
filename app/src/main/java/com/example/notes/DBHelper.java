package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper (Context context)
    {
        super(context,"myDB",null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "Create table notes (_id Integer Primary Key AutoIncrement, title text, content text)";
        db.execSQL(query);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
