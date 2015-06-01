package com.example.micky.together;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends ActionBarActivity {

    protected EditText usernameField;
    protected EditText passwordField;
    protected Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        usernameField = (EditText) findViewById(R.id.usernameLoginEditText);
        passwordField = (EditText) findViewById(R.id.passwordLoginEditText);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            Toast toast;
            int count = 0;
            @Override
            public void onClick(View v) {
                //to track number of login attempts.

                // get username input and password input
                final String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                if (count == 3){

                    final KeyListener usernameListener;
                    final KeyListener passwordListener;
                    usernameListener = usernameField.getKeyListener();
                    passwordListener = passwordField.getKeyListener();
                    //if try login 3 time in a row.
                    Toast.makeText(getApplicationContext(),"You have attempt 3 consecutive login." +
                            "\nPlease try again some times.",Toast.LENGTH_SHORT).show();
                    new CountDownTimer(30000, 1000) {

                        TextView timer = (TextView)findViewById(R.id.timerTextView);
                        public void onTick(long millisUntilFinished) {
                            usernameField.setKeyListener(null);
                            passwordField.setKeyListener(null);
                            timer.setVisibility(View.VISIBLE);
                            loginButton.setVisibility(View.INVISIBLE);
                            timer.setText("Seconds Remaining: " + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            count = 0;
                            loginButton.setVisibility(View.VISIBLE);
                            timer.setVisibility(View.INVISIBLE);
                            usernameField.setKeyListener(usernameListener);
                            passwordField.setKeyListener(passwordListener);
                        }
                    }.start();

                }
                else{
                    //check empty fields
                    if (username.matches("") || password.matches("")){
                        Toast.makeText(getApplicationContext(),"Please fill all empty fields",Toast.LENGTH_SHORT).show();
                        count++;
                    }
                    else{
                        //check username space
                        for(int index = 0 ; index < username.length() ; index++){
                            if (username.charAt(index) == ' '){
                                Toast.makeText(getApplicationContext(),"Username cannot contains spaces",Toast.LENGTH_SHORT).show();
                                count++;
                                break;
                            }
                            else if (index == username.length()-1){
                                final ProgressDialog dialog=new ProgressDialog(LoginActivity.this);
                                dialog.setMessage("Loging in");
                                dialog.setCancelable(false);
                                dialog.setInverseBackgroundForced(false);
                                dialog.show();
                                // doing login function in backend
                                ParseUser.logInInBackground(username, password, new LogInCallback() {

                                    public void done(ParseUser user, ParseException e) {
                                        if (user != null) {
                                            // Hooray! The user is logged in.
                                            // redirect to main page.
                                            dialog.dismiss();
                                            count = 0;
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                            toast = Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                        else {
                                            // Signup failed. Look at the ParseException to see what happened.
                                            dialog.dismiss();
                                            toast = Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT);
                                            toast.show();
                                            count++;
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
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
