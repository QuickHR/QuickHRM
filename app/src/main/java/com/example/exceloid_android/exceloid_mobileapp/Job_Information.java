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

public class Job_Information extends Fragment{

    TextView designation,employee_deprtment,team,employee_category,pay_grade,work_shift,define_weekends,leave_policy,payment_method,financial_account;
    TextView dateof_joining,probation_period,confirmation_date,retirement_date,relieved_date,reasonfor_leaving,yearsof_service;
    JSONParser jsonParser;
    String user_name,pass_word;
    String job_String="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View job=inflater.inflate(R.layout.job_information,null);

        designation=(TextView) job.findViewById(R.id.designation);
        employee_deprtment=(TextView)job.findViewById(R.id.employ_department);
        team=(TextView)job.findViewById(R.id.team);
        employee_category=(TextView)job.findViewById(R.id.employee_categeory);
        pay_grade=(TextView)job.findViewById(R.id.pay_grade);
        work_shift=(TextView) job.findViewById(R.id.work_shift);
        define_weekends=(TextView)job.findViewById(R.id.define_weekends);
        leave_policy=(TextView)job.findViewById(R.id.leave_policy);
        payment_method=(TextView)job.findViewById(R.id.payment_method);
        financial_account=(TextView)job.findViewById(R.id.financial_account);
        dateof_joining=(TextView) job.findViewById(R.id.dateof_joining);
        probation_period=(TextView)job.findViewById(R.id.probation_period);
        confirmation_date=(TextView)job.findViewById(R.id.confirmation_date);
        retirement_date=(TextView)job.findViewById(R.id.retirement_date);
        relieved_date=(TextView)job.findViewById(R.id.relieved_date);
        reasonfor_leaving=(TextView) job.findViewById(R.id.reasonfor_leaving);
        yearsof_service=(TextView)job.findViewById(R.id.yearsof_service);

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
          if(job_String!=null && job_String.trim().length()!=0) {
              getString_JobData();
          } else{
              job_Information();
          }
        }else {
          //  Toast.makeText(getContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
        }

        return  job;
    }

    public void job_Information() {

        JSONArray valuesobject = jsonParser.getJSONFromUrl(IPAddress_Class.URL_PROFILE_INFORMATION + "l=" + user_name + "&p=" + pass_word + "");
        try {

            if(valuesobject.length()>0){

                JSONObject objectvalues = valuesobject.getJSONObject(3);

                if(objectvalues.length()>0) {

                    JSONObject json = objectvalues.getJSONObject("job");

                    job_String = "" + json;
                    getString_JobData();
                }else{ }

            }else{   }

            //  work_shift.setText(json.getString("availableLeaves"));
            //  payment_method.setText(json.getString("paygradeName"));
            //  financial_account.setText(json.getString("availableLeaves"));
            //  relieved_date.setText(json.getString("encashedLeaves"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getString_JobData(){

        try{
            JSONObject reader=new JSONObject(job_String);

            if(!reader.isNull("designation")){
                if(!reader.getString("designation").equals("null")){
                    if (reader.getString("designation").toString().length()>0){
                        designation.setText(reader.getString("designation"));
                    }else{
                        designation.setText("- - -");
                    }
                }else{  designation.setText("- - -");  }
            }else{      designation.setText("- - -");  }

            if(!reader.isNull("departmentName")){
                if(!reader.getString("departmentName").equals("null")){
                    if (reader.getString("departmentName").toString().length()>0){
                        employee_deprtment.setText(reader.getString("departmentName"));
                    }else{
                        employee_deprtment.setText("- - -");
                    }
                }else{   employee_deprtment.setText("- - -");                     }
            }else{    employee_deprtment.setText("- - -");    }

            if(!reader.isNull("teamName")){
                if(!reader.getString("teamName").equals("null")){
                    if (reader.getString("teamName").toString().length()>0){
                        team.setText(reader.getString("teamName"));
                    }else{
                        team.setText("- - -");
                    }
                }else{    team.setText("- - -");                }
            }else{     team.setText("- - -");   }

            if(!reader.isNull("employeeCategoryName")){
                if(!reader.getString("employeeCategoryName").equals("null")){
                    if (reader.getString("employeeCategoryName").toString().length()>0){
                        employee_category.setText(reader.getString("employeeCategoryName"));
                    }else{
                        employee_category.setText("- - -");
                    }
                }else{    employee_category.setText("- - -");                }
            }else{    employee_category.setText("- - -");    }

            if(!reader.isNull("paygradeName")){
                if(!reader.getString("paygradeName").equals("null")){
                    if (reader.getString("paygradeName").toString().length()>0){
                        pay_grade.setText(reader.getString("paygradeName"));
                    }else{
                        pay_grade.setText("- - -");
                    }
                }else{    pay_grade.setText("- - -");                }
            }else{     pay_grade.setText("- - -");   }

            if(!reader.isNull("weekends")){
                if(!reader.getString("weekends").equals("null")){
                    if (reader.getString("weekends").toString().length()>0){
                        define_weekends.setText(reader.getString("weekends"));
                    }else{
                        define_weekends.setText("- - -");
                    }
                }else{    define_weekends.setText("- - -");                }
            }else{     define_weekends.setText("- - -");   }

            if(!reader.isNull("leavePolicy")){
                if(!reader.getString("leavePolicy").equals("null")){
                    if (reader.getString("leavePolicy").toString().length()>0){
                        leave_policy.setText(reader.getString("leavePolicy"));
                    }else{
                        leave_policy.setText("- - -");
                    }
                }else{    leave_policy.setText("- - -");                }
            }else{     leave_policy.setText("- - -");   }

            if(!reader.isNull("dateOOfJoining")){
                if(!reader.getString("dateOOfJoining").equals("null")){
                    if (reader.getString("dateOOfJoining").toString().length()>0){
                        dateof_joining.setText(reader.getString("dateOOfJoining"));
                    }else{
                        dateof_joining.setText("- - -");
                    }
                }else{     dateof_joining.setText("- - -");               }
            }else{      dateof_joining.setText("- - -");  }

            if(!reader.isNull("probationPeriod")){
                if(!reader.getString("probationPeriod").equals("null")){
                    if (reader.getString("probationPeriod").toString().length()>0){
                        probation_period.setText(reader.getString("probationPeriod"));
                    }else{
                        probation_period.setText("- - -");
                    }
                }else{     probation_period.setText("- - -");               }
            }else{    probation_period.setText("- - -");    }

            if(!reader.isNull("confirmationDate")){
                if(!reader.getString("confirmationDate").equals("null")){
                    if (reader.getString("confirmationDate").toString().length()>0){
                        confirmation_date.setText(reader.getString("confirmationDate"));
                    }else{
                        confirmation_date.setText("- - -");
                    }
                }else{    confirmation_date.setText("- - -");                }
            }else{     confirmation_date.setText("- - -");   }

            if(!reader.isNull("retirementDate")){
                if(!reader.getString("retirementDate").equals("null")){
                    if (reader.getString("retirementDate").toString().length()>0){
                        retirement_date.setText(reader.getString("retirementDate"));
                    }else{
                        retirement_date.setText("- - -");
                    }
                }else{     retirement_date.setText("- - -");               }
            }else{    retirement_date.setText("- - -");    }

            if(!reader.isNull("reasonForRelieving")){
                if(!reader.getString("reasonForRelieving").equals("null")){
                    if (reader.getString("reasonForRelieving").toString().length()>0){
                        reasonfor_leaving.setText(reader.getString("reasonForRelieving"));
                    }else{
                        reasonfor_leaving.setText("- - -");
                    }
                }else{     reasonfor_leaving.setText("- - -");               }
            }else{      reasonfor_leaving.setText("- - -");  }

            if(!reader.isNull("yearOfService")){
                if(!reader.getString("yearOfService").equals("null")){
                    if (reader.getString("yearOfService").toString().length()>0){
                        yearsof_service.setText(reader.getString("yearOfService"));
                    }else{
                        yearsof_service.setText("- - -");
                    }
                }else{     yearsof_service.setText("- - -");               }
            }else{     yearsof_service.setText("- - -");   }

        }catch (Exception e){

        }

    }
}
