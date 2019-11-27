package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class History extends AppCompatActivity {

    LinearLayout ll;
    DatabaseHelper db;

    String current_user_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ll = (LinearLayout) findViewById(R.id.ll_history);
        db = new DatabaseHelper(this);

        current_user_username = CustomerLogin.s;

        Cursor cursor = db.retrive_order_hist_basedon_username(current_user_username);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Button order_hist_button = new Button(this);
            String text_to_display_on_button = "Service: " + cursor.getString(cursor.getColumnIndex("service")) + "        Vendor: " + db.search_company_name_based_on_vendor_username(cursor.getString(cursor.getColumnIndex("vendor_username")));
            order_hist_button.setText(text_to_display_on_button);

            ll.addView(order_hist_button);
        }
    }
}
