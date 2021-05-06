package com.example.q15;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
public class dbclass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="COURSE";
    public dbclass(Context context) {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID TEXT,NAME TEXT,COURSE TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if"+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertMyData(String Id,String name,String course)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,Id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,course);
        /*db.insert(TABLE_NAME,null,contentValues);*/
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;
    }
    public Cursor getMyAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public void delete(String ss) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"Id=?", new String[]{ss});
    }
    public void update(String sid, String s1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2,s1);
        db.update(TABLE_NAME,cv,"ID=?", new String[]{sid});
    }
}