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

public class UserRegister extends AppCompatActivity {

    EditText fname;
    EditText email;
    EditText username_register;
    EditText psw;
    EditText psw2;
    Button register;
    DatabaseHelper db;
    EditText answer;
    EditText address;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        fname=(EditText) findViewById(R.id.fname);
        email=(EditText) findViewById(R.id.lname);
        username_register = (EditText) findViewById(R.id.id1);
        psw = (EditText) findViewById(R.id.psw1);
        psw2 = (EditText) findViewById(R.id.psw2);
        register = (Button) findViewById(R.id.button);
        db = new DatabaseHelper(this);
        answer = (EditText) findViewById(R.id.answer);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);


       register.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view){

               //strings from text objects on the screen
               String s = fname.getText().toString();  //fullname
               String s0= email.getText().toString();    //email
               String s1 = username_register.getText().toString();//username
               String s2 = psw.getText().toString();  //password
               String s3 = psw2.getText().toString();   //confirm password
               String s4= answer.getText().toString();     //answer to the security question
               String s5 = address.getText().toString();  //address
               String s6 = phone.getText().toString();   //phone

               //check if the boxes are empty
               if (s.equals("") || s0.equals(" ") || s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")|| s5.equals("") || s6.equals("")){
                   Toast.makeText(getApplicationContext(),"Please fill out all blanks", Toast.LENGTH_SHORT).show();

               } else{  //if not, then check if psw and confirm psw are the same
                   if (s2.equals(s3)){
                       Boolean checkUsrname = db.checkUsername(s1);
                       if(checkUsrname==true){
                           Boolean insert = db.insertUser(s,s0,s1,s2,s4,s5,s6);
                           if (insert==true){
                               Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                               Intent in = new Intent(UserRegister.this ,CustomerLogin.class);
                               startActivity(in);
                           }
                       }
                       else {
                           Toast.makeText(getApplicationContext(),"Username already exists", Toast.LENGTH_SHORT).show();
                       }
                   }
                   else
                   Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();
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

}

