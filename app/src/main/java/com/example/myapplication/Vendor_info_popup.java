package com.example.myapplication;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Vendor_info_popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_info_popup);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -50;
        params.height = 300;
        params.width = 400;
        params.y = -50;

        this.getWindow().setAttributes(params);
    }
}
