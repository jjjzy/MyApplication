package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class View_vendors extends AppCompatActivity {

    DatabaseHelper db;
    CharSequence selected_text;
    ArrayList<String> vendor_list = new ArrayList<String>();
    ArrayList<String> vendor_username_list = new ArrayList<String>();
    LinearLayout ll;

    public static String vendor_username_selected;

//    PopupWindow popUp;
//    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vendors);

        Log.d("Creation", "www");

        selected_text = Request_Service_activity.selected_text;
        db = new DatabaseHelper(this);
        ll = (LinearLayout)findViewById(R.id.ll);
        final Cursor cursor = db.return_vendor(selected_text.toString());
        cursor.moveToFirst();

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String data = cursor.getString(cursor.getColumnIndex("company"));
                vendor_list.add(data);

                String vendor_username = cursor.getString(cursor.getColumnIndex("username"));
                vendor_username_list.add(vendor_username);

                cursor.moveToNext();
            }
        }
//        cursor.close();

//        popUp = new PopupWindow(this);
//        layout = new LinearLayout(this);

//        TextView tv= new TextView(this);
//        tv.setText("FUCK POPUP");
//
//        layout.addView(tv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));


//        popUp.setContentView(layout);
//        popUp.setOutsideTouchable(true);
//        popUp.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams layout_param = getWindow().getAttributes();

        for(int i = 0; i < vendor_list.size(); i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);
            btn.setText(vendor_list.get(i));

            final PopupWindow popUp;
            final LinearLayout layout;
            popUp = new PopupWindow(this);

            popUp.setOutsideTouchable(true);
            popUp.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);

            TextView company_name= new TextView(this);
            company_name.setText("Company name: " +  vendor_list.get(i) + "\n" + "\n" +
                    "Address: "  + get_address(cursor, vendor_list.get(i)) + "\n" + "\n" +
                    "Phone number: " + get_phone(cursor, vendor_list.get(i)) + "\n" + "\n" +
                    "Email: " + get_email(cursor, vendor_list.get(i)) + "\n" + "\n" +
                    "Price: " + get_price(cursor, vendor_list.get(i)));
            company_name.setTextSize(30);
            company_name.setTextColor(Color.rgb(0,0,0));
//            company_name.setGravity(View.TEXT_ALIGNMENT_CENTER);
            company_name.setSingleLine(false);

            Button select_vd = new Button(this);
//            select_svs.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.WRAP_CONTENT));
//            select_svs.
            select_vd.setText("Select this vendor");
//            vendor_username_selected = vendor_username_list.get(i);
//            final int index = i;
            select_vd.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
//                    vendor_username_selected = get_vendor_username(cursor, vendor_list.get(i));
                    startActivity(new Intent(View_vendors.this, Request_time_total.class));
                }
            });

            layout.addView(company_name, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            layout.addView(select_vd);


//            TextView break_line= new TextView(this);
//            break_line.setText(" \n");
//            break_line.setSingleLine(false);
//
//            layout.addView(break_line, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));
//
//
//            TextView address= new TextView(this);
//            address.setText("Address: ");
//            address.setSingleLine(false);
//
//            layout.addView(address, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));


            popUp.setContentView(layout);


            btn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    popUp.showAtLocation(ll, Gravity.CENTER, 10, 10);
//                        popUp.show
                    popUp.update(50, 50, 1500, 1400);

                }
            });

            ll.addView(btn, params);
        }
    }

    public static String get_address(Cursor c, String company_name){
        c.moveToFirst();
//        Log.d("Creation", "inside if!");
        String address = " ";
        if (c.moveToFirst()){
//            Log.d("Creation", "inside if!");
            while(!c.isAfterLast()){
                Log.d("Creation", "we are at: ");
                Log.d("Creation", c.getString(c.getColumnIndex("company")));
                Log.d("Creation", "parameter is: ");
                Log.d("Creation", company_name);
//                Log.d("Creation", "inside while!");
                if(c.getString(c.getColumnIndex("company")).equals(company_name)){
                    Log.d("Creation", "inside if!");
                    address = c.getString(c.getColumnIndex("address"));
//                    break;
                }
                else{
                    Log.d("Creation", "not equal");
                }
//                String data = c.getString(c.getColumnIndex("company"));
                c.moveToNext();
            }
        }
        return address;
    }

    public static String get_phone(Cursor c, String company_name){
        c.moveToFirst();
//        Log.d("Creation", "inside if!");
        String phone = " ";
        if (c.moveToFirst()){
//            Log.d("Creation", "inside if!");
            while(!c.isAfterLast()){
                Log.d("Creation", "we are at: ");
                Log.d("Creation", c.getString(c.getColumnIndex("company")));
                Log.d("Creation", "parameter is: ");
                Log.d("Creation", company_name);
//                Log.d("Creation", "inside while!");
                if(c.getString(c.getColumnIndex("company")).equals(company_name)){
                    Log.d("Creation", "inside if!");
                    phone = c.getString(c.getColumnIndex("phone"));
//                    break;
                }
                else{
                    Log.d("Creation", "not equal");
                }
//                String data = c.getString(c.getColumnIndex("company"));
                c.moveToNext();
            }
        }
        return phone;
    }

    public static String get_email(Cursor c, String company_name){
        c.moveToFirst();
//        Log.d("Creation", "inside if!");
        String email = " ";
        if (c.moveToFirst()){
//            Log.d("Creation", "inside if!");
            while(!c.isAfterLast()){
                Log.d("Creation", "we are at: ");
                Log.d("Creation", c.getString(c.getColumnIndex("company")));
                Log.d("Creation", "parameter is: ");
                Log.d("Creation", company_name);
//                Log.d("Creation", "inside while!");
                if(c.getString(c.getColumnIndex("company")).equals(company_name)){
                    Log.d("Creation", "inside if!");
                    email = c.getString(c.getColumnIndex("email"));
//                    break;
                }
                else{
                    Log.d("Creation", "not equal");
                }
//                String data = c.getString(c.getColumnIndex("company"));
                c.moveToNext();
            }
        }
        return email;
    }

    public static String get_price(Cursor c, String company_name){
        c.moveToFirst();
//        Log.d("Creation", "inside if!");
        String price = " ";
        if (c.moveToFirst()){
//            Log.d("Creation", "inside if!");
            while(!c.isAfterLast()){
                Log.d("Creation", "we are at: ");
                Log.d("Creation", c.getString(c.getColumnIndex("company")));
                Log.d("Creation", "parameter is: ");
                Log.d("Creation", company_name);
//                Log.d("Creation", "inside while!");
                if(c.getString(c.getColumnIndex("company")).equals(company_name)){
                    Log.d("Creation", "inside if!");
                    price = c.getString(c.getColumnIndex("price"));
//                    break;
                }
                else{
                    Log.d("Creation", "not equal");
                }
//                String data = c.getString(c.getColumnIndex("company"));
                c.moveToNext();
            }
        }
        return price;
    }

    public static String get_vendor_username(Cursor c, String company_name){
        c.moveToFirst();
//        Log.d("Creation", "inside if!");
        String vendor_username = " ";
        if (c.moveToFirst()){
//            Log.d("Creation", "inside if!");
            while(!c.isAfterLast()){
                Log.d("Creation", "we are at: ");
                Log.d("Creation", c.getString(c.getColumnIndex("company")));
                Log.d("Creation", "parameter is: ");
                Log.d("Creation", company_name);
//                Log.d("Creation", "inside while!");
                if(c.getString(c.getColumnIndex("company")).equals(company_name)){
                    Log.d("Creation", "inside if!");
                    vendor_username = c.getString(c.getColumnIndex("vendor_username"));
//                    break;
                }
                else{
                    Log.d("Creation", "not equal");
                }
//                String data = c.getString(c.getColumnIndex("company"));
                c.moveToNext();
            }
        }
        return vendor_username;
    }
}
