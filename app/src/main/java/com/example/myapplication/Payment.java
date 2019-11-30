package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    Button cash;
    Button card;
    public static String method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        cash = (Button) findViewById(R.id.Cash);
        card = (Button) findViewById(R.id.CreditCard);


        cash.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                method = (String)cash.getText();
               Intent in = new Intent(Payment.this,Confirmation.class);
                startActivity(in);
            }
        });

        card.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                method = (String)card.getText();
                Intent in = new Intent(Payment.this,CreditCard.class);
                startActivity(in);
            }
        });
    }
}
