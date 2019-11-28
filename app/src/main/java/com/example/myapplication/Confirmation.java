package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Confirmation extends AppCompatActivity {

    TextView confirmation;
    String service_selected;
    String vendor_username;
    String user_username;
    DatabaseHelper db;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        home = (Button) findViewById(R.id.home);
        db = new DatabaseHelper(this);
        confirmation = (TextView) findViewById(R.id.confirmation);
        service_selected = Request_Service_activity.selected_text.toString();
        Log.d("Creation", "service selected: ");
        Log.d("Creation", service_selected);

        user_username = CustomerLogin.s.toString();
        Log.d("Creation", "user username: ");
        Log.d("Creation", user_username);

        final Cursor cursor = db.return_vendor(service_selected);
        cursor.moveToFirst();
        for(int i = 0; i < View_vendors.index; i++){
            cursor.moveToNext();
        }

        vendor_username = cursor.getString(cursor.getColumnIndex("username"));
        Log.d("Creation", "vendor username selected: ");
        Log.d("Creation", vendor_username);

        confirmation.setText(
                "Username: " + user_username + "\n" +"\n" +
                        "Service: " + service_selected + "\n" +"\n" +
                        "Vendor: " + cursor.getString(cursor.getColumnIndex("company"))+ "\n"+"\n" +
                        "Date: " + Request_time_total.monthFinal + "/" +
                        Request_time_total.dayFinal + "/" +
                        Request_time_total.yearFinal + "\n" +"\n" +
                        "Time: " + Request_time_total.hourFinal + ":" +
                        Request_time_total.minuteFinal + "\n"+"\n" +
                        "Payment method: " + Payment.method + "\n"

        );


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Confirmation.this,FrontPage.class);
                startActivity(in);
            }
        });
    }
}