package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class View_vendors extends AppCompatActivity {

    DatabaseHelper db;

    CharSequence selected_text;

    Request_Service_activity ra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vendors);

        selected_text = Request_Service_activity.selected_text;

        db = new DatabaseHelper(this);

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
                Log.d("Creation", data);
                cursor.moveToNext();
            }
        }
        cursor.close();
    }
}
