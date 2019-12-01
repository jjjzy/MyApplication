package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class VendorFrontPage extends AppCompatActivity {

    Button acceptService;
    Button historyVendor;
    Button settingsVendor;
    Button logoutVednor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_front_page);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        acceptService = (Button) findViewById(R.id.requestVendor);
        historyVendor = (Button) findViewById(R.id.historyVendor);
        settingsVendor = (Button) findViewById(R.id.SettingsVendor);
        logoutVednor = (Button) findViewById(R.id.logoutVendor);

        acceptService.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorFrontPage.this,VendorAcceptService.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_up);
            }
        });
        historyVendor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorFrontPage.this,VendorHistory.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_up);
            }
        });

        settingsVendor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorFrontPage.this,Settings.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_up);
            }
        });
        logoutVednor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorFrontPage.this,MainActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_up);
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_bottom);
    }
}
