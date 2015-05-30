package com.example.micky.together;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class RegisterTrip extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_trip);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_trip, menu);
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
    //Create TimePicker=====================================================
    @SuppressLint("ValidFragment")
    public class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String time = hourOfDay + ":" + minute;
            TextView textViewTime = (TextView)findViewById(R.id.textViewSetTime);
            textViewTime.setText(time);
        }
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    //Create DatePicker=====================================================
    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            String date = day + "/" + month + "/" + year;
            TextView textViewDate = (TextView)findViewById(R.id.textViewSetDate);
            textViewDate.setText(date);
        }
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    //Button add=======================================================
    public void add(View v) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        if (empty() == true){
            Toast.makeText(context,"Please all empty fields", duration).show();
        }
        else
            Toast.makeText(context, "Trip Created", duration).show();

    }

    public boolean empty(){
        EditText editText[] = new EditText[4];
        editText[0] = (EditText)findViewById(R.id.editTextFrom);
        editText[1] = (EditText)findViewById(R.id.editTextTo);
        editText[2] = (EditText)findViewById(R.id.editTextCapacity);
        editText[3] = (EditText)findViewById(R.id.editTextDescription);
        TextView textViewDate = (TextView)findViewById(R.id.textViewSetDate);
        TextView textViewTime = (TextView)findViewById(R.id.textViewSetTime);

        int count = 0;
        for (int index = 0 ; index < editText.length ; index++) {

            if ((editText[index].getText().toString().length() == 0) ||
                    (textViewDate.toString().equalsIgnoreCase("Set")) ||
                    (textViewTime.toString().equalsIgnoreCase("Set"))) {
                count ++;
            } else
                continue;
        }

        if (count != 0)
            return true;
        else
            return false;
    }
}

