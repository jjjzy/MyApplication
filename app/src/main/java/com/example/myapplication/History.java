package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStructure;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class History extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    LinearLayout ll;
    DatabaseHelper db;

    String current_user_username;
    int position;
    int position2;

    int day, month, year, hour, minute;
    int dayFinal = 0;
    int monthFinal = 0;
    int yearFinal = 0;
    int hourFinal = 0;
    int minuteFinal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ll = (LinearLayout) findViewById(R.id.ll_history);
        db = new DatabaseHelper(this);

        current_user_username = CustomerLogin.s;

        final Cursor cursor = db.retrive_order_hist_basedon_username(current_user_username);
        final Cursor cursor2 = db.retrive_order_hist_basedon_username(current_user_username);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Button order_hist_button = new Button(this);

            final PopupWindow popUp;
            final LinearLayout layout;
            popUp = new PopupWindow(this);

//            final Cursor cursor = db.return_vendor(service_selected);
//            cursor.moveToFirst();
//            for(int i = 0; i < View_vendors.index; i++){
//                cursor.moveToNext();
//            }

            popUp.setOutsideTouchable(true);
            popUp.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            layout = new LinearLayout(this);

            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);


            layout.setOrientation(LinearLayout.VERTICAL);

            TextView text_to_be_shown = new TextView(this);
            text_to_be_shown.setText(
                    "Service: " + cursor.getString(cursor.getColumnIndex("service")) + "\n" +
                    "Company: " + db.search_company_name_based_on_vendor_username(cursor.getString(cursor.getColumnIndex("vendor_username"))) + "\n" +
                            "Date: " + change_time_format(cursor.getString(cursor.getColumnIndex("date")))[0] + "\n" +
                            "Time: " + change_time_format(cursor.getString(cursor.getColumnIndex("date")))[1] + "\n" +
                            "Status: " + cursor.getString(cursor.getColumnIndex("status")) + "\n" +
                            "Price: " + cursor.getString(cursor.getColumnIndex("total")) + "\n" +
                            "Payment Method: " + cursor.getString(cursor.getColumnIndex("payment_method"))
            );
            text_to_be_shown.setMaxLines(1);
            text_to_be_shown.setEllipsize(TextUtils.TruncateAt.END);
            text_to_be_shown.setTextSize(30);
            text_to_be_shown.setTextColor(Color.rgb(0,0,0));
//            company_name.setGravity(View.TEXT_ALIGNMENT_CENTER);
            text_to_be_shown.setSingleLine(false);

            layout.addView(text_to_be_shown);

            if(cursor.getString(cursor.getColumnIndex("status")).equals("Pending") ||
                    cursor.getString(cursor.getColumnIndex("status")).equals("Accepted")){

                    Log.d("Creation", "this is pending");
                    final Button cancel_order = new Button(this);
                    cancel_order.setText("Cancel this order");
                    cancel_order.setId(cursor.getPosition());

                    cancel_order.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            position = cancel_order.getId();
                            cursor2.moveToFirst();
                            for(int i = 0; i < position; i++){
                                cursor2.moveToNext();
                            }

                            db.change_status_to_cancel("Cancel", cursor2.getString(cursor2.getColumnIndex("user_username")),
                                    cursor2.getString(cursor2.getColumnIndex("vendor_username")),
                                    cursor2.getString(cursor2.getColumnIndex("status")), cursor2.getString(cursor2.getColumnIndex("date")));

                            startActivity(new Intent(History.this, History.class));
                        }
                    });

                    layout.addView(cancel_order);



                final Button change_order = new Button(this);
                change_order.setText("change this order");
                change_order.setId(cursor.getPosition());
                change_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position = change_order.getId();
//                        cursor2.moveToFirst();
//                        for(int i = 0; i < position; i++){
//                            cursor2.moveToNext();
//                        }
                        Calendar calendar = Calendar.getInstance();
                        year = calendar.get(Calendar.YEAR);
                        month = calendar.get(Calendar.MONTH);
                        day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(History.this, History.this, year, month, day);
                        datePickerDialog.show();
                    }
                });


                layout.addView(change_order);
            }




            popUp.setContentView(layout);


            order_hist_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popUp.showAtLocation(ll, Gravity.CENTER, 0, 0);
//                        popUp.show
                    popUp.update(1000, params.WRAP_CONTENT);
                }
            });

            String text_to_display_on_button = "Service: " + cursor.getString(cursor.getColumnIndex("service")) + "        Vendor: " + db.search_company_name_based_on_vendor_username(cursor.getString(cursor.getColumnIndex("vendor_username")));
            order_hist_button.setText(text_to_display_on_button);

            ll.addView(order_hist_button);
        }
    }

    public String[] change_time_format(String old_time){
        String[] arrOfStr = old_time.split("/", 10);
        String new_date = arrOfStr[0] + "/" + arrOfStr[1] + "/" + arrOfStr[2];
        String new_time = arrOfStr[3] + ":" + arrOfStr[4];
        String [] Arr = new String[2];
        Arr[0] = new_date;
        Arr[1] = new_time;
        return Arr;
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



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(History.this, History.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;
        String data_time = yearFinal + "/" + monthFinal + "/" + dayFinal + "/" + hourFinal + "/" + minuteFinal;
        final Cursor cursor3 = db.retrive_order_hist_basedon_username(current_user_username);
        cursor3.moveToFirst();
        for(int i = 0; i < position; i++){
            cursor3.moveToNext();
        }
        db.change_order_time(data_time, cursor3.getString(cursor3.getColumnIndex("user_username")),
                cursor3.getString(cursor3.getColumnIndex("vendor_username")),
                cursor3.getString(cursor3.getColumnIndex("status")), cursor3.getString(cursor3.getColumnIndex("date")));

        startActivity(new Intent(History.this, History.class));

    }
}
