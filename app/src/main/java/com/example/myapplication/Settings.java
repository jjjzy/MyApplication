package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Switch switch1;
    Switch switch2;
    Switch switch3;

    private void mute() {
        //mute audio
        AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
    }

    public void unmute() {
        //unmute audio
        AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        switch1 = (Switch) findViewById(R.id.switch1);

        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        switch1.setChecked(sharedPreferences.getBoolean("value", true));

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == true){
                    //Toast.makeText(getBaseContext(), "On",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    switch1.setChecked((true));

                    unmute();
                }
                else {
                    //Toast.makeText(getBaseContext(), "Off", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    switch1.setChecked(false);

                    mute();
                }
            }
        });





        switch2 = (Switch) findViewById(R.id.switch2);
        switch2.setChecked(sharedPreferences.getBoolean("value2", true));
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    //Toast.makeText(getBaseContext(), "On",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value2",true);
                    editor.apply();
                    switch2.setChecked((true));
                }
                else{
                    //Toast.makeText(getBaseContext(),"Off",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value2",false);
                    editor.apply();
                    switch2.setChecked(false);
                }
            }
        });

        switch3 = (Switch) findViewById(R.id.switch3);
        switch3.setChecked(sharedPreferences.getBoolean("value3", true));
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    //Toast.makeText(getBaseContext(), "On",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value3",true);
                    editor.apply();
                    switch3.setChecked((true));
                }
                else {
                    //Toast.makeText(getBaseContext(),"Off",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value3",false);
                    editor.apply();
                    switch3.setChecked(false);
                }
            }
        });

    }
}
