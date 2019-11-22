package com.example.myapplication;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class View_vendors extends AppCompatActivity {

    DatabaseHelper db;
    CharSequence selected_text;
    Request_Service_activity ra;
    ArrayList<String> vendor_list = new ArrayList<String>();
    LinearLayout ll;

    PopupWindow popUp;
    LinearLayout layout;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vendors);

        selected_text = Request_Service_activity.selected_text;
        db = new DatabaseHelper(this);
        ll = (LinearLayout)findViewById(R.id.ll);
        Cursor cursor = db.return_vendor(selected_text.toString());
        cursor.moveToFirst();

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String data = cursor.getString(cursor.getColumnIndex("company"));
                vendor_list.add(data);
                cursor.moveToNext();
            }
        }
        cursor.close();

        popUp = new PopupWindow(this);
        layout = new LinearLayout(this);

        TextView tv= new TextView(this);
        tv.setText("FUCK POPUP");

        layout.addView(tv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        popUp.setContentView(layout);
        popUp.setOutsideTouchable(true);
        popUp.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams layout_param = getWindow().getAttributes();

        for(int i = 0; i < vendor_list.size(); i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);
            btn.setText(vendor_list.get(i));

            btn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
//                    TextView tv= new TextView(this);
//                    tv.setText("FUCK POPUP");

//                    layout.addView(tv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT));





//                    startActivity(new Intent(View_vendors.this, Vendor_info_popup.class));
                    popUp.showAtLocation(ll, Gravity.CENTER, 10, 10);
//                        popUp.show
                    popUp.update(50, 50, 1500, 600);

                }
            });

            ll.addView(btn, params);
        }
    }
}
