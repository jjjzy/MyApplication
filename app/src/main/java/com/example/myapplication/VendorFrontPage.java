package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        acceptService = (Button) findViewById(R.id.requestVendor);
        historyVendor = (Button) findViewById(R.id.historyVendor);
        settingsVendor = (Button) findViewById(R.id.SettingsVendor);
        logoutVednor = (Button) findViewById(R.id.logoutVendor);

        acceptService.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorFrontPage.this,VendorAcceptService.class);
                startActivity(in);
            }
        });
        historyVendor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorFrontPage.this,VendorHistory.class);
                startActivity(in);
            }
        });

        settingsVendor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorFrontPage.this,Settings.class);
                startActivity(in);
            }
        });
        logoutVednor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent in = new Intent(VendorFrontPage.this,MainActivity.class);
                startActivity(in);
            }
        });
    }
}
