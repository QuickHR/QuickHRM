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

public class Bank_Information extends Fragment {

    TextView account_num,account_name,bank_name,branch_name,description,defaults;

    public Bank_Information() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View bank=inflater.inflate(R.layout.bank_information,container,false);

        account_num=(TextView)bank.findViewById(R.id.account_num);
        account_name=(TextView)bank.findViewById(R.id.account_name);
        bank_name=(TextView)bank.findViewById(R.id.bank_name);
        branch_name=(TextView)bank.findViewById(R.id.branch_name);
        description=(TextView)bank.findViewById(R.id.description);
        defaults=(TextView)bank.findViewById(R.id.defaultss);

        return bank;

    }
}
