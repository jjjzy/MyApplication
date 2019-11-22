package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class View_vendors extends AppCompatActivity {

    DatabaseHelper db;
    CharSequence selected_text;
    Request_Service_activity ra;
    ArrayList<String> vendor_list = new ArrayList<String>();
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vendors);

        selected_text = Request_Service_activity.selected_text;
        db = new DatabaseHelper(this);
        ll = (LinearLayout)findViewById(R.id.ll);
        Cursor cursor = db.return_vendor(selected_text.toString());
        cursor.moveToFirst();

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String data = cursor.getString(cursor.getColumnIndex("company"));
                vendor_list.add(data);
                cursor.moveToNext();
            }
        }
        cursor.close();

        for(int i = 0; i < vendor_list.size(); i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);
            btn.setText(vendor_list.get(i));

            btn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
//                    startActivity(new Intent(View_vendors.this, Vendor_info_popup.class));

                }
            });

            ll.addView(btn, params);
        }
    }
}
