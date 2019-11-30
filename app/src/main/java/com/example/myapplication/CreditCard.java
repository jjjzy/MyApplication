package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreditCard extends AppCompatActivity {

    public static EditText cardNum;
    public static EditText cardHolderName;
    public static Spinner spinnerA;
    public static Spinner spinnerB;
    public static EditText cvv;
    public static EditText zip;
    Button pay;

    CheckBox cb;
    public static boolean want_to_save_card;

    Button use_prefer;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        db = new DatabaseHelper(this);


        cb = (CheckBox) findViewById(R.id.checkBox);
        use_prefer = (Button) findViewById(R.id.button2);
//        if(cb.isChecked()){
//            want_to_save_card = true;
//        }
//        use_prefer = (Button) findViewById(R.id.user_prefer);

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

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CreditCard.this, android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.years));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerB.setAdapter(adapter2);

        final Cursor cursor = db.retrive_card_info_based_on_username(CustomerLogin.s.toString());
        cursor.moveToFirst();
        use_prefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardHolderName.setText(cursor.getString(cursor.getColumnIndex("fullname")));
                cvv.setText(cursor.getString(cursor.getColumnIndex("cvv")));
                zip.setText(cursor.getString(cursor.getColumnIndex("zip")));
                String full_card_num = cursor.getString(cursor.getColumnIndex("first")) +
                        cursor.getString(cursor.getColumnIndex("second")) +
                        cursor.getString(cursor.getColumnIndex("third"))+
                        cursor.getString(cursor.getColumnIndex("fourth"));
                cardNum.setText(full_card_num);

                ArrayList<String> month_list = new ArrayList<String>();
                for(int i = 0; i < getResources().getStringArray(R.array.months).length; i++){
                    month_list.add(getResources().getStringArray(R.array.months)[i]);
                }

                ArrayList<String> year_list = new ArrayList<String>();
                for(int i = 0; i < getResources().getStringArray(R.array.years).length; i++){
                    month_list.add(getResources().getStringArray(R.array.years)[i]);
                }



                spinnerA.setSelection(month_list.indexOf(cursor.getString(cursor.getColumnIndex("month"))));
                spinnerB.setSelection(year_list.indexOf(cursor.getString(cursor.getColumnIndex("year"))));

            }
        });


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
//                                Toast.makeText(getApplicationContext(),"Your payment is being proccessed!", Toast.LENGTH_SHORT).show();
                                if(cb.isChecked()){
                                    want_to_save_card = true;
//                                    Log.d("Creation", "inside credit card, cb is checked");
                                }
                                else{
//                                    Log.d("Creation", "inside credit card, cb is not checked");
                                }




                                Intent in = new Intent(CreditCard.this ,Confirmation.class);
                                startActivity(in);

                            }
                        }
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



}
