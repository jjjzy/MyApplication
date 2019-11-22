package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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

//        Toast.makeText(View_vendors.this, selected_text, Toast.LENGTH_SHORT).show();
//        Log.d("Creation", selected_text.toString());

        Cursor cursor = db.return_vendor(selected_text.toString());

        cursor.moveToFirst();

//        Log.d("Creation", "begincout");

        if (cursor.moveToFirst()){
//            Log.d("Creation", "inside if");
            while(!cursor.isAfterLast()){
//                Log.d("Creation", "inside while");
                String data = cursor.getString(cursor.getColumnIndex("username"));
                vendor_list.add(data);
//                Log.d("Creation", data);
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
//            ll.addView(btn);
            ll.addView(btn, params);
//            Log.d("Creation", vendor_list.get(i));
        }
    }
}
