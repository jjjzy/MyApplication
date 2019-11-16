package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //constructor
    //test merge
    public DatabaseHelper(Context context){
        super(context,"Login.db",null,3);
    }

    //create a table for customers
    public void onCreate(SQLiteDatabase db){
        db.execSQL("Create table user(fname text, lname text, username text primary key, password text, answer text, address text,phone text)");
        db.execSQL("Create table vendor(username text primary key, password text, answer text, address text,phone text,company text,service text)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL("drop table if exists user");
        onCreate(db);
    }

    //insert username and password to the database
    public boolean insertUser(String fname, String lname, String username,String password,String answer,String address, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("answer", answer);
        contentValues.put("address",address);
        contentValues.put("phone",phone);
        long ins = db.insert("user", null,contentValues);

        if (ins==-1) return false;   //if the query does not work return false
        else return true;
    }

    public boolean insertVendor(String username, String password, String answer,String address,String phone,String company, String service){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("answer", answer);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("company",company);
        contentValues.put("service",service);
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
}
