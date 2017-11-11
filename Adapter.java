package com.example.david.studenttracker;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by David on 11.11.2017.
 */

public class Adapter extends ArrayAdapter<Entries> {
    Context context;
    int layoutResourceID;
    List<Entries> data = null;
    public Adapter(Context context, int layoutResourceID, List<Entries>data)
    {
        super(context,layoutResourceID,data);
        this.layoutResourceID=layoutResourceID;
        this.context=context;
        this.data=data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        EntryHolder holder = null;
        if(row==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceID,parent,false);
            holder = new EntryHolder();
            holder.txtjmeno = (TextView)row.findViewById(R.id.txtjmeno);
            holder.txtkod = (TextView)row.findViewById(R.id.txtkod);
            row.setTag(holder);
        }
        else
        {
            holder = (EntryHolder)row.getTag();
        }
        Entries entry = data.get(position);
        holder.txtjmeno.setText(entry.predmet);
        holder.txtkod.setText(entry.kod);
        return row;
    }


    static class EntryHolder
    {
        TextView txtjmeno;
        TextView txtkod;

    }
}
