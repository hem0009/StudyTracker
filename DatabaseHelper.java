package com.example.david.listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by David on 19.11.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //database
    public static final String DATABASE = "Tracker.db";
    //tables
    public static final String TABLE_CLASS = "class_table";
    public static final String TABLE_TESTS = "tests_table";
    //class_table columns
    public static final String COL_1 = "KOD";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Teacher";
    public static final String COL_4 = "Type";
    //tests_table columns
    public static final String COL_11 = "KOD";
    public static final String COL_22 = "What";
    public static final String COL_33 = "Min";
    public static final String COL_44 = "Max";
    public static final String COL_55 = "When";
    public static final String COL_66 = "Gained";
    public DatabaseHelper (Context context)
    {
        super(context,DATABASE,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+TABLE_CLASS+" (KOD TEXT, Name TEXT, Teacher text, Type text)");
        db.execSQL("create table "+TABLE_TESTS+" (KOD TEXT, What TEXT, Min INTEGER, Max INTEGER, When TEXT");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLASS );
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TESTS);
        onCreate(db);
    }

    public boolean insertData(String kod, String name, String teacher, String type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,kod);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,teacher);
        contentValues.put(COL_4,type);
        long result = db.insert(TABLE_CLASS,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertTest(String kod, String what, int min, int max, String when)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_11,kod);
        contentValues.put(COL_22,what);
        contentValues.put(COL_33,min);
        contentValues.put(COL_44,max);
        contentValues.put(COL_55,when);
        contentValues.put(COL_66,0);
        long result = db.insert(TABLE_TESTS,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
            return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_CLASS,null);
        return res;
    }
    public Cursor getAllTest()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_TESTS,null);
        return res;
    }
    public ArrayList<MyClasses> getData()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<MyClasses> myClasses = new ArrayList<MyClasses>();
        Cursor res = db.rawQuery("select * from "+TABLE_CLASS,null);
        while (res.moveToNext())
        {
            myClasses.add(new MyClasses(res.getString(res.getColumnIndexOrThrow(COL_1)),res.getString(res.getColumnIndex(COL_2)),res.getString(res.getColumnIndex(COL_3)),res.getString(res.getColumnIndex(COL_4))));

        }
        return myClasses;
    }
    public Integer deleteData(String kod)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CLASS,"KOD = ?",new String [] {kod});
    }
}
