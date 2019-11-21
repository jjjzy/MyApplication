package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class View_vendors extends AppCompatActivity {

    CharSequence selected_text;

    Request_Service_activity ra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vendors);

        selected_text = Request_Service_activity.selected_text;

//        Toast.makeText(View_vendors.this, selected_text, Toast.LENGTH_SHORT).show();
        Log.d("Creation", selected_text.toString());
    }
}
