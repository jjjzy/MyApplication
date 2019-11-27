package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

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
                            "Price: " + cursor.getString(cursor.getColumnIndex("total"))
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
                        db.change_status_to_cancel("Cancel", cursor2.getString(cursor.getColumnIndex("user_username")),
                                cursor2.getString(cursor2.getColumnIndex("vendor_username")),
                                cursor2.getString(cursor2.getColumnIndex("status")), cursor2.getString(cursor2.getColumnIndex("date")));
                    }
                });
                layout.addView(cancel_order);



                final Button change_order = new Button(this);
                change_order.setText("change this order");
                change_order.setId(cursor.getPosition());
                change_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        position = change_order.getId();
//                        cursor2.moveToFirst();
//                        for(int i = 0; i < position; i++){
//                            cursor2.moveToNext();
//                        }
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
                    popUp.update(params.WRAP_CONTENT, params.WRAP_CONTENT);
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}
