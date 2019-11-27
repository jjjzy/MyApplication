package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Request_time_total extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    String service_selected;
    String vendor_username;
    String user_username;

    DatabaseHelper db;

    Button pick_d_t;
    TextView view_d_t;


    int day, month, year, hour, minute;
    public static int dayFinal = 0;
    public static int monthFinal = 0;
    public static int yearFinal = 0;
    public static int hourFinal = 0;
    public static int minuteFinal = 0;

    Button submit_request_button;
    Cursor cursor;

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

        cursor = db.return_vendor(service_selected);
        cursor.moveToFirst();
        for(int i = 0; i < View_vendors.index; i++){
            cursor.moveToNext();
        }

        vendor_username = cursor.getString(cursor.getColumnIndex("username"));
        Log.d("Creation", "vendor username selected: ");
        Log.d("Creation", vendor_username);

        pick_d_t = (Button) findViewById(R.id.d_t_picker);
        view_d_t = (TextView) findViewById(R.id.d_t_viewer);

        pick_d_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Request_time_total.this, Request_time_total.this, year, month, day);
                datePickerDialog.show();
            }
        });


        submit_request_button = (Button) findViewById(R.id.submit_request);

        submit_request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data_time = yearFinal + "/" + monthFinal + "/" + dayFinal + "/" + hourFinal + "/" + minuteFinal;
                Boolean insert = db.insertOrder(user_username, vendor_username, service_selected, data_time, cursor.getDouble(cursor.getColumnIndex("price")),"Pending");
                if((insert == true) && (dayFinal != 0) && (hourFinal != 0)){
                    Intent in = new Intent(Request_time_total.this, Payment.class);
                    startActivity(in);
                }
                else
                    Toast.makeText(getApplicationContext(),"Please choose date/time!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Request_time_total.this, Request_time_total.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;

        view_d_t.setText(
                "Username: " + user_username + "\n" +"\n" +
                        "Service: " + service_selected + "\n" +"\n" +
                        "Vendor: " + cursor.getString(cursor.getColumnIndex("company"))+ "\n"+"\n" +
                        "Date: " + monthFinal + "/" +
                        dayFinal + "/" +
                        yearFinal + "\n" +"\n" +
                        "Time: " + hourFinal + ":" +
                        minuteFinal + "\n"+"\n" );
    }
}


