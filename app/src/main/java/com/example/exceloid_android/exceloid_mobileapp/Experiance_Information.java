package com.example.exceloid_android.exceloid_mobileapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Exceloid-Android on 26-09-2016.
 */

public class Experiance_Information extends Fragment {

    TextView previous_employer,dateof_joining,salary,relieved_date,reasonof_leving;

    public Experiance_Information() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ex=inflater.inflate(R.layout.experiance_information,container,false);

        previous_employer=(TextView)ex.findViewById(R.id.previous_employer);
        dateof_joining=(TextView)ex.findViewById(R.id.dateof_joining);
        salary=(TextView)ex.findViewById(R.id.salary);
        relieved_date=(TextView)ex.findViewById(R.id.relieved_date);
        reasonof_leving=(TextView)ex.findViewById(R.id.reasonfor_leaving);

        return ex;
    }
}
