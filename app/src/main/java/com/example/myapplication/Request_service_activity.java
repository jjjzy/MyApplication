package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Request_service_activity extends AppCompatActivity {

    MainActivity ma;

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service_activity);

        Log.d("Creation", "www");
        Log.d("Creation", MainActivity.s);

        db = new DatabaseHelper(this);

        String x = db.get_firstname(MainActivity.s);

        Log.d("Creation", x);

    }
}
