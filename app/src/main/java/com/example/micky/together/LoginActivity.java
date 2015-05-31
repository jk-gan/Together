package com.example.micky.together;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends ActionBarActivity {

    protected EditText usernameField;
    protected EditText passwordField;
    protected Button loginButton;
    protected Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        usernameField = (EditText) findViewById(R.id.usernameLoginEditText);
        passwordField = (EditText) findViewById(R.id.passwordLoginEditText);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get username input and password input
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                final ProgressDialog dialog=new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Loading");
                dialog.setCancelable(false);
                dialog.setInverseBackgroundForced(false);
                dialog.show();
                // doing login function in backend
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            // redirect to main page
                            dialog.dismiss();
                            Toast toast;
                            toast = Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT);
                            toast.show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            dialog.dismiss();
                            Toast toast;
                            toast = Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT);
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
