package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from notes",new String[]{});

        ArrayList<String> note = new ArrayList<String>();
        if (cursor.moveToNext() != false)
        {

            do
            {
                note.add(cursor.getString(1));
            }while (cursor.moveToNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1,note
            );
            listView.setAdapter(adapter);
        }
        String[] option = {"Open","Delete"};
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) //open
                        {
                            Intent intent = new Intent(MainActivity.this,page.class);
                            int id = position;
                            intent.putExtra("id",++id);
                            startActivity(intent);
                        }
                        else if (which == 1) //edit
                        {
                            DBHelper helper = new DBHelper(MainActivity.this);
                            SQLiteDatabase db = helper.getWritableDatabase();
                            helper.delete(db,position);
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    public void insert(View view)
    {
        Intent intent = new Intent(MainActivity.this,page.class);
        intent.putExtra("id","");
        startActivity(intent);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.insert("This is title","This is data",db);
    }

}