package com.example.exceloid_android.exceloid_mobileapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.os.Handler;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Exceloid-Android on 20-09-2016.
 */
public class Login_Activity extends ActionBarActivity {

    EditText username,password;
    Button login;
    UserFunctions userFunctions;
    private static int SPLASH_TIME_OUT = 3000;
    private String user_name,pass_word,success;
    private Animation animShow;
    LinearLayout linearLayout,layout;
    JSONParser jsonParser;
    InputStream is = null;
    String result = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username=(EditText)findViewById(R.id.user_name);
        password=(EditText)findViewById(R.id.pass_word);
        login=(Button)findViewById(R.id.exceloid_login);
        linearLayout=(LinearLayout)findViewById(R.id.linear_layout);
        layout=(LinearLayout)findViewById(R.id.layout);
        userFunctions = new UserFunctions();
        jsonParser = new JSONParser();
        animShow = AnimationUtils.loadAnimation(this, R.anim.view_show);

        if (android.os.Build.VERSION.SDK_INT >9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(userFunctions.isUserLoggedIn(getApplicationContext())) {
                    Intent login = new Intent(getApplicationContext(), MainActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    finish();
                }
                else {
                    layout.startAnimation(animShow);
                    linearLayout.startAnimation(animShow);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        }, SPLASH_TIME_OUT);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_name = username.getText().toString();
                pass_word = password.getText().toString();

                ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nf = cn.getActiveNetworkInfo();

                if (user_name != null && pass_word != null && user_name.length() != 0 && pass_word.length() != 0) {

                    if (nf != null && nf.isConnected() == true) {

                        new MyAsync_Login().execute();

                    } else {

                     Toast.makeText(Login_Activity.this, "Network is Not Available!", Toast.LENGTH_SHORT).show();

                    }
                } else if(user_name==null && user_name.length() != 0){
                    username.setError("Invalid");
                }else if(pass_word==null && pass_word.length() != 0){
                    password.setError("Invalid");
                }else {
                        username.setError("Invalid");
                        password.setError("Invalid");
                }  }
        });    }

    class MyAsync_Login extends AsyncTask<Void, Integer, Void> {

        boolean running;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //Do something...
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;
            progressDialog = ProgressDialog.show(Login_Activity.this, null, "Please Wait!");
            progressDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            insert_Loginvalues();
            progressDialog.dismiss();
        }
    }

    public void insert_Loginvalues() {

        ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

        nameValuePair.add(new BasicNameValuePair("empcode", user_name));
        nameValuePair.add(new BasicNameValuePair("password", pass_word));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(IPAddress_Class.URL_LOGIN+"l=" + user_name + "&p=" + pass_word +"");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (Exception e) {

            Toast.makeText(Login_Activity.this, "Invalid IP Address!", Toast.LENGTH_SHORT).show();

        }
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {

                builder.append(line + "\n");
            }
            is.close();
            result = builder.toString();

        } catch (Exception e) {
            // TODO: handle exception
            Toast.makeText(Login_Activity.this, "Opps! An error occured in connection to server!", Toast.LENGTH_SHORT).show();
        }

        try {

            JSONObject jsonObject = new JSONObject(result);

            success = (jsonObject.getString("success"));

            Toast.makeText(getApplicationContext(),""+success,Toast.LENGTH_SHORT).show();

            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
            db.addUser(user_name, pass_word);

            progressDialog.dismiss();

            username.setText("");
            password.setText("");

            Intent login=new Intent(Login_Activity.this,MainActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            finish();

        } catch (Exception e) {
            progressDialog.dismiss();
            Toast.makeText(Login_Activity.this, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
           // username.setError("Wrong Credentials!");
           // password.setError("Wrong Credentials!");

        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        Login_Activity.this.finish();
        moveTaskToBack(true);
        super.onBackPressed();
    }
}
