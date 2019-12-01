package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


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
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(CustomerLogin.this,ResetPassword.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                else Toast.makeText(getApplicationContext(), "Wrong username/password",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
       /* switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);*/
        Intent myIntent = new Intent(getApplicationContext(), VendorFrontPage.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
