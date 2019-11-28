package com.example.myapplication;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class VendorHistory extends AppCompatActivity {

    DatabaseHelper db;
    String current_vendor_username;
    ScrollView sv2;
    LinearLayout ll3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_history);

       /* getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        public boolean onOptionsItemSelected(MenuItem item){
            int id = item.getItemId();

            if (id == android.R.id.home){
                this.finish();
            }

            return super.onOptionsItemSelected(item);
        }*/
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        sv2 = (ScrollView) findViewById(R.id.sv_history2);
        ll3 = (LinearLayout) findViewById(R.id.ll_history2);
        db = new DatabaseHelper(this);
        current_vendor_username = VendorLogin.n;

        Cursor cursor = db.retrive_all_vendor__orders(current_vendor_username);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Button order_hist_button = new Button(this);

            final PopupWindow popUp;
            final LinearLayout layout;
            popUp = new PopupWindow(this);

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
                            "Full name: " + db.get_customer_fullname_based_on_username(cursor.getString(cursor.getColumnIndex("user_username"))) + "\n" +
                            "Date: " + change_time_format(cursor.getString(cursor.getColumnIndex("date")))[0] + "\n" +
                            "Time: " + change_time_format(cursor.getString(cursor.getColumnIndex("date")))[1] + "\n" +
                            "Status: " + cursor.getString(cursor.getColumnIndex("status")) + "\n" +
                            "Price: " + cursor.getString(cursor.getColumnIndex("total"))
            );
            text_to_be_shown.setMaxLines(1);
            text_to_be_shown.setEllipsize(TextUtils.TruncateAt.END);
            text_to_be_shown.setTextSize(30);
            text_to_be_shown.setTextColor(Color.rgb(0, 0, 0));
            text_to_be_shown.setSingleLine(false);

            layout.addView(text_to_be_shown);

            popUp.setContentView(layout);

            order_hist_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popUp.showAtLocation(ll3, Gravity.CENTER, 0, 0);
                    popUp.update(params.WRAP_CONTENT, params.WRAP_CONTENT);
                }
            });

            String text_to_display_on_button = "Date: " + cursor.getString(cursor.getColumnIndex("date")) + "        FullName: " + db.get_customer_fullname_based_on_username(cursor.getString(cursor.getColumnIndex("user_username")));
            order_hist_button.setText(text_to_display_on_button);

            ll3.addView(order_hist_button);

        }


    }

    public String[] change_time_format(String old_time) {
        String[] arrOfStr = old_time.split("/", 10);
        String new_date = arrOfStr[0] + "/" + arrOfStr[1] + "/" + arrOfStr[2];
        String new_time = arrOfStr[3] + ":" + arrOfStr[4];
        String[] Arr = new String[2];
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
}
