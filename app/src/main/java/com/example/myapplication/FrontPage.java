package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FrontPage extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;
    Button b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        b1 = (Button) findViewById(R.id.request);
        b2 = (Button) findViewById(R.id.history);
        b3 = (Button) findViewById(R.id.Settings);
        b4 = (Button) findViewById(R.id.logout);
//        b5 = (Button) findViewById(R.id.change_cancel_request);

        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(FrontPage.this,Request_Service_activity.class);
                startActivity(in);
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(FrontPage.this,History.class);
                startActivity(in);
            }
        });

        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(FrontPage.this,Settings.class);
                startActivity(in);
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(FrontPage.this,MainActivity.class);
                startActivity(in);
            }
        });

//        b5.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(new Intent(FrontPage.this, Cancel_change_requests.class));
//            }
//        });

    }
}
