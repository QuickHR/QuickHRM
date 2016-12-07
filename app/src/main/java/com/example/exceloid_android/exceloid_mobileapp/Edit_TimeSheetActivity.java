package com.example.exceloid_android.exceloid_mobileapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Exceloid-Android on 13-10-2016.
 */

public class Edit_TimeSheetActivity extends AppCompatActivity {

    TextView back,display_date,display_weekend;
    Spinner client,project;
    String[] s1=new String[]{"Cubicle Work","SH Client","Decathlon Client","Lee Cooper","Multiline ERP"};
    String[] s2=new String[]{"Mobile Application","Web Application","Exceloid Application","SH Application"};
    String weekend_string,task_name_string,mon_time_string,tue_time_string,wed_time_string,thu_time_string,fri_time_string,sat_time_string,sun_time_string,description_string,client_string,project_string;
    String mon_String,tue_String,wed_String,thu_String,fri_String,sat_String,sun_String;
    EditText task_name,mon_time,tue_time,wed_time,thu_time,fri_time,sat_time,sun_time,description;
    Button save_task;
    SQLiteDatabase database;

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
        display_weekend.setText(weekend_string);

        database = openOrCreateDatabase("time_Sheet", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS insert_values (Weekend VARCHAR, Monday VARCHAR, Tuesday VARCHAR, Wednesday VARCHAR,Thursday VARCHAR, Friday VARCHAR, Saturday VARCHAR, Sunday VARCHAR, Client VARCHAR, Project VARCHAR, Task VARCHAR, Mon_Time VARCHAR, Tue_Time VARCHAR, Wed_Time VARCHAR, Thu_Time VARCHAR, Fri_Time VARCHAR, Sat_Time VARCHAR, Sun_Time VARCHAR, Description VARCHAR(3000), Id INTEGER PRIMARY KEY);");
        database.close();

        ArrayAdapter<String> adp=new ArrayAdapter<String>(Edit_TimeSheetActivity.this,R.layout.spinner,R.id.text_spinner,s1);
        client.setAdapter(adp);

        ArrayAdapter<String> adp1=new ArrayAdapter<String>(Edit_TimeSheetActivity.this,R.layout.spinner,R.id.text_spinner,s2);
        project.setAdapter(adp1);

        client.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                client_string=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                project_string=parent.getItemAtPosition(position).toString();

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
                        database.execSQL("INSERT INTO insert_values VALUES('" + weekend_string + "','" + mon_String + "','" + tue_String +"','" + wed_String + "','" + thu_String + "','" + fri_String + "','" + sat_String + "','" + sun_String + "','" + client_string + "', '" + project_string + "','" + task_name_string + "', '" + mon_time_string + "','" + tue_time_string + "','" + wed_time_string + "','" + thu_time_string + "','"+ fri_time_string +"','" + sat_time_string + "','" + sun_time_string + "','" + description_string + "',null);");
                        database.close();
                        finish();
                    }catch (Exception e){
                        //Toast.makeText(getApplicationContext(),""+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        //System.out.println("errrorr....."+e.getLocalizedMessage());
                        e.printStackTrace();
                    }//  insert_Database();
                }else{
                    Toast.makeText(Edit_TimeSheetActivity.this, "please check your fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void insert_Database(){

       /* database = openOrCreateDatabase("time_Sheet", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS insert_values (Weekend varchar, Day varchar, Client varchar, Project varchar, Task varchar, Time varchar, Description varchar);");
        database.close();

        Boolean record_exists = false;
        String line_values_updatequantity;
        try {
            database = openOrCreateDatabase("time_Sheet", MODE_PRIVATE, null);
            line_values_cursor = database.rawQuery("SELECT * FROM insert_values", null);
            if (line_values_cursor.getCount() != 0 && line_values_cursor != null) {
                line_values_cursor.moveToFirst();
                do {
                    if (line_values_cursor.getString(0).equalsIgnoreCase(line_values_spinnername)) {
                        record_exists = true;
                        line_values_spinnername = line_values_cursor.getString(0);
                        line_values_editquantity = line_values_cursor.getString(1);
                    }
                } while (line_values_cursor.moveToNext());
                if (record_exists == true) {
                    line_values_updatequantity=line_values_quantity.getText().toString();
                    if(line_values_updatequantity!=null && line_values_spinnername!=null && line_values_updatequantity.trim().length() != 0 && line_values_spinnername.trim().length() != 0){
                        database = openOrCreateDatabase("parent_LineProduction", MODE_PRIVATE, null);
                        database.execSQL("UPDATE line_values set Qty='" + line_values_updatequantity + "' where Name='" + line_values_spinnername + "'");
                        database.close();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "please check your fields", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (record_exists == false) {
                    if(line_values_editquantity!=null && line_values_spinnername!=null && line_values_editquantity.trim().length() != 0 && line_values_spinnername.trim().length() != 0){
                        database = openOrCreateDatabase("parent_LineProduction", MODE_PRIVATE, null);
                        database.execSQL("INSERT INTO line_values VALUES('" + line_values_spinnername + "' , '" + line_values_editquantity + "','"+ userId +"');");
                        database.close();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"please check your fields",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else if(line_values_editquantity!=null && line_values_spinnername!=null && line_values_editquantity.trim().length() != 0 && line_values_spinnername.trim().length() != 0
                    && userId!=null &&userId.trim().length()!=0) {
                Log.e("Database", "empty");
                database = openOrCreateDatabase("parent_LineProduction", MODE_PRIVATE, null);
                database.execSQL("INSERT INTO line_values VALUES('" + line_values_spinnername + "' , '" + line_values_editquantity + "','"+userId+"');");
                database.close();
            }
            else{
                Toast.makeText(getApplicationContext(),"please check your fields",Toast.LENGTH_SHORT).show();
            }}
        catch (Exception e) {

            Toast.makeText(Edit_TimeSheetActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        //line_values_quantity.setText("");*/

    }

}
