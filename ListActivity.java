package com.example.david.studenttracker;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.ListView;




public class ListActivity extends Activity {
    Entries entry = new Entries("test","t");
    Entries entry1 = new Entries("test1","t1");

    Context context;
    List<Entries>data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Adapter adapter = new Adapter(context,R.layout.activity_list,data);
        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }

}
