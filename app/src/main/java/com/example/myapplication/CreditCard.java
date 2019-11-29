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

public class CreditCard extends AppCompatActivity {

    EditText cardNum;
    EditText cardHolderName;
    Spinner spinnerA;
    Spinner spinnerB;
    EditText cvv;
    EditText zip;
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        cardNum = (EditText) findViewById(R.id.cardNum);
        cardHolderName = (EditText) findViewById(R.id.holderName);
        spinnerA = (Spinner) findViewById(R.id.spinnerA);
        spinnerB = (Spinner) findViewById(R.id.spinnerB);
        cvv = (EditText) findViewById(R.id.cvv);
        zip = (EditText) findViewById(R.id.zip);
        pay = (Button) findViewById(R.id.pay);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreditCard.this, android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.months));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerA.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CreditCard.this, android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.years));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerB.setAdapter(adapter2);


        pay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String s = cardNum.getText().toString();
                String s1 = cardHolderName.getText().toString();
                String s2 = spinnerA.getSelectedItem().toString();
                String s3 = spinnerB.getSelectedItem().toString();
                String s4 = cvv.getText().toString();
                String s5 = zip.getText().toString();


                if (s.equals("") || s1.equals(" ") || s2.equals("") || s3.equals("") || s4.equals("")|| s5.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill out all blanks", Toast.LENGTH_SHORT).show();

                }
                else {

                    //check if there are 16 characters in the card number string
                    if(s.length()!= 16){
                        Toast.makeText(getApplicationContext(),"Please enter valid credit card number", Toast.LENGTH_SHORT).show();
                    } else {

                        //check if cvv is valid
                        if (s4.length() != 3){
                            Toast.makeText(getApplicationContext(),"Please enter a valid cvv", Toast.LENGTH_SHORT).show();
                        } else {

                            //check the valid zip code
                            if (s5.length()!=5){
                                Toast.makeText(getApplicationContext(),"Please enter a valid zip code (5 digits)", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Your payment is being proccessed!", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(CreditCard.this ,Confirmation.class);
                                startActivity(in);

                            }
                        }
                    }


                }

            }
        });
    }


}
