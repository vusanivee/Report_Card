package com.example.admin.reportcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016/10/26.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
   public static final  String DATABASE_NAME = "student.database";
    public static final  String TABLE_NAME = "student_table";
    public static final  String column_1 = "ID";
    public static final  String column_2= "NAME";
    public static final  String column_3 = "SURNAME";
    public static final  String column_4 = "MARKS";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    public  boolean insertData(String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_2,name);
        contentValues.put(column_3,surname);
        contentValues.put(column_4,marks);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if (result ==-1)
            return false;
            else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME,null);
        return  res;



    }
    public boolean updateData(String id,String name,String surname,String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_1,id);
        contentValues.put(column_2,name);
        contentValues.put(column_3,surname);
        contentValues.put(column_4,marks);
        db.update(TABLE_NAME,contentValues,"id = ?",new String[] {id});
        return true;
    }
    public Integer deleteData (String id)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID =? ",new String[]{id});
    }

}
