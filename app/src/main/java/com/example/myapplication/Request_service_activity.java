package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Request_service_activity extends AppCompatActivity {

    Spinner services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service_activity);

        services = (Spinner) findViewById(R.id.services);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Request_service_activity.this, android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.names));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        services.setAdapter(adapter);


    }
}
