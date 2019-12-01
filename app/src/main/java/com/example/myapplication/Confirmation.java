package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

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

        Log.d("Creation", "inside confimation");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        home = (Button) findViewById(R.id.home);
        db = new DatabaseHelper(this);
        confirmation = (TextView) findViewById(R.id.confirmation);
        service_selected = Request_Service_activity.selected_text.toString();
//        Log.d("Creation", "service selected: ");
//        Log.d("Creation", service_selected);

        user_username = CustomerLogin.s.toString();
//        Log.d("Creation", "user username: ");
//        Log.d("Creation", user_username);

        final Cursor cursor = db.return_vendor(service_selected);
        cursor.moveToFirst();
        for(int i = 0; i < View_vendors.index; i++){
            cursor.moveToNext();
        }

        vendor_username = cursor.getString(cursor.getColumnIndex("username"));
//        Log.d("Creation", "vendor username selected: ");
//        Log.d("Creation", vendor_username);

        double before_points = Double.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex("price"))));
        final double amount_after_points = before_points -
                Double.valueOf(Integer.parseInt(db.retrive_points_based_on_username(CustomerLogin.s.toString()))) / 100;

        if(Payment.method.equals("Cash") ){
            confirmation.setText(
                    "Username: " + user_username + "\n" +"\n" +
                            "Service: " + service_selected + "\n" +"\n" +
                            "Vendor: " + cursor.getString(cursor.getColumnIndex("company"))+ "\n"+"\n" +
                            "Date: " + Request_time_total.monthFinal + "/" +
                            Request_time_total.dayFinal + "/" +
                            Request_time_total.yearFinal + "\n" +"\n" +
                            "Time: " + Request_time_total.hourFinal + ":" +
                            Request_time_total.minuteFinal + "\n"+"\n" +
                            "Payment method: " + Payment.method + "\n" + "\n" +
                            "You should pay: " + before_points + "\n"
            );
        }
        else if(Payment.method.equals("CreditCard")){
            if(CreditCard.want_to_use_points == true){
                confirmation.setText(
                        "Username: " + user_username + "\n" +"\n" +
                                "Service: " + service_selected + "\n" +"\n" +
                                "Vendor: " + cursor.getString(cursor.getColumnIndex("company"))+ "\n"+"\n" +
                                "Date: " + Request_time_total.monthFinal + "/" +
                                Request_time_total.dayFinal + "/" +
                                Request_time_total.yearFinal + "\n" +"\n" +
                                "Time: " + Request_time_total.hourFinal + ":" +
                                Request_time_total.minuteFinal + "\n"+"\n" +
                                "Payment method: " + Payment.method + "\n" + "\n" +
                                "You should pay: " + before_points + "\n" + "\n" +
                                "After using points you pay: " + amount_after_points + "\n" + "\n" +
                                "Your points before using points: " + db.retrive_points_based_on_username(CustomerLogin.s.toString()) + "\n" + "\n" +
                                "Your points after using points: " + Integer.parseInt(cursor.getString(cursor.getColumnIndex("price")))
                );
            }
            else{
                confirmation.setText(
                        "Username: " + user_username + "\n" +"\n" +
                                "Service: " + service_selected + "\n" +"\n" +
                                "Vendor: " + cursor.getString(cursor.getColumnIndex("company"))+ "\n"+"\n" +
                                "Date: " + Request_time_total.monthFinal + "/" +
                                Request_time_total.dayFinal + "/" +
                                Request_time_total.yearFinal + "\n" +"\n" +
                                "Time: " + Request_time_total.hourFinal + ":" +
                                Request_time_total.minuteFinal + "\n"+"\n" +
                                "Payment method: " + Payment.method + "\n" + "\n" +
                                "You should pay: " + before_points + "\n" + "\n" +
                                "Your points after payment: " + (Integer.parseInt(db.retrive_points_based_on_username(CustomerLogin.s.toString())) + Integer.parseInt(cursor.getString(cursor.getColumnIndex("price"))))
                );
            }

        }

//        confirmation.setText(
//
//                "Username: " + user_username + "\n" +"\n" +
//                        "Service: " + service_selected + "\n" +"\n" +
//                        "Vendor: " + cursor.getString(cursor.getColumnIndex("company"))+ "\n"+"\n" +
//                        "Date: " + Request_time_total.monthFinal + "/" +
//                        Request_time_total.dayFinal + "/" +
//                        Request_time_total.yearFinal + "\n" +"\n" +
//                        "Time: " + Request_time_total.hourFinal + ":" +
//                        Request_time_total.minuteFinal + "\n"+"\n" +
//                        "Payment method: " + Payment.method + "\n" +
//                        "You should pay: " + before_points + "\n"
//
//        );



        final String date = Request_time_total.yearFinal + "/" + Request_time_total.monthFinal + "/" + Request_time_total.dayFinal + "/" + Request_time_total.hourFinal + "/" + Request_time_total.minuteFinal;
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
                SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);
                pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
                pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
                Calendar calendar = new GregorianCalendar(pdt);
                Date trialTime = new Date();
                calendar.setTime(trialTime);

                String cur_time = Integer.toString(calendar.get(Calendar.YEAR)) +
                        Integer.toString(calendar.get(Calendar.MONTH)) +
                        Integer.toString(calendar.get(Calendar.DATE)) +
                        Integer.toString(calendar.get(Calendar.HOUR)) +
                        Integer.toString(calendar.get(Calendar.MINUTE)) +
                        Integer.toString(calendar.get(Calendar.SECOND));



//                today.setToNow();
//                Boolean insert = db.insertOrder(CustomerLogin.s.toString(),
//                        cursor.getString(cursor.getColumnIndex("username")),
//                        Request_Service_activity.selected_text.toString(),
//                        date,
//                        cursor.getDouble(cursor.getColumnIndex("price")),"Pending",
//                        Payment.method,
//                        cur_time);
                Log.d("Creation", "submitted");

                if(Payment.method.equals("CreditCard")){
                    if (CreditCard.want_to_save_card == true){
                        if(db.retrive_card_info_based_on_username(CustomerLogin.s.toString()).getCount() == 0){
                            Log.d("Creation", "checked");
                            String str = CreditCard.cardNum.getText().toString();
//                    int fullInt = Integer.parseInt(str);
//                    String first4char = str.substring(0,4);
//
////                    int intForFirst4Char = Integer.parseInt(first4char);
                            Log.d("Creation", str.substring(0,4));
                            Log.d("Creation", str.substring(4,8));
                            Log.d("Creation", str.substring(8,12));
                            Log.d("Creation", str.substring(12,16));
                            Log.d("Creation", CreditCard.cardHolderName.getText().toString());
                            Log.d("Creation", CreditCard.spinnerA.getSelectedItem().toString());
                            Log.d("Creation", CreditCard.spinnerB.getSelectedItem().toString());
                            Log.d("Creation", CreditCard.cvv.getText().toString());
                            Log.d("Creation", CreditCard.zip.getText().toString());
//
//
//
//
                            db.insertCard(CustomerLogin.s.toString(),
                                    str.substring(0,4),
                                    str.substring(4,8),
                                    str.substring(8,12),
                                    str.substring(12,16),
                                    CreditCard.cardHolderName.getText().toString(),
                                    CreditCard.spinnerA.getSelectedItem().toString(),
                                    CreditCard.spinnerB.getSelectedItem().toString(),
                                    CreditCard.cvv.getText().toString(),
                                    CreditCard.zip.getText().toString());
                        }
                        else{
                            Log.d("Creation", "there are card stored");
                            db.delete_card_info_based_on_username(CustomerLogin.s.toString());
                            String str = CreditCard.cardNum.getText().toString();
                            db.insertCard(CustomerLogin.s.toString(),
                                    str.substring(0,4),
                                    str.substring(4,8),
                                    str.substring(8,12),
                                    str.substring(12,16),
                                    CreditCard.cardHolderName.getText().toString(),
                                    CreditCard.spinnerA.getSelectedItem().toString(),
                                    CreditCard.spinnerB.getSelectedItem().toString(),
                                    CreditCard.cvv.getText().toString(),
                                    CreditCard.zip.getText().toString());
                        }

                    }

                    if(CreditCard.want_to_use_points == true){
                        db.update_points(CustomerLogin.s.toString(), cursor.getString(cursor.getColumnIndex("price")));

                        Boolean insert = db.insertOrder(CustomerLogin.s.toString(),
                                cursor.getString(cursor.getColumnIndex("username")),
                                Request_Service_activity.selected_text.toString(),
                                date,
                                cursor.getDouble(cursor.getColumnIndex("price")),"Pending",
                                Payment.method,
                                cur_time,
                                amount_after_points);
                    }
                    if(CreditCard.want_to_use_points == false){
                        int cur_points = Integer.parseInt(db.retrive_points_based_on_username(CustomerLogin.s.toString())) + Integer.parseInt(cursor.getString(cursor.getColumnIndex("price")));
                        String _cur_points = Integer.toString(cur_points);
                        db.update_points(CustomerLogin.s.toString(), _cur_points);

                        Boolean insert = db.insertOrder(CustomerLogin.s.toString(),
                                cursor.getString(cursor.getColumnIndex("username")),
                                Request_Service_activity.selected_text.toString(),
                                date,
                                cursor.getDouble(cursor.getColumnIndex("price")),"Pending",
                                Payment.method,
                                cur_time,
                                cursor.getDouble(cursor.getColumnIndex("price")));
                    }
                }

                if(Payment.method.equals("Cash")){
                    Boolean insert = db.insertOrder(CustomerLogin.s.toString(),
                            cursor.getString(cursor.getColumnIndex("username")),
                            Request_Service_activity.selected_text.toString(),
                            date,
                            cursor.getDouble(cursor.getColumnIndex("price")),"Pending",
                            Payment.method,
                            cur_time,
                            cursor.getDouble(cursor.getColumnIndex("price")));
                }



                Toast.makeText(getApplicationContext(),"Your order is being placed!", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(Confirmation.this,FrontPage.class);
                startActivity(in);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
