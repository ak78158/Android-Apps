package com.example.login_sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Login.db";
    public static final String TABLE_NAME = "login_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Pass";
    public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, 1); }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY ,Pass TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME); onCreate(db);
    }

    public boolean insertData(String id,String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,pass);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1) return false; else return true; }

}