package com.example.exceloid_android.exceloid_mobileapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Exceloid-Android on 04-10-2016.
 */

public class Dictionary_Activity extends AppCompatActivity {

    TextView first,last,middle,email,phone;
    JSONParser jsonParser;
    String user_name,pass_word,name;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_activity);
        setupActionBar();

        first=(TextView)findViewById(R.id.dictionary_firstname);
        last=(TextView)findViewById(R.id.dictionary_lastname);
        middle=(TextView)findViewById(R.id.dictionary_middlename);
        email=(TextView)findViewById(R.id.dictionary_email);
        phone=(TextView)findViewById(R.id.dictionary_phonenum);

        jsonParser=new JSONParser();

        DatabaseHandler db = new DatabaseHandler(this);
        HashMap<String, String> user = db.getUserDetails();
        user_name = user.get("user_name");
        pass_word = user.get("pass_word");

        Bundle b = getIntent().getExtras();
        i = b.getInt("int");
        name=b.getString("name");

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true ) {
          //   new Get_Information().execute();
            dictionary_employ_Information();
        }else {
            Toast.makeText(getApplicationContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
        }
    }

    public void dictionary_employ_Information(){

        JSONArray valuesobject=jsonParser.getJSONFromUrl(IPAddress_Class.URL_DICTIONARY_INFORMATION+"l="+ user_name +"&p="+ pass_word +"");
        try{

            for(int s=0;s<valuesobject.length();s++){

               // JSONObject js=valuesobject.getJSONObject(s);
                JSONObject objectvalues=valuesobject.getJSONObject(s);

                String name1=objectvalues.getString("firstName");

                if(name.equals(name1)){

                    if(!objectvalues.isNull("firstName")){
                        if(!objectvalues.getString("firstName").equals("null")){
                            if (objectvalues.getString("firstName").toString().length()>0){
                                first.setText(objectvalues.getString("firstName"));
                            }else{
                                first.setText("- - -");
                            }
                        }else{  first.setText("- - -");  }
                    }else{      first.setText("- - -");  }

                    if(!objectvalues.isNull("middleName")){
                        if(!objectvalues.getString("middleName").equals("null")){
                            if (objectvalues.getString("middleName").toString().length()>0){
                                middle.setText(objectvalues.getString("middleName"));
                            }else{
                                middle.setText("- - -");
                            }
                        }else{  middle.setText("- - -");  }
                    }else{      middle.setText("- - -");  }

                    if(!objectvalues.isNull("lastName")){
                        if(!objectvalues.getString("lastName").equals("null")){
                            if (objectvalues.getString("lastName").toString().length()>0){
                                last.setText(objectvalues.getString("lastName"));
                            }else{
                                last.setText("- - -");
                            }
                        }else{  last.setText("- - -");  }
                    }else{      last.setText("- - -");  }

                    if(!objectvalues.isNull("email")){
                        if(!objectvalues.getString("email").equals("null")){
                            if (objectvalues.getString("email").toString().length()>0){
                                email.setText(objectvalues.getString("email"));
                            }else{
                                email.setText("- - -");
                            }
                        }else{  email.setText("- - -");  }
                    }else{      email.setText("- - -");  }

                    if(!objectvalues.isNull("phoneNo")){
                        if(!objectvalues.getString("phoneNo").equals("null")){
                            if (objectvalues.getString("phoneNo").toString().length()>0){
                                phone.setText(objectvalues.getString("phoneNo"));
                            }else{
                                phone.setText("- - -");
                            }
                        }else{  phone.setText("- - -");  }
                    }else{      phone.setText("- - -");  }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
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

    public class Get_Information extends AsyncTask<String,String,JSONArray> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Dictionary_Activity.this);
            progressDialog.setMessage("Getting Data...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray valuesobject=jsonParser.getJSONFromUrl(IPAddress_Class.URL_DICTIONARY_INFORMATION+"l="+ user_name +"&p="+ pass_word +"");
            return valuesobject;
        }
        @Override
        protected void onPostExecute(JSONArray valuesobject) {
            // super.onPostExecute(values_object);
            progressDialog.dismiss();
            try{
                JSONObject objectvalues=valuesobject.getJSONObject(i);

                // JSONObject json=objectvalues.getJSONObject("profile");

                first.setText(objectvalues.getString("firstName"));
                middle.setText(objectvalues.getString("middleName"));
                last.setText(objectvalues.getString("lastName"));
                email.setText(objectvalues.getString("email"));
                phone.setText(objectvalues.getString("phoneNo"));

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
