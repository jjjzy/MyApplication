package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button userbtn;
    Button vendorbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userbtn = (Button) findViewById(R.id.userbtn);
        vendorbtn=(Button) findViewById(R.id.vendorbtn);

        userbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(MainActivity.this,CustomerLogin.class);
                startActivity(in);
            }
        });

        vendorbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(MainActivity.this,VendorLogin.class);
                startActivity(in);
            }
        });


    }
}
