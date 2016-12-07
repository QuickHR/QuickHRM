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

public class Shift_Information extends Fragment {

    TextView shift_rotation,shift_schedule,start_day,end_day,description;
    JSONParser jsonParser;
    String user_name,pass_word;
    String shift_String="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View shift=inflater.inflate(R.layout.shift_information,container,false);

        shift_rotation=(TextView)shift.findViewById(R.id.shift_rotation);
        shift_schedule=(TextView)shift.findViewById(R.id.shift_schedule);
        start_day=(TextView)shift.findViewById(R.id.start_day);
        end_day=(TextView)shift.findViewById(R.id.end_day);
        description=(TextView)shift.findViewById(R.id.descriptions);

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
            if(shift_String!=null && shift_String.trim().length()!=0) {

                getString_ShiftData();

            }else {
                shift_Information();
            }
        }else {
          //  Toast.makeText(getContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
        }

        return  shift;
    }

    public void shift_Information() {

        JSONArray valuesobject = jsonParser.getJSONFromUrl(IPAddress_Class.URL_PROFILE_INFORMATION + "l=" + user_name + "&p=" + pass_word + "");
        try {
            if(valuesobject.length()>0) {

                JSONObject objectvalues = valuesobject.getJSONObject(8);

                if(objectvalues.length()>0){

                JSONObject shift_json = objectvalues.getJSONObject("shift");

                shift_String = "" + shift_json;

                    getString_ShiftData();
            } else { }}else { }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getString_ShiftData(){

        try{
            JSONObject shift_reader=new JSONObject(shift_String);

            if(!shift_reader.isNull("shiftRotation")){
                if(!shift_reader.getString("shiftRotation").equals("null")){
                    if(shift_reader.getString("shiftRotation").toString().length()>0){
                        shift_rotation.setText(shift_reader.getString("shiftRotation"));
                    }else{
                        shift_rotation.setText("- - -");
                    }
                }else{   shift_rotation.setText("- - -"); }
            }else{       shift_rotation.setText("- - -");  }

            if(!shift_reader.isNull("shiftSchedule")){
                if(!shift_reader.getString("shiftSchedule").equals("null")){
                    if(shift_reader.getString("shiftSchedule").toString().length()>0){
                        shift_schedule.setText(shift_reader.getString("shiftSchedule"));
                    }else{
                        shift_schedule.setText("- - -");
                    }
                }else{shift_schedule.setText("- - -");     }
            }else{    shift_schedule.setText("- - -");     }

            if(!shift_reader.isNull("startDay")){
                if(!shift_reader.getString("startDay").equals("null")){
                    if(shift_reader.getString("startDay").toString().length()>0){
                        start_day.setText(shift_reader.getString("startDay"));
                    }else{
                        start_day.setText("- - -");
                    }
                }else{  start_day.setText("- - -");   }
            }else{      start_day.setText("- - -");   }

            if(!shift_reader.isNull("endDay")){
                if(!shift_reader.getString("endDay").equals("null")){
                    if(shift_reader.getString("endDay").toString().length()>0){
                        end_day.setText(shift_reader.getString("endDay"));
                    }else{
                        end_day.setText("- - -");
                    }
                }else{  end_day.setText("- - -");   }
            }else{      end_day.setText("- - -");   }

            if(!shift_reader.isNull("description")){
                if(!shift_reader.getString("description").equals("null")){
                    if(shift_reader.getString("description").toString().length()>0){
                        description.setText(shift_reader.getString("description"));
                    }else{
                        description.setText("- - -");
                    }
                }else{  description.setText("- - -");   }
            }else{      description.setText("- - -");   }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
