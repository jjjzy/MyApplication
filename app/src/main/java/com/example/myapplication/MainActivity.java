package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //jjjj
    EditText username;
    EditText password;
    Button btn;
    Button btn2;
    DatabaseHelper db;
    Button reset;
    Button vendorLogin;
    Button vendorRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username3);
        password = (EditText) findViewById(R.id.passwordEditText);
        btn = (Button) findViewById(R.id.btn);
        btn2 = (Button) findViewById(R.id.btn2);
        db = new DatabaseHelper(this);
        reset = (Button) findViewById(R.id.reset);
        vendorLogin = (Button) findViewById(R.id.vendorLogin);
        vendorRegister = (Button) findViewById(R.id.vendorRegister);

        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(MainActivity.this,UserRegister.class);
                startActivity(in);
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(MainActivity.this,ResetPassword.class);
                startActivity(in);
            }
        });

        vendorLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(MainActivity.this,VendorLogin.class);
                startActivity(in);
            }
        });

        vendorRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(MainActivity.this,VendorRegister.class);
                startActivity(in);
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String s = username.getText().toString();
                String p = password.getText().toString();
                Boolean verify = db.checkInfo(s,p); //check if the username and password matche
                if (verify==true) {
                    Toast.makeText(getApplicationContext(),"Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(MainActivity.this,FrontPage.class);
                    startActivity(in);

                }
                else Toast.makeText(getApplicationContext(), "Wrong username/password",Toast.LENGTH_SHORT).show();

            }
        });




    }
}
