package com.example.exceloid_android.exceloid_mobileapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Exceloid-Android on 18-10-2016.
 */

public class Update_TimeSheetActivity extends AppCompatActivity {

    TextView back,display_date,display_weekend;
    Spinner client,project;
    String[] s1=new String[]{"Cubicle Work","SH Client","Decathlon Client","Lee Cooper","Multiline ERP"};
    String[] s2=new String[]{"Mobile Application","Web Application","Exceloid Application","SH Application"};
    String weekend_string,task_name_string,mon_time_string,tue_time_string,wed_time_string,thu_time_string,fri_time_string,sat_time_string,sun_time_string,description_string,client_string,project_string;
    String mon_String,tue_String,wed_String,thu_String,fri_String,sat_String,sun_String,update_Value;
    String spinner_one,spinner_two;
    EditText task_name,mon_time,tue_time,wed_time,thu_time,fri_time,sat_time,sun_time,description;
    Button save_task;
    SQLiteDatabase database;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_timesheet);

        back=(TextView)findViewById(R.id.back_id);
        client=(Spinner) findViewById(R.id.client_spinner);
        project=(Spinner) findViewById(R.id.project_spinner);
        task_name=(EditText)findViewById(R.id.task_name);
        mon_time=(EditText)findViewById(R.id.mon_time);
        tue_time=(EditText)findViewById(R.id.tue_time);
        wed_time=(EditText)findViewById(R.id.wed_time);
        thu_time=(EditText)findViewById(R.id.thu_time);
        fri_time=(EditText)findViewById(R.id.fri_time);
        sat_time=(EditText)findViewById(R.id.sat_time);
        sun_time=(EditText)findViewById(R.id.sun_time);
        description=(EditText)findViewById(R.id.enter_description);
        save_task=(Button)findViewById(R.id.save_task);
        display_date=(TextView)findViewById(R.id.display_date);
        display_weekend=(TextView)findViewById(R.id.display_weekends);

        Bundle b = getIntent().getExtras();
        weekend_string = b.getString("weekend");
        mon_String = b.getString("monday");
        tue_String = b.getString("tuesday");
        wed_String = b.getString("wednesday");
        thu_String = b.getString("thursday");
        fri_String = b.getString("friday");
        sat_String = b.getString("saturday");
        sun_String = b.getString("sunday");
        update_Value = b.getString("id");
        id=Integer.parseInt(update_Value);
        display_weekend.setText(weekend_string);

        database = openOrCreateDatabase("time_Sheet", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS insert_values (Weekend VARCHAR, Monday VARCHAR, Tuesday VARCHAR, Wednesday VARCHAR,Thursday VARCHAR, Friday VARCHAR, Saturday VARCHAR, Sunday VARCHAR, Client VARCHAR, Project VARCHAR, Task VARCHAR, Mon_Time VARCHAR, Tue_Time VARCHAR, Wed_Time VARCHAR, Thu_Time VARCHAR, Fri_Time VARCHAR, Sat_Time VARCHAR, Sun_Time VARCHAR, Description VARCHAR(3000), Id INTEGER PRIMARY KEY);");
        database.close();

        ArrayAdapter<String> adp = new ArrayAdapter<String>(Update_TimeSheetActivity.this, R.layout.spinner,R.id.text_spinner, s1);
        client.setAdapter(adp);

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(Update_TimeSheetActivity.this, R.layout.spinner,R.id.text_spinner, s2);
        project.setAdapter(adp1);

        getting_DatabaseValues();

        int spinnerone = adp.getPosition(spinner_one);
        client.setSelection(spinnerone);
        int spinnertwo = adp1.getPosition(spinner_two);
        project.setSelection(spinnertwo);

        client.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                client_string = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                project_string = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(task_name.getText().toString()!=null && task_name.getText().toString().trim().length()!=0 && description.getText().toString()!=null && description.getText().toString().trim().length()!=0){

                    task_name_string=task_name.getText().toString();
                    if(mon_time.getText().toString().isEmpty()){
                        mon_time_string="0";
                    }else{
                        mon_time_string=mon_time.getText().toString();
                    }
                    if(tue_time.getText().toString().isEmpty()){
                        tue_time_string="0";
                    }else {
                        tue_time_string = tue_time.getText().toString();
                    }
                    if(wed_time.getText().toString().isEmpty()){
                        wed_time_string="0";
                    }else {
                        wed_time_string = wed_time.getText().toString();
                    }
                    if(thu_time.getText().toString().isEmpty()){
                        thu_time_string="0";
                    }else {
                        thu_time_string = thu_time.getText().toString();
                    }
                    if(fri_time.getText().toString().isEmpty()){
                        fri_time_string="0";
                    }else {
                        fri_time_string = fri_time.getText().toString();
                    }
                    if(sat_time.getText().toString().isEmpty()){
                        sat_time_string="0";
                    }else {
                        sat_time_string = sat_time.getText().toString();
                    }
                    if(sun_time.getText().toString().isEmpty()){
                        sun_time_string="0";
                    }else {
                        sun_time_string = sun_time.getText().toString();
                    }
                    description_string=description.getText().toString();

                    description_string=description_string.replace("'", "''");

                    try {
                        database = openOrCreateDatabase("time_Sheet", MODE_PRIVATE, null);
                        database.execSQL("UPDATE insert_values set Weekend='" + weekend_string + "',Monday='"+ mon_String +"',Tuesday='"+ tue_String +"',Wednesday='"+ wed_String +"',Thursday='"+ thu_String +"',Friday='"+ fri_String +"',Saturday='"+ sat_String +"',Sunday='"+ sun_String +"',Client='"+ client_string +"',Project='"+ project_string +"',Task='"+ task_name_string +"'," +
                                "Mon_Time='"+ mon_time_string +"',Tue_Time='"+ tue_time_string +"',Wed_Time='"+ wed_time_string +"',Thu_Time='"+ thu_time_string +"',Fri_Time='"+ fri_time_string +"',Sat_Time='"+ sat_time_string +"',Sun_Time='"+ sun_time_string +"',Description='"+ description_string +"' where Id='" + update_Value + "'");
                        database.close();
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }//  insert_Database();
                } else {
                    Toast.makeText(Update_TimeSheetActivity.this, "please fill the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getting_DatabaseValues(){
        database = openOrCreateDatabase("time_Sheet", MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM insert_values", null);
        try{
            if(cursor.getCount()!=0&&cursor!=null){
                if(cursor.moveToFirst()) {
                    do {
                        String s1=cursor.getString(19);
                        if(s1.equals(update_Value)){
                            task_name.setText(cursor.getString(10));
                            mon_time.setText(cursor.getString(11));
                            tue_time.setText(cursor.getString(12));
                            wed_time.setText(cursor.getString(13));
                            thu_time.setText(cursor.getString(14));
                            fri_time.setText(cursor.getString(15));
                            sat_time.setText(cursor.getString(16));
                            sun_time.setText(cursor.getString(17));
                            description.setText(cursor.getString(18));
                            spinner_one=cursor.getString(8);
                            spinner_two=cursor.getString(9);
                            // Toast.makeText(getContext(),s1,Toast.LENGTH_SHORT).show();
                        }else{
                            //  Toast.makeText(getContext(),"no records",Toast.LENGTH_SHORT).show();
                        }
                    } while (cursor.moveToNext());

                    cursor.close();
                }        }
            else{

            }

        }catch (Exception e){
           // Toast.makeText(getContext(),""+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
