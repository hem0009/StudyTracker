package com.example.david.listview;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView simpleList;
    FloatingActionButton button;
    DatabaseHelper myDB;
    ArrayList<MyClasses>myClasses=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleList=(ListView)findViewById(R.id.mobile_list);
        myDB = new DatabaseHelper(this);
        myClasses=myDB.getData();
        settingBut();
        MyAdapter myAdapter = new MyAdapter(this,R.layout.list_view,myClasses);
        simpleList = (ListView)findViewById(R.id.mobile_list);
        simpleList.setAdapter(myAdapter);
        simpleList.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyClasses myClasses = (MyClasses)adapterView.getItemAtPosition(i);

                Intent intent = new Intent(view.getContext(),ClassDetail.class);
                intent.putExtra("KOD",myClasses.kod);
                intent.putExtra("Name",myClasses.name);
                intent.putExtra("Teacher",myClasses.teacher);
                intent.putExtra("Type",myClasses.type);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), "test: " + myClasses.kod , Toast.LENGTH_SHORT).show();
            }
        });
        //Cursor res = myDB.getAllData();
        //MyAdapter myAdapter = new MyAdapter(getApplicationContext(),res);
        //simpleList.setAdapter(myAdapter);


    }

    public void settingBut()
    {
        button = (FloatingActionButton)findViewById(R.id.btnPlus);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FormActivity.class);
                startActivity(intent);
            }
        });
    }
}
