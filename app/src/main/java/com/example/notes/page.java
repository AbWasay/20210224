package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        Intent intent = getIntent();
        intent.getStringExtra("id");
        Log.d("UFC",intent.getStringExtra("id"));

    }

    public void save(View view) {
    }
}