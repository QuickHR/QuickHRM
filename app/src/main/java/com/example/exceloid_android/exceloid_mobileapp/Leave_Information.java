package com.example.exceloid_android.exceloid_mobileapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Exceloid-Android on 26-09-2016.
 */

public class Leave_Information extends Fragment {

    TextView leave_type,available_leaves,encashed_leaves,leaves_taken;
    JSONParser jsonParser;
    String user_name,pass_word;
    String leave_String="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View leave=inflater.inflate(R.layout.leave_information,container,false);

        leave_type=(TextView)leave.findViewById(R.id.leave_type);
        available_leaves=(TextView)leave.findViewById(R.id.available_leaves);
        encashed_leaves=(TextView)leave.findViewById(R.id.encashed_leaves);
        leaves_taken=(TextView)leave.findViewById(R.id.leaves_taken);

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
            //   new GetProfile_Information().execute();
            if(leave_String!=null&&leave_String.trim().length()!=0){
                getString_LeaveData();
            }else {
                leave_Information();
            }
        }else {
           // Toast.makeText(getContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
        }

        return  leave;
    }

    public void leave_Information() {

        JSONArray valuesobject = jsonParser.getJSONFromUrl(IPAddress_Class.URL_PROFILE_INFORMATION + "l=" + user_name + "&p=" + pass_word + "");
        try {

            if(valuesobject.length()>0) {

                JSONObject objectvalues = valuesobject.getJSONObject(7);

                if(objectvalues.length()>0){

                JSONObject leave_json = objectvalues.getJSONObject("leave");

                    leave_String=""+leave_json;

                    getString_LeaveData();

                } else { }}else{
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getString_LeaveData(){

        try{

            JSONObject leave_reader = new JSONObject(leave_String);

            if(!leave_reader.isNull("leaveType")){
                if(!leave_reader.getString("leaveType").equals("null")){
                    if(leave_reader.getString("leaveType").toString().length()>0){
                        leave_type.setText(leave_reader.getString("leaveType"));
                    }else{
                        leave_type.setText("- - -");
                    }
                }else{   leave_type.setText("- - -"); }
            }else{       leave_type.setText("- - -");  }

            if(!leave_reader.isNull("availableLeaves")){
                if(!leave_reader.getString("availableLeaves").equals("null")){
                    if(leave_reader.getString("availableLeaves").toString().length()>0){
                        available_leaves.setText(leave_reader.getString("availableLeaves"));
                    }else{
                        available_leaves.setText("- - -");
                    }
                }else{available_leaves.setText("- - -");     }
            }else{    available_leaves.setText("- - -");     }

            if(!leave_reader.isNull("encashedLeaves")){
                if(!leave_reader.getString("encashedLeaves").equals("null")){
                    if(leave_reader.getString("encashedLeaves").toString().length()>0){
                        encashed_leaves.setText(leave_reader.getString("encashedLeaves"));
                    }else{
                        encashed_leaves.setText("- - -");
                    }
                }else{  encashed_leaves.setText("- - -");   }
            }else{      encashed_leaves.setText("- - -");   }

            if(!leave_reader.isNull("leavesTaken")){
                if(!leave_reader.getString("leavesTaken").equals("null")){
                    if(leave_reader.getString("leavesTaken").toString().length()>0){
                        leaves_taken.setText(leave_reader.getString("leavesTaken"));
                    }else{
                        leaves_taken.setText("- - -");
                    }
                }else{  leaves_taken.setText("- - -");   }
            }else{      leaves_taken.setText("- - -");   }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
