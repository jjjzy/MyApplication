package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Request_time_total extends AppCompatActivity {
    String service_selected;
    String vendor_username;
    String user_username;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_time_total);

        db = new DatabaseHelper(this);


        service_selected = Request_Service_activity.selected_text.toString();
        Log.d("Creation", "service selected: ");
        Log.d("Creation", service_selected);

        user_username = CustomerLogin.s.toString();
        Log.d("Creation", "user username: ");
        Log.d("Creation", user_username);

        Cursor cursor = db.return_vendor(service_selected);
        cursor.moveToFirst();
        for(int i = 0; i < View_vendors.index; i++){
            cursor.moveToNext();
        }

        vendor_username = cursor.getString(cursor.getColumnIndex("username"));
        Log.d("Creation", "vendor username selected: ");
        Log.d("Creation", vendor_username);
    }
}
