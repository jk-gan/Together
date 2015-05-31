package com.example.micky.together;

/**
 * Created by JKGan on 5/31/15.
 */

import android.app.Activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.micky.together.R;
import com.parse.ParseUser;

public class SingleItemView extends ActionBarActivity {
    // Declare Variables
    TextView txtname;
    TextView txtTo;
    TextView txtFrom;
    String name;
    String from;
    String to;
    String capacity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);

        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();

        // Get the name
        name = i.getStringExtra("owner");
        from = i.getStringExtra("from");
        to = i.getStringExtra("to");
        capacity = i.getStringExtra("capacity");

        // Locate the TextView in singleitemview.xml
        txtname = (TextView) findViewById(R.id.nameTextView2);
        txtTo = (TextView) findViewById(R.id.toTextView2);
        txtFrom = (TextView) findViewById(R.id.fromTextView2);

        // Load the text into the TextView
        txtname.setText(name);
        txtTo.setText(to);
        txtFrom.setText(from);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logoutButton) {
            ParseUser.logOut();
            startActivity(new Intent(SingleItemView.this, FirstPage.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}