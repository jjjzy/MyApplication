package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Request_Service_activity extends AppCompatActivity {

    Button sbmt_button;
    RadioGroup rdio_grp;
    RadioButton selected_btn;

    public static CharSequence selected_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__service_activity);

        sbmt_button = (Button) findViewById(R.id.sbmt);
        rdio_grp = (RadioGroup) findViewById(R.id.rdiogrp);
//        RadioButton selected_btn;

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
