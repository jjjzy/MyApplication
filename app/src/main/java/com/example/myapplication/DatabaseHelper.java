package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //constructor
    public DatabaseHelper(Context context){
        super(context,"Login.db",null,17);

    }

    //create a table for customers
    public void onCreate(SQLiteDatabase db){
        db.execSQL("Create table user(fullname text, email text, username text primary key, password text, answer text, address text,phone text, points text)");
        db.execSQL("Create table vendor(username text primary key, password text, answer text, address text,phone text,company text,service text,price text,email text)");
        db.execSQL("Create table orders(user_username text,vendor_username text, service text, date text,total double,status text, payment_method text, order_number text, amount_paid double)");
        db.execSQL("Create table services(service_category text primary key)");
        db.execSQL("Create table prefered_card(username text primary key, first text, second text, third text, fourth text, fullname text, month text, year text, cvv text, zip text)");

        ContentValues contentValues = new ContentValues();

        contentValues.put("service_category", "Appliances");
        db.insert("services", null, contentValues);
        contentValues.put("service_category", "Electrical");
        db.insert("services", null, contentValues);
        contentValues.put("service_category", "Plumbing");
        db.insert("services", null, contentValues);
        contentValues.put("service_category", "Home Cleaning");
        db.insert("services", null, contentValues);
        contentValues.put("service_category", "Packaging and Moving");
        db.insert("services", null, contentValues);
        contentValues.put("service_category", "Computer Repair");
        db.insert("services", null, contentValues);
        contentValues.put("service_category", "Home Repair and Painting");
        db.insert("services", null, contentValues);
        contentValues.put("service_category", "Pest Control");
        db.insert("services", null, contentValues);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists vendor");
        db.execSQL("drop table if exists orders");
        db.execSQL("drop table if exists services");
        db.execSQL("drop table if exists prefered_card");
        onCreate(db);
    }

    public boolean insertCard(String username, String first, String second, String third, String fourth, String fullname, String month, String year, String cvv, String zip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("first", first);
        contentValues.put("second", second);
        contentValues.put("third", third);
        contentValues.put("fourth", fourth);
        contentValues.put("fullname", fullname);
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("cvv", cvv);
        contentValues.put("zip", zip);
        long ins = db.insert("prefered_card", null, contentValues);

        if (ins == -1) return false;   //if the query does not work return false
        else return true;
    }

    public boolean insertServices(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        <item>Appliances</item>
//        <item>Electrical</item>
//        <item>Plumbing</item>
//        <item>Home Cleaning</item>
//        <item>Tutoring</item>
//        <item>Packaging and Moving</item>
//        <item>Computer Repair</item>
//        <item>Home Repair and Painting</item>
//        <item>Pest Control</item>
        contentValues.put("service_category", "Appliances");
        contentValues.put("service_category", "Electrical");
        contentValues.put("service_category", "Plumbing");
        contentValues.put("service_category", "Home Cleaning");
        contentValues.put("service_category", "Packaging and Moving");
        contentValues.put("service_category", "Computer Repair");
        contentValues.put("service_category", "Home Repair and Painting");
        contentValues.put("service_category", "Pest Control");

        long ins = db.insert("services", null, contentValues);

        if (ins == -1) return false;   //if the query does not work return false
        else return true;

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
        contentValues.put("points", "0");
        long ins = db.insert("user", null, contentValues);

        if (ins == -1) return false;   //if the query does not work return false
        else return true;
    }


    public boolean insertOrder(String user_username, String vendor_username, String service,String date,double total, String status, String method, String order_number, double amount_paid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_username", user_username);
        contentValues.put("vendor_username", vendor_username);
        contentValues.put("service", service);
        contentValues.put("date", date);
        contentValues.put("total", total);
        contentValues.put("status", status);
        contentValues.put("payment_method", method);
        contentValues.put("order_number", order_number);
        contentValues.put("amount_paid", amount_paid);
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

    public Cursor retrive_all_service_caterogies(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from services",new String[] {});
        return cursor;
    }

    public Cursor retrive_card_info_based_on_username(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from prefered_card where username = ?", new String[] {username});
        return cursor;
    }

    public void delete_card_info_based_on_username(String Username){
        SQLiteDatabase db = this.getWritableDatabase();
//        db.rawQuery("delete from prefered_card where username=?", new String[] {Username});
        db.execSQL("DELETE FROM " + "prefered_card" + " WHERE "+"username"+"='"+Username+"'");
    }

    public String retrive_points_based_on_username(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=?",new String[] {username});
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("points"));
    }

    public void update_points(String username, String new_point){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("points", new_point);
        String [] args = new String[]{username};
        db.update("user", cv,"username=?",args);
    }

}
