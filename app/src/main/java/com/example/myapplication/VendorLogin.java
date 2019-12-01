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

public class VendorLogin extends AppCompatActivity {

    EditText vendorLogin;
    EditText vendorLoginPassword;
    Button login;
    Button resetPsw;
    DatabaseHelper db;
    Button venreg;

    public static String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        vendorLogin = (EditText) findViewById(R.id.vendorUsername2);
        vendorLoginPassword=(EditText) findViewById(R.id.vendorPsw3);
        login = (Button) findViewById(R.id.vendorLogin);
        resetPsw = (Button) findViewById(R.id.resetPsw2);
        db = new DatabaseHelper(this);
        venreg = (Button) findViewById(R.id.venreg);

        resetPsw.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorLogin.this,VendorReset.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_up);
            }
        });

        venreg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorLogin.this,VendorRegister.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_up);
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                n = vendorLogin.getText().toString();
                String p = vendorLoginPassword.getText().toString();
                Boolean verify = db.checkVendorInfo(n,p); //check if the username and password matche
                if (verify==true) {
                    Toast.makeText(getApplicationContext(),"Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(VendorLogin.this, VendorFrontPage.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_up);
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
