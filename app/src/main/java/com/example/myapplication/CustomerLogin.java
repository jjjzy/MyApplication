package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerLogin extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btn;
    Button btn2;
    DatabaseHelper db;
    Button reset;

    public static String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        username = (EditText) findViewById(R.id.username3);
        password = (EditText) findViewById(R.id.passwordEditText);
        btn = (Button) findViewById(R.id.btn);
        btn2 = (Button) findViewById(R.id.btn2);
        db = new DatabaseHelper(this);
        reset = (Button) findViewById(R.id.reset);

        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(CustomerLogin.this,UserRegister.class);
                startActivity(in);
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(CustomerLogin.this,ResetPassword.class);
                startActivity(in);
            }
        });


        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                s = username.getText().toString();
                String p = password.getText().toString();
                Boolean verify = db.checkInfo(s,p); //check if the username and password matche
                if (verify==true) {
                    Toast.makeText(getApplicationContext(),"Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(CustomerLogin.this,FrontPage.class);
                    startActivity(in);

                }
                else Toast.makeText(getApplicationContext(), "Wrong username/password",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
