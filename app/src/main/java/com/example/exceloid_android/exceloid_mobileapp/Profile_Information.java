package com.example.exceloid_android.exceloid_mobileapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Profile_Information extends Fragment {

    TextView employee_name,employee_id,designation;
    JSONParser jsonParser;
    String user_name,pass_word;
    String profile_String="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View profile=inflater.inflate(R.layout.profile_information,null);

        employee_name=(TextView)profile.findViewById(R.id.employee_name);
        employee_id=(TextView)profile.findViewById(R.id.employee_id);
        designation=(TextView)profile.findViewById(R.id.designation);
        jsonParser=new JSONParser();

        DatabaseHandler db = new DatabaseHandler(getContext());
        HashMap<String, String> user = db.getUserDetails();
        user_name = user.get("user_name");
        pass_word = user.get("pass_word");

       // Toast.makeText(getContext(), user_name+"="+pass_word, Toast.LENGTH_SHORT).show();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ConnectivityManager cn=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true ) {

            if(profile_String!=null&&profile_String.trim().length()!=0){

                getProfiele_StringData();

            }else{

                new GetProfile_Information().execute();
               // profile_Information();
            }

        }else {
            Toast.makeText(getContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
        }

        return profile;
    }

    public class GetProfile_Information extends AsyncTask<String,String,JSONArray>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Getting Data...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected JSONArray doInBackground(String... params) {
            JSONArray values_object=jsonParser.getJSONFromUrl(IPAddress_Class.URL_PROFILE_INFORMATION+"l="+ user_name +"&p="+ pass_word +"");
            return values_object;
        }
        @Override
        protected void onPostExecute(JSONArray values_object) {
           // super.onPostExecute(values_object);
            progressDialog.dismiss();
            try{
                if(values_object.length()>0){

                    JSONObject objectvalues=values_object.getJSONObject(0);

                    if(objectvalues.length()>0){

                        JSONObject json=objectvalues.getJSONObject("profile");

                        profile_String=""+json;

                        getProfiele_StringData();

                    } else{

                    }}else{                }

            }catch (Exception e){
                e.printStackTrace();
            }       }
    }
    public void profile_Information(){

            JSONArray values_obj=jsonParser.getJSONFromUrl(IPAddress_Class.URL_PROFILE_INFORMATION+"l="+ user_name +"&p="+ pass_word +"");
            try{
                if(values_obj.length()>0){

                JSONObject objectvalues=values_obj.getJSONObject(0);

                if(objectvalues.length()>0){

                    JSONObject json=objectvalues.getJSONObject("profile");

                    profile_String=""+json;

                    getProfiele_StringData();

                } else{

                }}else{

                }

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public void  getProfiele_StringData(){

        try{

            JSONObject reader = new JSONObject(profile_String);

            if(!reader.isNull("empName")){
                if(!reader.getString("empName").equals("null")){
                    if(reader.getString("empName").toString().length()>0){
                        employee_name.setText(reader.getString("empName"));
                    }else{
                        employee_name.setText("- - -");
                    }
                }else{   employee_name.setText("- - -"); }
            }else{       employee_name.setText("- - -");  }

            if(!reader.isNull("empId")){
                if(!reader.getString("empId").equals("null")){
                    if(reader.getString("empId").toString().length()>0){
                        employee_id.setText(reader.getString("empId"));
                    }else{
                        employee_id.setText("- - -");
                    }
                }else{employee_id.setText("- - -");     }
            }else{    employee_id.setText("- - -");     }

            if(!reader.isNull("designation")){
                if(!reader.getString("designation").equals("null")){
                    if(reader.getString("designation").toString().length()>0){
                        designation.setText(reader.getString("designation"));
                    }else{
                        designation.setText("- - -");
                    }
                }else{  designation.setText("- - -");   }
            }else{      designation.setText("- - -");   }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
