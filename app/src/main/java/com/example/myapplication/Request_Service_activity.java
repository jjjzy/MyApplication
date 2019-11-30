package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Request_Service_activity extends AppCompatActivity {

    Button sbmt_button;
    RadioGroup rdio_grp;
    RadioButton selected_btn;
    LinearLayout l_l;

    DatabaseHelper db;

    public static CharSequence selected_text;

    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__service_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Log.d("Creation", "xxx");

//        sv = (SearchView) findViewById(R.id.search_services);

        sbmt_button = (Button) findViewById(R.id.sbmt);
//        rdio_grp = (RadioGroup) findViewById(R.id.rdiogrp);
//        RadioButton selected_btn;
        db = new DatabaseHelper(this);
        rdio_grp = (RadioGroup) findViewById(R.id.rdiogrp);
        l_l = (LinearLayout) findViewById(R.id.l_l);

        Cursor cursor = db.retrive_all_service_caterogies();

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                230);


        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            RadioButton rb = new RadioButton(this);
//            rb.setText("wwer");
            rb.setText(cursor.getString(cursor.getColumnIndex("service_category")));

            Log.d("Creation", cursor.getString(cursor.getColumnIndex("service_category")));
            rdio_grp.addView(rb, params);
        }
//        ll.addView(rdio_grp);

        sv = (SearchView) findViewById(R.id.search_services);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                    for(int i = 0; i < ll.getChildCount(); i++){
//                        Button temp = (Button)ll.getChildAt(i);
//                        temp.setVisibility(View.VISIBLE);
//                        if(!temp.getText().toString().contains(query)){
//                            temp.setVisibility(View.GONE);
//                        }
//                    }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                for(int i = 0; i < rdio_grp.getChildCount(); i++){
                    RadioButton temp = (RadioButton) rdio_grp.getChildAt(i);
                    temp.setVisibility(View.VISIBLE);
                    if(!temp.getText().toString().contains(newText)){
                        temp.setVisibility(View.GONE);
                    }
//                    RadioButton temp = (RadioButton) ll.getChildAt(i);
                }
                return false;
            }
        });

        sv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                for(int i = 0; i < rdio_grp.getChildCount(); i++){
                    RadioButton temp = (RadioButton) rdio_grp.getChildAt(i);
//                        if(temp.getText().equals("penny")){
//                            temp.setVisibility(View.INVISIBLE);
//                        }
                    temp.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });



//        CharSequence selected_text;

        sbmt_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                if (rdio_grp.getCheckedRadioButtonId() == -1) {
//                    Log.d("Creation", "You must select one");
                    Toast.makeText(getApplicationContext(),"Please Select a service", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Log.d("Creation", "all good");

                    selected_btn = (RadioButton) findViewById(rdio_grp.getCheckedRadioButtonId());

                    selected_text = selected_btn.getText();

                    Toast.makeText(Request_Service_activity.this, selected_text, Toast.LENGTH_SHORT).show();

                    Intent in = new Intent(Request_Service_activity.this, View_vendors.class);
                    startActivity(in);
                }
            }
        });


    }
}
