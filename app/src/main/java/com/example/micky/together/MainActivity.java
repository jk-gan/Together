package com.example.micky.together;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import trip.Trip;


public class MainActivity extends ActionBarActivity {
    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_main);
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
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
            startActivity(new Intent(MainActivity.this, FirstPage.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        ArrayList<Trip> trips = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Together");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "Country" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "Trip");
//            query.orderByDescending("_created_at");
            try {
                ob = query.find();
            } catch (com.parse.ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into an ArrayAdapter
            MyAdapter adapter = new MyAdapter(MainActivity.this, generateData());
            // Retrieve object "description" from Parse.com database

            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
            // Capture button clicks on ListView items
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // Send single item click data to SingleItemView Class
                    Intent i = new Intent(MainActivity.this,
                            SingleItemView.class);
                    // Pass data "name" followed by the position
                    i.putExtra("owner", ob.get(position).getString("owner"));
                    i.putExtra("from", ob.get(position).getString("from"));
                    i.putExtra("to", ob.get(position).getString("to"));
                    i.putExtra("capacity", ob.get(position).getString("capacity"));
                    //send the username
                    i.putExtra("username", "cool");
                    // Open SingleItemView.java Activity
                    startActivity(i);
                }
            });
        }

        private ArrayList<Trip> generateData() {
            for (ParseObject trip : ob) {
                trips.add(new Trip((String)trip.get("to"), (String)trip.get("description")));
//                adapter.add((String) trip.get("to"));
            }
            return trips;
        }


    }
}
