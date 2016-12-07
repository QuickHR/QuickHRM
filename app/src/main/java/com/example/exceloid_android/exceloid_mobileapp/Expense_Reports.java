package com.example.exceloid_android.exceloid_mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Exceloid-Android on 20-09-2016.
 */
public class Expense_Reports extends Fragment {

    LinearLayout expenses,expenses_reports,new_expenses,newexpenses_reports;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.expense_reports,container,false);

        expenses=(LinearLayout)v.findViewById(R.id.expenses);
        expenses_reports=(LinearLayout)v.findViewById(R.id.expenses_reports);
        new_expenses=(LinearLayout)v.findViewById(R.id.new_expenses);
        newexpenses_reports=(LinearLayout)v.findViewById(R.id.newexpenses_reports);

        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),Expenses_Activity.class);
                startActivity(in);

            }
        });

        expenses_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),Expenses_Reports_Activity.class);
                startActivity(in);
            }
        });

        new_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),AddExpenses_Activity.class);
                startActivity(in);
            }
        });

        newexpenses_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),AddExpenses_Reports_Activity.class);
                startActivity(in);
            }
        });

        return v;
    }
}
