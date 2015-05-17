package com.example.micky.together;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import com.parse.ParseException;


public class SignupActivity extends ActionBarActivity {

    protected EditText usernameField;
    protected EditText passwordField;
    private Button SignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "XRGu8hmElf1tVf2JdNydy5GJey5HhUJ7BKSQMAF4", "upT7cr6CUEqu2Jylx2fOUJClImfyY7N48OXJBDi5");


        usernameField = (EditText) findViewById(R.id.usernameEditText);
        passwordField = (EditText) findViewById(R.id.passwordEditText);
        SignupButton = (Button) findViewById(R.id.signupButton);

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast toast;
                            toast = Toast.makeText(getApplicationContext(), "Signup Successfully", Toast.LENGTH_SHORT);
                            toast.show();

                            // call LoginActivity
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Toast toast;
                            toast = Toast.makeText(getApplicationContext(), "Failed to Signup", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
