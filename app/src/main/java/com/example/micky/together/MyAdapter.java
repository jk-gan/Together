package com.example.micky.together;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import trip.Trip;

/**
 * Created by JKGan on 6/1/15.
 */
public class MyAdapter extends ArrayAdapter<Trip> {

    private final Context context;
    private final ArrayList<Trip> tripsArrayList;

    public MyAdapter(Context context, ArrayList<Trip> tripsArrayList) {

        super(context, R.layout.listview_item, tripsArrayList);

        this.context = context;
        this.tripsArrayList = tripsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.listview_item, parent, false);

        // 3. Get the two text view from the rowView
        TextView textView = (TextView) rowView.findViewById(R.id.text);
        TextView valueView = (TextView) rowView.findViewById(R.id.value);

        // 4. Set the text for textView
        textView.setText(tripsArrayList.get(position).getDestination());
        valueView.setText(tripsArrayList.get(position).getDescription());

        // 5. retrn rowView
        return rowView;
    }
}