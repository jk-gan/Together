package com.example.micky.together;

/**
 * Created by JKGan on 5/31/15.
 */

import android.app.Activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.micky.together.R;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
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
    String username;
    protected Button joinTripButton;

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
        username = i.getStringExtra("username");

        // Locate the TextView in singleitemview.xml
        txtname = (TextView) findViewById(R.id.nameTextView2);
        txtTo = (TextView) findViewById(R.id.toTextView2);
        txtFrom = (TextView) findViewById(R.id.fromTextView2);

        // Load the text into the TextView
        txtname.setText(name);
        txtTo.setText(to);
        txtFrom.setText(from);

        // Join button
        joinTripButton = (Button) findViewById(R.id.joinButton);

        joinTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create our Installation query
                ParseQuery pushQuery = ParseInstallation.getQuery();
                pushQuery.whereEqualTo("userID", ParseUser.getCurrentUser());

                // Send push notification to query
                ParsePush push = new ParsePush();
                push.setQuery(pushQuery); // Set our Installation query
                push.setMessage("12345");
                try {
                    push.send();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Toast toast;
                toast = Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT);
                toast.show();
                //joining process
                //Check the capacity first
                //if (Integer.parseInt(capacity) == 0) {
                //   Toast toast;
                //    toast = Toast.makeText(getApplicationContext(), "Sorry, Seat is Full", Toast.LENGTH_SHORT);
                //    toast.show();
                //} else {
                    //create new join request
                //    ParseObject request = new ParseObject("Request");
                //    request.put("tripID", "001");
                //    request.put("userName", username);
                //    // 0 = reject, 1 = pending, 2 = accept
                //    request.put("status", 1);
                //    final ProgressDialog dialog = new ProgressDialog(SingleItemView.this);
                //    dialog.setMessage("Loading");
                //    dialog.setCancelable(false);
                //    dialog.setInverseBackgroundForced(false);
                //    dialog.show();
                //    request.saveInBackground();
                //    dialog.dismiss();
                //    Toast toast;
                //    toast = Toast.makeText(getApplicationContext(), "Join Successfully", Toast.LENGTH_SHORT);
                //    toast.show();
                //}
            }
        });

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