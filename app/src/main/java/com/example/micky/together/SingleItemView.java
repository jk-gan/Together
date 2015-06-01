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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.micky.together.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class SingleItemView extends ActionBarActivity {
    // Declare Variables
    private TextView txtname;
    private TextView txtTo;
    private TextView txtFrom;
    private TextView txtCapacity;
    private String name;
    private String from;
    private String to;
    private int capacity;
    private String userID;
    private String tripID;
    protected Button joinTripButton;
    protected Button cancelTripButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);

        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();

        // Get the data
        tripID = i.getStringExtra("tripID");
        name = i.getStringExtra("owner");
        from = i.getStringExtra("from");
        to = i.getStringExtra("to");
        capacity = i.getIntExtra("capacity", 0);
        userID = ParseUser.getCurrentUser().getObjectId();

        // Locate the TextView in singleitemview.xml
        txtname = (TextView) findViewById(R.id.nameTextView2);
        txtTo = (TextView) findViewById(R.id.toTextView2);
        txtFrom = (TextView) findViewById(R.id.fromTextView2);
        txtCapacity = (TextView) findViewById(R.id.capacityTextView2);

        // Load the text into the TextView
        txtname.setText(name);
        txtTo.setText(to);
        txtFrom.setText(from);
        txtCapacity.setText("" + capacity);

        // Join button
        joinTripButton = (Button) findViewById(R.id.joinButton);

        // Cancel button
        cancelTripButton = (Button) findViewById(R.id.cancelButton);

        // SELECT * FROM Request WHERE userID = userID AND tripID = tripID
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Request");
        query.whereEqualTo("userID", userID);
        query.whereEqualTo("tripID", tripID);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> users, ParseException e) {
                if (users.size() == 0) {
                    // set button to be visible
                    joinTripButton.setVisibility(View.VISIBLE);
                    joinTripButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //joining process
                            //Check the capacity first
                            if (capacity <= 0) {
                                Toast toast;
                                toast = Toast.makeText(getApplicationContext(), "Sorry, the car is Full", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                //create new join request
                                ParseObject request = new ParseObject("Request");
                                request.put("userID", userID);
                                request.put("tripID", tripID);
//                    // status = "rejected", "pending", "accepted"
                                request.put("status", "pending");

//                    final ProgressDialog dialog = new ProgressDialog(SingleItemView.this);
//                    dialog.setMessage("Loading");
//                    dialog.setCancelable(false);
//                    dialog.setInverseBackgroundForced(false);
//                    dialog.show();
                                request.saveInBackground();
//                    dialog.dismiss();
                                Toast toast;
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                                toast = Toast.makeText(getApplicationContext(), "Join Successfully", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    });

                } else {
                    cancelTripButton.setVisibility(View.VISIBLE);
                    cancelTripButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            users.get(0).deleteInBackground();
                            Toast toast;
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                            toast = Toast.makeText(
                                    getApplicationContext(),
                                    "You had canceled for this trip", Toast.LENGTH_SHORT);
                            toast.show();

                        }
                    });
                }
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
