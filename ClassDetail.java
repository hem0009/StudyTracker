package com.example.david.listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by David on 25.11.2017.
 */

public class ClassDetail extends AppCompatActivity {

    TextView txtKOD;
    TextView txtName;
    TextView txtTeacher;
    TextView txtType;
    TextView txtPoints;
    FloatingActionButton button;
    Button viewTests;
    DatabaseHelper myDb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        myDb = new DatabaseHelper(this);
        Intent intent = getIntent();
        String kod = intent.getStringExtra("KOD");
        String name = intent.getStringExtra("Name");
        String teacher = intent.getStringExtra("Teacher");
        String type = intent.getStringExtra("Type");
        int currentPoints = 0;
        int remainingPoints = 100-currentPoints;
        String points = currentPoints+" of 100 ("+remainingPoints+")";




        txtKOD=(TextView)findViewById(R.id.detailKod);
        txtKOD.setText(kod);
        txtName=(TextView)findViewById(R.id.detailName);
        txtName.setText(name);
        txtTeacher=(TextView)findViewById(R.id.detailTeacher);
        txtTeacher.setText(teacher);
        txtType=(TextView)findViewById(R.id.detailType);
        txtType.setText(type);
        //txtPoints=(TextView)findViewById(R.id.detailPoints);
        //txtPoints.setText(points);
        viewTests=(Button)findViewById(R.id.showAll);
        viewAll();
        settingBut();
    }

    public void settingBut()
    {
        button = (FloatingActionButton)findViewById(R.id.btnAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TestFormActivity.class);
                startActivity(intent);
            }
        });
    }
    public void viewAll() {
        viewTests.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllTest();
                        //Cursor res = myDb.getAllTest(txtKOD.toString());
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Kod :"+ res.getString(0)+"\n");
                            buffer.append("What :"+ res.getString(1)+"\n");
                            buffer.append("Min :"+ res.getInt(2)+"\n");
                            buffer.append("Max :"+ res.getInt(3)+"\n");
                            buffer.append("When :"+res.getString(4)+"\n");
                            buffer.append("Gained :"+res.getInt(5)+"\n\n");
                        }

                        // Show all data
                        showMessage("My tests",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
