package com.example.exceloid_android.quickhrmobileapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class ClaimRequest extends AppCompatActivity {

    Spinner claimProjects,claimSpinner;
    private static TextView to_date,from_date;
    String[] s1=new String[]{"--Select--","Test Admin","Test Employee","Kiran Nalla"};
    String[] s2=new String[]{"--Select--","Quick HR","Claim App","Leave App"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.claim_request);
        setUpActionBar();

        claimSpinner=(Spinner)findViewById(R.id.claimSpinner);
        claimProjects=(Spinner)findViewById(R.id.claimProjects);
        to_date=(TextView)findViewById(R.id.to_date);
        from_date=(TextView)findViewById(R.id.from_date);

        ArrayAdapter<String> adp=new ArrayAdapter<String>(ClaimRequest.this,R.layout.spinner,R.id.text_spinner,s1);
        claimSpinner.setAdapter(adp);
        ArrayAdapter<String>adp1=new ArrayAdapter<String>(ClaimRequest.this,R.layout.spinner,R.id.text_spinner,s2);
        claimProjects.setAdapter(adp1);

        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "fromPicker");

            }
        });

        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickerFragmentto();
                newFragment.show(getSupportFragmentManager(), "toPicker");

            }
        });
    }

    public static class DatePickerFragment extends DialogFragment
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
            int i=month+1;
            from_date.setText(day+" - "+i+" - "+year);
        }
    }

    public static class DatePickerFragmentto extends DialogFragment
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
            int i = month + 1;
            to_date.setText(day + " - " + i + " - " + year);
        }
    }

    public void setUpActionBar(){
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
