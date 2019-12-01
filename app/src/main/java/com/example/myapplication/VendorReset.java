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

public class VendorReset extends AppCompatActivity {

    EditText username;
    EditText answer;
    Button submit;
    DatabaseHelper db;
    EditText newpsw;
    EditText cnfnewpsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_reset);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        username= (EditText) findViewById(R.id.username3);
        answer = (EditText) findViewById(R.id.answer3);
        submit = (Button) findViewById(R.id.submit3);
        db = new DatabaseHelper(this);
        newpsw = (EditText) findViewById(R.id.newp);
        cnfnewpsw = (EditText) findViewById(R.id.cofirm);

        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String s = username.getText().toString();
                String s0 = answer.getText().toString();
                String s1 = newpsw.getText().toString();
                String s2 = cnfnewpsw.getText().toString();

                //check if boxes are empty
                if (s.equals("") || s0.equals(" ") || s1.equals("") || s2.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill out all blanks", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkUsrname = db.checkVendorUsername(s);  //checks if the username exists
                    if(checkUsrname==true){
                        Toast.makeText(getApplicationContext(),"Username does not exist",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (s1.equals(s2)){
                            Boolean checkMaiden = db.checkPetName(s,s0);
                            if (checkMaiden==true)
                            {
                                db.changeVendorPassword(s1,s);
                                Toast.makeText(getApplicationContext(),"Password successfully changed",Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(VendorReset.this ,VendorLogin.class);
                                startActivity(in);
                                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_up);
                            }
                            else Toast.makeText(getApplicationContext(),"Maiden Last name/Username not corect",Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();

                    }

                }
            }

        });


    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_bottom);
    }
}
