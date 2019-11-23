package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Request_time_total extends AppCompatActivity {
    String service_selected;
    String vendor_username;
    String user_username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_time_total);

        service_selected = Request_Service_activity.selected_text.toString();
        Log.d("Creation", "service selected: ");
        Log.d("Creation", service_selected);

        user_username = CustomerLogin.s.toString();
        Log.d("Creation", "user username: ");
        Log.d("Creation", user_username);

        vendor_username = View_vendors.vendor_username_selected;
        Log.d("Creation", "vendor username selected: ");
        Log.d("Creation", vendor_username);
    }
}
