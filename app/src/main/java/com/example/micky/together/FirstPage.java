package com.example.micky.together;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class FirstPage extends ActionBarActivity {

    protected Button loginButton;
    protected Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            startActivity(new Intent(FirstPage.this, MainActivity.class));
        } else {
            // show the signup or login screen
            setContentView(R.layout.activity_first_page);

            loginButton = (Button) findViewById(R.id.gotoLoginButton);
            signupButton = (Button) findViewById(R.id.gotoSignupButton);

            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // call SignupActivity
                    startActivity(new Intent(FirstPage.this, SignupActivity.class));
                }
            });

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FirstPage.this, LoginActivity.class));

                }
            });
        }


//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
