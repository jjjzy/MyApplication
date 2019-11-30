package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPassword extends AppCompatActivity {
    EditText username2;
    EditText answer2;
    Button submit2;
    DatabaseHelper db;
    EditText newpsw;
    EditText newpsw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        username2 = (EditText) findViewById(R.id.username3);
        answer2 = (EditText) findViewById(R.id.answer3);
        submit2 = (Button) findViewById(R.id.submit3);
        db = new DatabaseHelper(this);
        newpsw = (EditText) findViewById(R.id.newPass);
        newpsw2 = (EditText) findViewById(R.id.confirm);


        submit2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String s = username2.getText().toString();
                String s0 = answer2.getText().toString();
                String s1 = newpsw.getText().toString();
                String s2 = newpsw2.getText().toString();

                //check if boxes are empty
                if (s.equals("") || s0.equals(" ") || s1.equals("") || s2.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill out all blanks", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkUsrname = db.checkUsername(s);  //checks if the username exists
                    if(checkUsrname==true){
                        Toast.makeText(getApplicationContext(),"Username does not exist",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (s1.equals(s2)){
                            Boolean checkMaiden = db.checkMaidenName(s,s0);
                            if (checkMaiden==true)
                            {
                                db.changePassword(s1,s);
                                Toast.makeText(getApplicationContext(),"Password successfully changed",Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(ResetPassword.this ,CustomerLogin.class);
                                startActivity(in);
                            }
                            else Toast.makeText(getApplicationContext(),"Maiden Last name/Username not corect",Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();

                    }

                }
            }

         });
    };
}
