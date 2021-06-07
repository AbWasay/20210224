package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public void insert (SQLiteDatabase db,String title, String content,String id)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("content",content);
        if (id == null)
        {
            db.insert("notes",null,contentValues);
        }
        else
        {
            db.update("notes",contentValues,"_id=?",new String[]{id});
        }

    }

    public void delete (String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from notes",new String[]{});
        cursor.moveToFirst();
        for (int i=1; i < Integer.valueOf(id);i++)
        {
            cursor.moveToNext();
        }
        id = cursor.getString(0);
        db.delete("notes","_id=?",new String[]{id});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
