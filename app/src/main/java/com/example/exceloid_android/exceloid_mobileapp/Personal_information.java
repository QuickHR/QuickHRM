package com.example.exceloid_android.exceloid_mobileapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Exceloid-Android on 26-09-2016.
 */

public class Personal_information extends Fragment {

    TextView first_name,middle_name,last_name,gender,dateof_birth,age,marital_status,blood_group,religion,citizenship;
    TextView ethnic_race,fathers_name,mothers_name,spouse_name,dependencies,placeof_birth,nick_name,pan_no,esi_no,unique_identity;
    TextView driving_license_no,driving_license_issue,driving_license_expiry,passport_no,passport_issue,passport_expiry;
    JSONParser jsonParser;
    String emp_Name,emp_Id,emp_Designation,user_name,pass_word;
    String personal_String="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View profile=inflater.inflate(R.layout.personal_information,null);

        first_name=(TextView) profile.findViewById(R.id.first_name);
        middle_name=(TextView)profile.findViewById(R.id.middle_name);
        last_name=(TextView)profile.findViewById(R.id.last_name);
        gender=(TextView)profile.findViewById(R.id.gender);
        dateof_birth=(TextView)profile.findViewById(R.id.dateof_birth);
        age=(TextView)profile.findViewById(R.id.age);
        marital_status=(TextView)profile.findViewById(R.id.marital_status);
        blood_group=(TextView)profile.findViewById(R.id.blood_group);
        religion=(TextView)profile.findViewById(R.id.religion);
        citizenship=(TextView)profile.findViewById(R.id.citizen_ship);
        ethnic_race=(TextView) profile.findViewById(R.id.ethnic_race);
        fathers_name=(TextView)profile.findViewById(R.id.fathers_name);
        mothers_name=(TextView)profile.findViewById(R.id.mothers_name);
        spouse_name=(TextView)profile.findViewById(R.id.spouse_name);
        dependencies=(TextView)profile.findViewById(R.id.dependencies);
        placeof_birth=(TextView) profile.findViewById(R.id.placeof_birth);
        nick_name=(TextView)profile.findViewById(R.id.nick_name);
        pan_no=(TextView)profile.findViewById(R.id.pan_num);
        esi_no=(TextView)profile.findViewById(R.id.esi_num);
        unique_identity=(TextView)profile.findViewById(R.id.unique_id);
        driving_license_no=(TextView) profile.findViewById(R.id.driving_license_no);
        driving_license_issue=(TextView)profile.findViewById(R.id.driving_license_issue_date);
        driving_license_expiry=(TextView)profile.findViewById(R.id.driving_license_expiry_date);
        passport_no=(TextView)profile.findViewById(R.id.passport_num);
        passport_issue=(TextView)profile.findViewById(R.id.passport_issue_date);
        passport_expiry=(TextView)profile.findViewById(R.id.passport_expiry_date);

        jsonParser=new JSONParser();

        DatabaseHandler db = new DatabaseHandler(getContext());
        HashMap<String, String> user = db.getUserDetails();
        user_name = user.get("user_name");
        pass_word = user.get("pass_word");

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ConnectivityManager cn=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true ) {

            if(personal_String!=null&&personal_String.trim().length()!=0){

                getPersonal_StrindData();

            }else{

                personal_Information();
            }

        }else {
           // Toast.makeText(getContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
        }
        return profile;
    }

    public void personal_Information(){

            JSONArray values_object=jsonParser.getJSONFromUrl(IPAddress_Class.URL_PROFILE_INFORMATION+"l="+ user_name +"&p="+ pass_word +"");
            try{

                if(values_object.length()>0) {

                    JSONObject personal_values = values_object.getJSONObject(1);

                    if (personal_values.length() > 0) {

                        JSONObject personal_json = personal_values.getJSONObject("personel");

                        personal_String = "" + personal_json;

                        getPersonal_StrindData();
                    } else{

                    }
                } else{

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    public void getPersonal_StrindData(){

        try{

            JSONObject reader=new JSONObject(personal_String);

            if(!reader.isNull("firstName")){
                if(!reader.getString("firstName").equals("null")){
                    if (reader.getString("firstName").toString().length()>0){
                        first_name.setText(reader.getString("firstName"));
                    }else{
                        first_name.setText("- - -");
                    }
                }else{  first_name.setText("- - -");  }
            }else{      first_name.setText("- - -");  }

            if(!reader.isNull("middleName")){
                if(!reader.getString("middleName").equals("null")){
                    if (reader.getString("middleName").toString().length()>0){
                        middle_name.setText(reader.getString("middleName"));
                    }else{
                        middle_name.setText("- - -");
                    }
                }else{   middle_name.setText("- - -");                     }
            }else{    middle_name.setText("- - -");    }

            if(!reader.isNull("lastName")){
                if(!reader.getString("lastName").equals("null")){
                    if (reader.getString("lastName").toString().length()>0){
                        last_name.setText(reader.getString("lastName"));
                    }else{
                        last_name.setText("- - -");
                    }
                }else{    last_name.setText("- - -");                }
            }else{     last_name.setText("- - -");   }

            if(!reader.isNull("gender")){
                if(!reader.getString("gender").equals("null")){
                    if (reader.getString("gender").toString().length()>0){
                        gender.setText(reader.getString("gender"));
                    }else{
                        gender.setText("- - -");
                    }
                }else{    gender.setText("- - -");                }
            }else{    gender.setText("- - -");    }

            if(!reader.isNull("dateOfBirth")){
                if(!reader.getString("dateOfBirth").equals("null")){
                    if (reader.getString("dateOfBirth").toString().length()>0){
                        dateof_birth.setText(reader.getString("dateOfBirth"));
                    }else{
                        dateof_birth.setText("- - -");
                    }
                }else{    dateof_birth.setText("- - -");                }
            }else{     dateof_birth.setText("- - -");   }

            if(!reader.isNull("age")){
                if(!reader.getString("age").equals("null")){
                    if (reader.getString("age").toString().length()>0){
                        age.setText(reader.getString("age"));
                    }else{
                        age.setText("- - -");
                    }
                }else{    age.setText("- - -");                }
            }else{     age.setText("- - -");   }

            if(!reader.isNull("maritialStatus")){
                if(!reader.getString("maritialStatus").equals("null")){
                    if (reader.getString("maritialStatus").toString().length()>0){
                        marital_status.setText(reader.getString("maritialStatus"));
                    }else{
                        marital_status.setText("- - -");
                    }
                }else{    marital_status.setText("- - -");                }
            }else{     marital_status.setText("- - -");   }

            if(!reader.isNull("bloodGroup")){
                if(!reader.getString("bloodGroup").equals("null")){
                    if (reader.getString("bloodGroup").toString().length()>0){
                        blood_group.setText(reader.getString("bloodGroup"));
                    }else{
                        blood_group.setText("- - -");
                    }
                }else{     blood_group.setText("- - -");               }
            }else{      blood_group.setText("- - -");  }

            if(!reader.isNull("religion")){
                if(!reader.getString("religion").equals("null")){
                    if (reader.getString("religion").toString().length()>0){
                        religion.setText(reader.getString("religion"));
                    }else{
                        religion.setText("- - -");
                    }
                }else{     religion.setText("- - -");               }
            }else{    religion.setText("- - -");    }

            if(!reader.isNull("citizenShip")){
                if(!reader.getString("citizenShip").equals("null")){
                    if (reader.getString("citizenShip").toString().length()>0){
                        citizenship.setText(reader.getString("citizenShip"));
                    }else{
                        citizenship.setText("- - -");
                    }
                }else{    citizenship.setText("- - -");                }
            }else{     citizenship.setText("- - -");   }

            if(!reader.isNull("ethnicRace")){
                if(!reader.getString("ethnicRace").equals("null")){
                    if (reader.getString("ethnicRace").toString().length()>0){
                        ethnic_race.setText(reader.getString("ethnicRace"));
                    }else{
                        ethnic_race.setText("- - -");
                    }
                }else{     ethnic_race.setText("- - -");               }
            }else{    ethnic_race.setText("- - -");    }

            if(!reader.isNull("fatherName")){
                if(!reader.getString("fatherName").equals("null")){
                    if (reader.getString("fatherName").toString().length()>0){
                        fathers_name.setText(reader.getString("fatherName"));
                    }else{
                        fathers_name.setText("- - -");
                    }
                }else{     fathers_name.setText("- - -");               }
            }else{      fathers_name.setText("- - -");  }

            if(!reader.isNull("motherName")){
                if(!reader.getString("motherName").equals("null")){
                    if (reader.getString("motherName").toString().length()>0){
                        mothers_name.setText(reader.getString("motherName"));
                    }else{
                        mothers_name.setText("- - -");
                    }
                }else{     mothers_name.setText("- - -");               }
            }else{     mothers_name.setText("- - -");   }

            if(!reader.isNull("spouse")){
                if(!reader.getString("spouse").equals("null")){
                    if (reader.getString("spouse").toString().length()>0){
                        spouse_name.setText(reader.getString("spouse"));
                    }else{
                        spouse_name.setText("- - -");
                    }
                }else{   spouse_name.setText("- - -");                 }
            }else{     spouse_name.setText("- - -");   }

            if(!reader.isNull("noOfDependencies")){
                if(!reader.getString("noOfDependencies").equals("null")){
                    if (reader.getString("noOfDependencies").toString().length()>0){
                        dependencies.setText(reader.getString("noOfDependencies"));
                    }else{
                        dependencies.setText("- - -");
                    }
                }else{    dependencies.setText("- - -");                }
            }else{     dependencies.setText("- - -");   }

            if(!reader.isNull("placeOfBirth")){
                if(!reader.getString("placeOfBirth").equals("null")){
                    if (reader.getString("placeOfBirth").toString().length()>0){
                        placeof_birth.setText(reader.getString("placeOfBirth"));
                    }else{
                        placeof_birth.setText("- - -");
                    }
                }else{   placeof_birth.setText("- - -");                 }
            }else{     placeof_birth.setText("- - -");   }

            if(!reader.isNull("nickName")){
                if(!reader.getString("nickName").equals("null")){
                    if (reader.getString("firstName").toString().length()>0){
                        nick_name.setText(reader.getString("nickName"));
                    }else{
                        nick_name.setText("- - -");
                    }
                }else{    nick_name.setText("- - -");                }
            }else{     nick_name.setText("- - -");   }

            if(!reader.isNull("panNo")){
                if(!reader.getString("panNo").equals("null")){
                    if (reader.getString("panNo").toString().length()>0){
                        pan_no.setText(reader.getString("panNo"));
                    }else{
                        pan_no.setText("- - -");
                    }
                }else{  pan_no.setText("- - -");              }
            }else{     pan_no.setText("- - -");   }

            if(!reader.isNull("esiNo")){
                if(!reader.getString("esiNo").equals("null")){
                    if (reader.getString("esiNo").toString().length()>0){
                        esi_no.setText(reader.getString("esiNo"));
                    }else{
                        esi_no.setText("- - -");
                    }
                }else{     esi_no.setText("- - -");               }
            }else{     esi_no.setText("- - -");   }

            if(!reader.isNull("uniqueIdentityNo")){
                if(!reader.getString("uniqueIdentityNo").equals("null")){
                    if (reader.getString("uniqueIdentityNo").toString().length()>0){
                        unique_identity.setText(reader.getString("uniqueIdentityNo"));
                    }else{
                        unique_identity.setText("- - -");
                    }
                }else{    unique_identity.setText("- - -");                }
            }else{     unique_identity.setText("- - -");   }

            if(!reader.isNull("drivingLicenceNo")){
                if(!reader.getString("drivingLicenceNo").equals("null")){
                    if (reader.getString("drivingLicenceNo").toString().length()>0){
                        driving_license_no.setText(reader.getString("drivingLicenceNo"));
                    }else{
                        driving_license_no.setText("- - -");
                    }
                }else{  driving_license_no.setText("- - -"); }
            }else{    driving_license_no.setText("- - -");    }

            if(!reader.isNull("drivingLicenceIssueDate")){
                if(!reader.getString("drivingLicenceIssueDate").equals("null")){
                    if (reader.getString("drivingLicenceIssueDate").toString().length()>0){
                        driving_license_issue.setText(reader.getString("drivingLicenceIssueDate"));
                    }else{
                        driving_license_issue.setText("- - -");
                    }
                }else{    driving_license_issue.setText("- - -");                }
            }else{   driving_license_issue.setText("- - -");     }

            if(!reader.isNull("drivingLicenceExpiryDate")){
                if(!reader.getString("drivingLicenceExpiryDate").equals("null")){
                    if (reader.getString("drivingLicenceExpiryDate").toString().length()>0){
                        driving_license_expiry.setText(reader.getString("drivingLicenceExpiryDate"));
                    }else{
                        driving_license_expiry.setText("- - -");
                    }
                }else{ driving_license_expiry.setText("- - -");                }
            }else{    driving_license_expiry.setText("- - -");    }

            if(!reader.isNull("passportNo")){
                if(!reader.getString("passportNo").equals("null")){
                    if (reader.getString("passportNo").toString().length()>0){
                        passport_no.setText(reader.getString("passportNo"));
                    }else{
                        passport_no.setText("- - -");
                    }
                }else{     passport_no.setText("- - -");               }
            }else{     passport_no.setText("- - -");   }

            if(!reader.isNull("passportIssueDate")){
                if(!reader.getString("passportIssueDate").equals("null")){
                    if (reader.getString("passportIssueDate").toString().length()>0){
                        passport_issue.setText(reader.getString("passportIssueDate"));
                    }else{
                        passport_issue.setText("- - -");
                    }
                }else{     passport_issue.setText("- - -");               }
            }else{    passport_issue.setText("- - -");    }

            if(!reader.isNull("passportExpiryDate")){
                if(!reader.getString("passportExpiryDate").equals("null")){
                    if (reader.getString("passportExpiryDate").toString().length()>0){
                        passport_expiry.setText(reader.getString("passportExpiryDate"));
                    }else{
                        passport_expiry.setText("- - -");
                    }
                }else{  passport_expiry.setText("- - -");              }
            }else{    passport_expiry.setText("- - -");    }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
