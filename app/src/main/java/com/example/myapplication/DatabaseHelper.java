package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //constructor
    public DatabaseHelper(Context context){
        super(context,"Login.db",null,6);

    }

    //create a table for customers
    public void onCreate(SQLiteDatabase db){
        db.execSQL("Create table user(fullname text, email text, username text primary key, password text, answer text, address text,phone text)");
        db.execSQL("Create table vendor(username text primary key, password text, answer text, address text,phone text,company text,service text,price text,email text)");
        db.execSQL("Create table orders(user_username text,vendor_username text, service text, date text,total double,status text)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists vendor");
        db.execSQL("drop table if exists orders");
        onCreate(db);
    }

    //insert username and password to the database
    public boolean insertUser(String fullname, String email, String username,String password,String answer,String address, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("email", email);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("answer", answer);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        long ins = db.insert("user", null, contentValues);

        if (ins == -1) return false;   //if the query does not work return false
        else return true;
    }


    public boolean insertOrder(String user_username, String vendor_username, String service,String date,double total, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_username", user_username);
        contentValues.put("vendor_username", vendor_username);
        contentValues.put("service", service);
        contentValues.put("date", date);
        contentValues.put("total", total);
        contentValues.put("status", status);
        long ins = db.insert("orders", null,contentValues);

        if (ins==-1) return false;   //if the query does not work return false
        else return true;
    }

    public boolean insertVendor(String username, String password, String answer,String address,String phone,String company, String service, String price, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("answer", answer);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("company",company);
        contentValues.put("service",service);
        contentValues.put("price",price);
        contentValues.put("email",email);
        long ins = db.insert("vendor", null,contentValues);

        if (ins==-1) return false;   //if the query does not work return false
        else return true;
    }

    //checks if  users' username already exists in the database
    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=?",new String[] {username});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //checks if vendor username already exist in the database
    public Boolean checkVendorUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from vendor where username=?",new String[] {username});
        if (cursor.getCount() > 0) return false;
        else return true;
    }


    //checks if username and password match the info in database
    public Boolean checkInfo(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=? and password=?",new String[] {username,password});
        if(cursor.getCount() > 0 ) return true;
        else return false;
    }

    //checks if username and password match the info in database
    public Boolean checkVendorInfo(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from vendor where username=? and password=?",new String[] {username,password});
        if(cursor.getCount() > 0 ) return true;
        else return false;
    }

    //check if the maiden name match
    public Boolean checkMaidenName(String username, String answer){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=? and answer=?",new String[] {username,answer});
        if(cursor.getCount() > 0 ) return true;
        else return false;
    }

    public Boolean checkPetName(String username, String answer){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from vendor where username=? and answer=?",new String[] {username,answer});
        if(cursor.getCount() > 0 ) return true;
        else return false;
    }

    //change the customers password in the database
    public void changePassword(String newPassword, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPassword);
        String [] args = new String[]{username};
        db.update("user", cv,"username=?",args);
    }

    public void changeVendorPassword(String newPassword, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPassword);
        String [] args = new String[]{username};
        db.update("vendor", cv,"username=?",args);
    }

    public Cursor return_vendor(String username){
//        Log.d("Creation", username);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from vendor where service=?",new String[] {username});
//        if(cursor.getCount() > 0 ) return true;
//        else return false;
        return cursor;
    }

    public Cursor retrive_order_hist_basedon_username(String username){
//        Log.d("Creation", username);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from orders where user_username=?",new String[] {username});
//        if(cursor.getCount() > 0 ) return true;
//        else return false;
        return cursor;
    }

    public String search_company_name_based_on_vendor_username(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from vendor where username=?",new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("company"));
    }


    public String get_customer_fullname_based_on_username(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=?",new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("username"));
    }

    public Cursor retrive_vendor__orders(String username,String status){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from orders where vendor_username=? and status=?",new String[] {username,status});
        return cursor;
    }

    public Cursor retrive_all_vendor__orders(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from orders where vendor_username=?",new String[] {username});
        return cursor;
    }

    public void setStatus (String vendor_username,String user_username, String date, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("status", status);
        String [] args = new String[]{vendor_username,user_username,date};
        db.update("orders", cv,"vendor_username=? and user_username=? and date=? ",args);

    }



    public void change_status_to_cancel(String new_status, String user_username, String vendor_username, String status, String date){
        SQLiteDatabase db = this.getReadableDatabase();
//        db.rawQuery("delete from orders where user_username=? and vendor_username = ? and service = ? and date = ? and total = ? and status = ?",new String[] {user_username, vendor_username, service, date, total, status});
//        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("status", new_status);
        String [] args = new String[]{user_username, vendor_username, status, date};
        db.update("orders", cv,"user_username=? and vendor_username=? and status=? and date=?",args);
    }

    public void change_order_time(String new_time, String user_username, String vendor_username, String status, String date){
        SQLiteDatabase db = this.getReadableDatabase();
//        db.rawQuery("delete from orders where user_username=? and vendor_username = ? and service = ? and date = ? and total = ? and status = ?",new String[] {user_username, vendor_username, service, date, total, status});
//        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("date", new_time);
        String [] args = new String[]{user_username, vendor_username, status, date};
        db.update("orders", cv,"user_username=? and vendor_username=? and status=? and date=?",args);
    }

}
