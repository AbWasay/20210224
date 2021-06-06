package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class page extends AppCompatActivity {

    EditText textView,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        if (id == null)
        {
            textView.setHint("Title");
            textView2.setHint("Enter Text");
        }
        else
        {
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from notes",new String[]{});
            cursor.moveToFirst();
            for (int i=1; i < Integer.valueOf(id);i++)
            {
                cursor.moveToNext();
            }
            textView.setText(cursor.getString(1));
            textView2.setText(cursor.getString(2));
        }
    }

    public void save(View view) {
    }
}