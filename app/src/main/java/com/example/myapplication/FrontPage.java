package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FrontPage extends AppCompatActivity {
    Button request_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        request_button = (Button) findViewById(R.id.request);

        request_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(FrontPage.this,Request_service_activity.class);
                startActivity(in);
            }
        });
    }
}
