package com.example.myapplication;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
}
