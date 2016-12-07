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

public class Qualification_Information extends Fragment {
    TextView degree,university,yearof_passing,percentage,specilazation,institute;

    public Qualification_Information() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View qualification=inflater.inflate(R.layout.qualification_information,container,false);
        degree=(TextView)qualification.findViewById(R.id.degree);
        university=(TextView)qualification.findViewById(R.id.university);
        yearof_passing=(TextView)qualification.findViewById(R.id.yearof_passing);
        percentage=(TextView)qualification.findViewById(R.id.percentage);
        specilazation=(TextView)qualification.findViewById(R.id.specialization);
        institute=(TextView)qualification.findViewById(R.id.institute);

        return qualification;
    }
}
