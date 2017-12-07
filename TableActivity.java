package com.example.david.listview;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by David on 01.12.2017.
 */

public class TableActivity extends AppCompatActivity{
    private String inputtxt="";
    DatabaseHelper myDB;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_activity);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        TableLayout myLayout = (TableLayout) findViewById(R.id.mytable);

        int count = myLayout.getChildCount();
        for(int i =0;i<count;i++)
        {
            View v = myLayout.getChildAt(i);
            if(v instanceof TableRow)
            {
                TableRow row = (TableRow)v;
                int rowCount = row.getChildCount();
                for (int r =0;r<rowCount;r++)
                {
                    View v2 = row.getChildAt(r);
                    if(v2 instanceof TextView)
                    {
                        final TextView t = (TextView)v2;
                        String inputtext="";
                        int color=0;
                        //inputtext=myDB.getText(t.getId());
                        //if(inputtext !="" || inputtext !=null)
                        //t.setText(inputtext);
                        t.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                //int test = t.getId();
                                String tmp = t.getResources().getResourceName(t.getId());
                                String [] split = tmp.split("/");
                                String tmp2 = split[1];
                                final TextView update = (TextView)findViewById(t.getId());
                                AlertDialog.Builder builder = new AlertDialog.Builder(TableActivity.this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                                builder.setTitle("Insert class");

                                final EditText classinput = new EditText(TableActivity.this);
                                classinput.setInputType(InputType.TYPE_CLASS_TEXT);
                                builder.setView(classinput);
                                builder.setPositiveButton("Exercise",new DialogInterface.OnClickListener()
                                {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which)
                                   {
                                       inputtxt=classinput.getText().toString();
                                       update.setText(inputtxt);
                                       update.setBackgroundColor(0xFF0000FF);
                                       myDB.insertInput(t.getId(),inputtxt,0xFF0000FF);
                                   }
                                });

                                builder.setNeutralButton("Lecture",new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        inputtxt=classinput.getText().toString();
                                        update.setText(inputtxt);
                                        update.setBackgroundColor(0xFFFF0000);
                                    }
                                });

                                builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener()
                                {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which)
                                   {
                                       dialog.cancel();
                                   }
                                });
                                builder.show();

                                //String tmp = String.valueOf(test);
                                Toast.makeText(getApplicationContext(),tmp2,Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        });
                        //Toast.makeText(getApplicationContext(),"it works",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:

                    return true;
            }
            return false;
        }
    };
}
