package com.example.exceloid_android.exceloid_mobileapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * Created by Exceloid-Android on 08-11-2016.
 */

public class AddExpenses_Reports_Activity extends AppCompatActivity {

    Button save;
    Spinner country,fromdate,todate,fromtime,totime;
    Switch switch_repo;
    EditText edt1,edt2;
    String[] s1=new String[]{"01/11/2016","02/11/2016","03/11/2016","04/11/2016"};
    String[] s2=new String[]{"01/11/2016","02/11/2016","03/11/2016","04/11/2016"};
    String[] s3=new String[]{"06:00","08:00","10:00","12:00","14:00"};
    String[] s4=new String[]{"08:00","10:00","12:00","14:00","16:00"};
    String[] s5=new String[]{"India","Singapore","Australia","Malaysia","America"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpense_reports_activity);
        setupActionBar();

        save=(Button)findViewById(R.id.save_expenses_reports);
        country=(Spinner)findViewById(R.id.country_spinner);
        fromdate=(Spinner)findViewById(R.id.datefrom_spinner);
        todate=(Spinner)findViewById(R.id.dateto_spinner);
        fromtime=(Spinner)findViewById(R.id.timefrom_spinner);
        totime=(Spinner)findViewById(R.id.timeto_spinner);
        switch_repo=(Switch)findViewById(R.id.receipt_switch);
        edt1=(EditText)findViewById(R.id.workshop);
        edt2=(EditText)findViewById(R.id.oakwood);

        ArrayAdapter<String>adp=new ArrayAdapter<String>(AddExpenses_Reports_Activity.this,R.layout.spinner,R.id.text_spinner,s1);
        fromdate.setAdapter(adp);
        ArrayAdapter<String>adp1=new ArrayAdapter<String>(AddExpenses_Reports_Activity.this,R.layout.spinner,R.id.text_spinner,s2);
        todate.setAdapter(adp1);
        ArrayAdapter<String>adp2=new ArrayAdapter<String>(AddExpenses_Reports_Activity.this,R.layout.spinner,R.id.text_spinner,s3);
        fromtime.setAdapter(adp2);
        ArrayAdapter<String>adp3=new ArrayAdapter<String>(AddExpenses_Reports_Activity.this,R.layout.spinner,R.id.text_spinner,s4);
        totime.setAdapter(adp3);
        ArrayAdapter<String>adp4=new ArrayAdapter<String>(AddExpenses_Reports_Activity.this,R.layout.spinner,R.id.text_spinner,s5);
        country.setAdapter(adp4);
    }

    public void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
    return super.onOptionsItemSelected(item);
}
}
