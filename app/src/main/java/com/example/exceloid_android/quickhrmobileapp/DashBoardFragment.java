package com.example.exceloid_android.quickhrmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DashBoardFragment extends Fragment {

    LinearLayout applyLeave,applyClaim,viewProfile;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_dash_board,null);

        viewProfile=(LinearLayout)root.findViewById(R.id.viewProfile);
        applyClaim=(LinearLayout)root.findViewById(R.id.applyClaim);
        applyLeave=(LinearLayout)root.findViewById(R.id.applyLeave);

        applyLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),CreateLeaveRequest.class);
                startActivity(in);
            }
        });

        applyClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),ClaimRequest.class);
                startActivity(in);
            }
        });

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),ViewProfile.class);
                startActivity(in);
            }
        });

        return root;
    }
}
