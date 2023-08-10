package com.example.moneymanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String dataBaseName = "Register.db";

    public DataBaseHelper(Context context) {
        super(context, "Register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase UserDataBase) {
        UserDataBase.execSQL("create TABLE users(email TEXT PRIMARY KEY, fullName TEXT, password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase UserDataBase, int i, int i1) {
        UserDataBase.execSQL("DROP TABLE if exists users");
    }

    public boolean insertData(String email, String fullName, String password){
        SQLiteDatabase UserDataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("fullName", fullName);
        contentValues.put("password", password);

        long result = UserDataBase.insert("users",null, contentValues);
        if(result == 1){
            return false;
        } else{
            return true;
        }
    }

    public boolean checkEmail(String email){
        SQLiteDatabase UserDataBase = this.getWritableDatabase();
        Cursor cursor = UserDataBase.rawQuery("Select * from users where email = ?", new String [] {email});
        if( cursor.getCount() > 0 ){
            return true;
        } else{
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase UserDataBase = this.getWritableDatabase();
        Cursor cursor = UserDataBase.rawQuery("Select * from users where email = ? and password = ?", new String[] {email, password});
        if( cursor.getCount() > 0 ){
            return true;
        } else {
            return false;
        }
    }

}

