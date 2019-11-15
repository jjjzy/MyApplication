package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VendorRegister extends AppCompatActivity {

     EditText vendorName;
     EditText vendorAddress;
     EditText vendorPhone;
     Spinner services;
     EditText vendorUsername;
     EditText vendorPsw;
     EditText vendorPsw2;
     Button register;
     DatabaseHelper db;
    EditText answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_register);
        services = (Spinner) findViewById(R.id.services);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(VendorRegister.this, android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.names));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        services.setAdapter(adapter);

        vendorName = (EditText) findViewById(R.id.vendorName);
        vendorAddress = (EditText) findViewById(R.id.vendorAddress);
        vendorPhone = (EditText) findViewById(R.id.vendorPhone);
        vendorUsername = (EditText) findViewById(R.id.vendorUsername);
        vendorPsw = (EditText) findViewById(R.id.vendorPsw);
        vendorPsw2 = (EditText) findViewById(R.id.vendorPsw2);
        register = (Button) findViewById(R.id.submitVendor);
        db = new DatabaseHelper(this);
        answer = (EditText) findViewById(R.id.answer3);

        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String s = vendorName.getText().toString();
                String s1 = vendorAddress.getText().toString();
                String s2 = vendorPhone.getText().toString();
                String s3 = vendorUsername.getText().toString();
                String s4 = vendorPsw.getText().toString();
                String s5 = vendorPsw2.getText().toString();
                String s6 = services.getSelectedItem().toString();
                String s7 = answer.getText().toString();

                if (s.equals("") || s1.equals(" ") || s2.equals("") || s3.equals("") || s4.equals("")|| s5.equals("") || s6.equals("") || s7.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill out all blanks", Toast.LENGTH_SHORT).show();

                }
                else {  //if not, then check if psw and confirm psw are the same
                    if (s4.equals(s5)) {
                        Boolean checkUsrname = db.checkVendorUsername(s3);
                        if (checkUsrname == true) {
                            Boolean insert = db.insertVendor(s3, s4, s7, s1, s2, s, s6);
                            if (insert == true)
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(VendorRegister.this ,VendorLogin.class);
                            startActivity(in);

                        } else
                            Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();


                }

            }
        });



    }
}
