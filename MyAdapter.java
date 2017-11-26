package com.example.david.listview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 19.11.2017.
 */

public class MyAdapter extends ArrayAdapter{
    private Context context;
    private ArrayList<MyClasses>myClasses;

    public MyAdapter(Context context, int textViewID, ArrayList objects)
    {
        super(context,textViewID,objects);
        this.context=context;
        myClasses=objects;
    }

    private class ViewHolder
    {
        TextView kod;
        TextView name;
        TextView teacher;
        TextView type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view,null);

            holder = new ViewHolder();
            holder.kod=(TextView)convertView.findViewById(R.id.showKod);
            holder.name=(TextView)convertView.findViewById(R.id.showName);
            holder.teacher=(TextView)convertView.findViewById(R.id.showTeacher);
            holder.type=(TextView)convertView.findViewById(R.id.showType);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        MyClasses eachone=myClasses.get(position);
        holder.kod.setText(eachone.getKod());
        holder.name.setText(eachone.getName());
        holder.teacher.setText(eachone.getTeacher());
        holder.type.setText(eachone.getType());
        return convertView;

    }
}
/*public class MyAdapter extends CursorAdapter {
    Context context;
    Cursor cursor;

    LayoutInflater inflater;

    public MyAdapter(Context context, Cursor cursor)
    {
        super(context,cursor);
        this.cursor=cursor;
        this.context=context;

        inflater=(LayoutInflater.from(context));
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView kod = (TextView)view.findViewById(R.id.showKod);
        kod.setText(cursor.getString(0));
        TextView name = (TextView)view.findViewById(R.id.showName);
        name.setText(cursor.getString(1));
        //TextView teacher = (TextView)view.findViewById(R.id.showTeacher);
        //teacher.setText(cursor.getString(2));
        //TextView type = (TextView)view.findViewById(R.id.showType);
        //type.setText(cursor.getString(3));
    }

    @Override
    public View newView(Context context, Cursor  cursor, ViewGroup parent)
    {

        inflater=LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_view,parent, false);

        return  v;
    }

}*/
