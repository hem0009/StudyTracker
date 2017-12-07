package com.example.david.listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by David on 26.11.2017.
 */

public class TestFormActivity extends Activity {
    DatabaseHelper myDb;
    EditText editKod, editWhat, editMin, editMax, editWhen, editGained;
    Button btnAddTest, btnAddPoints, btnHowTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_form_activity);
        myDb = new DatabaseHelper(this);
        editKod=(EditText)findViewById(R.id.editKod);
        editWhat=(EditText)findViewById(R.id.editWhat);
        editMin=(EditText)findViewById(R.id.editMin);
        editMax=(EditText)findViewById(R.id.editMax);
        editWhen=(EditText)findViewById(R.id.editWhen);
        editGained=(EditText)findViewById(R.id.editGained);
        btnAddTest=(Button)findViewById(R.id.addTest);
        btnAddPoints=(Button)findViewById(R.id.addPoints);
        btnHowTo=(Button)findViewById(R.id.howTo);
        AddData();
        AddPoints();
        HowTo();
    }

    public void AddData() {
        btnAddTest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertTest(editKod.getText().toString(),
                                editWhat.getText().toString(),
                                editMin.getText().toString(),editMax.getText().toString(),editWhen.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(TestFormActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(TestFormActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddPoints(){
        btnAddPoints.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertPoints(editKod.getText().toString(),editGained.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(TestFormActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(TestFormActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void HowTo(){
        btnHowTo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        StringBuffer buffer = new StringBuffer();
                        buffer.append("To add new test, insert all information but Gained points\n");
                        buffer.append("To add points to a test, fill only code and Gained points\n");

                        // Show all data
                        showMessage("How To",buffer.toString());
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
