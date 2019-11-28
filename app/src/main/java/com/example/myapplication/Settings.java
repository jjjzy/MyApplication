package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Switch switch1;
    Switch switch2;
    Switch switch3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    Toast.makeText(getBaseContext(), "On",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getBaseContext(),"Off",Toast.LENGTH_SHORT).show();
            }
        });

        switch2 = (Switch) findViewById(R.id.switch1);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    Toast.makeText(getBaseContext(), "On",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getBaseContext(),"Off",Toast.LENGTH_SHORT).show();
            }
        });

        switch3 = (Switch) findViewById(R.id.switch1);
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    Toast.makeText(getBaseContext(), "On",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getBaseContext(),"Off",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
