package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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

        final String date = Request_time_total.yearFinal + "/" + Request_time_total.monthFinal + "/" + Request_time_total.dayFinal + "/" + Request_time_total.hourFinal + "/" + Request_time_total.minuteFinal;
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean insert = db.insertOrder(CustomerLogin.s.toString(),
                        cursor.getString(cursor.getColumnIndex("username")),
                        Request_Service_activity.selected_text.toString(),
                        date,
                        cursor.getDouble(cursor.getColumnIndex("price")),"Pending",
                        Payment.method);
                Log.d("Creaation", "submitted");

                if (CreditCard.want_to_save_card == true){
                    Log.d("Creaation", "checked");
//                    String str = CreditCard.cardNum.getText().toString();
//                    int fullInt = Integer.parseInt(str);
//                    String first4char = str.substring(0,4);
//
////                    int intForFirst4Char = Integer.parseInt(first4char);
//                    Log.d("Creation", str.substring(0,4));
//                    Log.d("Creation", str.substring(4,8));
//                    Log.d("Creation", str.substring(8,12));
//                    Log.d("Creation", str.substring(12,16));
//                    Log.d("Creation", CreditCard.cardHolderName.toString());
//                    Log.d("Creation", CreditCard.spinnerA.getSelectedItem().toString());
//                    Log.d("Creation", CreditCard.spinnerB.getSelectedItem().toString());
//                    Log.d("Creation", CreditCard.cvv.getText().toString());
//                    Log.d("Creation", CreditCard.zip.getText().toString());
//
//
//
//
////                    db.insertCard(CustomerLogin.s.toString(),
////                            str.substring(0,4),
////                            str.substring(4,8),
////                            str.substring(8,12),
////                            str.substring(12,16),
////                            CreditCard.cardHolderName.toString(),
////                            CreditCard.spinnerA.getSelectedItem().toString(),
////                            CreditCard.spinnerB.getSelectedItem().toString(),
////                            CreditCard.cvv.getText().toString(),
////                            CreditCard.zip.getText().toString());
                }

                Toast.makeText(getApplicationContext(),"Your order is being placed!", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(Confirmation.this,FrontPage.class);
                startActivity(in);
            }
        });
    }
}
