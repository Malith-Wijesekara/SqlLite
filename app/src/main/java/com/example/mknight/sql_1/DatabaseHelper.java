package com.example.mknight.sql_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.key;
import static android.R.id.primary;
import static android.os.Build.ID;

/**
 * Created by Malith Wijesekara on 5/31/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static  final  String DATABASE_NAME = "student.db";
    public static  final  String TABLE_NAME = "student_table";
    public static  final  String col_1 = "ID";
    public static  final  String col_2 = "Name";
    public static  final  String col_3 = "Marks";
    public static  final  String col_4 = "Time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID integer primary key autoincrement, name text, marks integer,time DateTime default CURRENT_TIMe)");//exec queries
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists"+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String marks){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,marks);
        contentValues.put(col_4,"12:13");
        long result = db.insert(TABLE_NAME,null,contentValues); //if not successful it returns -1

        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select* from "+TABLE_NAME,null);
        return res;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "ID=?",new String[] {id});
    }
}
