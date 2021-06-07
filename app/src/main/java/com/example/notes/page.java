package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class page extends AppCompatActivity {

    EditText editText,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        if (id == null)
        {
            editText.setHint("Title");
            editText2.setHint("Enter Text");
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
            editText.setText(cursor.getString(1));
            editText2.setText(cursor.getString(2));
        }
    }

    public void save(View view) {

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        if (id != null)
        {
            Cursor cursor = db.rawQuery("select * from notes",new String[]{});
            cursor.moveToFirst();
            for (int i=1; i < Integer.valueOf(id);i++)
            {
                cursor.moveToNext();
            }

            id = cursor.getString(0);
        }

        helper.insert(db,editText.getText().toString(),editText2.getText().toString(),id);

        startActivity(new Intent(this,MainActivity.class));
    }
}