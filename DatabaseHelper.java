package com.example.david.listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
    public static final String TABLE_TIMETABLE ="timetable_table";
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
    public static final String COL_55 = "Date";
    public static final String COL_66 = "Gained";
    //timetable_table columns
    public static final String COL_111 = "Id";
    public static final String COL_222 = "Input";
    public static final String COL_333 = "Color";
    public DatabaseHelper (Context context)
    {
        super(context,DATABASE,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+TABLE_CLASS+" (KOD TEXT, Name TEXT, Teacher text, Type text)");
        db.execSQL("create table "+TABLE_TESTS+" (KOD TEXT, What TEXT, Min INTEGER, Max INTEGER, Date TEXT, Gained INTEGER)");
        db.execSQL("create table "+TABLE_TIMETABLE+" (Id INTEGER, Input TEXT, Color INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CLASS );
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TESTS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TIMETABLE);
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
    public boolean insertTest(String kod, String what, String min, String max, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_11,kod);
        contentValues.put(COL_22,what);
        contentValues.put(COL_33,Integer.parseInt(min));
        contentValues.put(COL_44,Integer.parseInt(max));
        contentValues.put(COL_55,date);
        contentValues.put(COL_66,0);
        long result = db.insert(TABLE_TESTS,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
            return true;
    }

    public boolean insertInput(int id, String input, int color)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_111,id);
        contentValues.put(COL_222,input);
        contentValues.put(COL_333,color);
        long result = db.insert(TABLE_TIMETABLE,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
            return true;
    }
    public boolean insertPoints (String kod, String gained)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_66,Integer.parseInt(gained));
        db.update(TABLE_TESTS,contentValues,"KOD = ?",new String[]{kod});
        return true;
    }


    public String getText(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = "select Input from timetable_table where Id = "+id;
        Cursor cursor = db.rawQuery(select,null);
        String value="";
        if(cursor.moveToFirst())
        {
            value = cursor.getString(0);
        }
        return value;
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
    /*public Cursor getAllTest(String kod)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_TESTS+" where KOD like '%"+kod+"%'",null);
        return res;
    }*/
    public ArrayList<MyClasses> getData()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<MyClasses> myClasses = new ArrayList<MyClasses>();
        Cursor res = db.rawQuery("select * from "+TABLE_CLASS,null);
        if(res!=null & res.getCount()>0)
        {
            while (res.moveToNext())
            {
                myClasses.add(new MyClasses(res.getString(res.getColumnIndexOrThrow(COL_1)),res.getString(res.getColumnIndex(COL_2)),res.getString(res.getColumnIndex(COL_3)),res.getString(res.getColumnIndex(COL_4))));

            }
        }

        return myClasses;
    }
    public Integer deleteData(String kod)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TESTS,"KOD = ?",new String[]{kod});
        return db.delete(TABLE_CLASS,"KOD = ?",new String [] {kod});
    }
}
