package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    Button cash;
    Button card;
    public static String method;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        db = new DatabaseHelper(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        cash = (Button) findViewById(R.id.Cash);
        card = (Button) findViewById(R.id.CreditCard);

        final Cursor cursor = db.return_vendor(Request_Service_activity.selected_text.toString());
        cursor.moveToFirst();
        for(int i = 0; i < View_vendors.index; i++){
            cursor.moveToNext();
        }

//        vendor_username = cursor.getString(cursor.getColumnIndex("username"));
        final String date = Request_time_total.yearFinal + "/" + Request_time_total.monthFinal + "/" + Request_time_total.dayFinal + "/" + Request_time_total.hourFinal + "/" + Request_time_total.minuteFinal;

        cash.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                method = (String)cash.getText();
//                Boolean insert = db.insertOrder(CustomerLogin.s.toString(),
//                        cursor.getString(cursor.getColumnIndex("username")),
//                        Request_Service_activity.selected_text.toString(),
//                        date,
//                        cursor.getDouble(cursor.getColumnIndex("price")),"Pending",
//                        method);
                Intent in = new Intent(Payment.this,Confirmation.class);
                startActivity(in);
            }
        });

        card.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                method = (String)card.getText();
                Intent in = new Intent(Payment.this,CreditCard.class);
                startActivity(in);
            }
        });
    }
}
