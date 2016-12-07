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
public class LeavesRequests_Activity extends Fragment {

    LinearLayout leave_Request,view_request,leave_Balance,approve_Request,leave_Calender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.leaves_requests,container,false);

        leave_Request=(LinearLayout)v.findViewById(R.id.createLeave_Request);
        view_request=(LinearLayout)v.findViewById(R.id.view_Request);
        leave_Balance=(LinearLayout)v.findViewById(R.id.leave_Balance);
        approve_Request=(LinearLayout)v.findViewById(R.id.approve_Requests);
        leave_Calender=(LinearLayout)v.findViewById(R.id.leave_Calender);

        leave_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),Create_LeaveRequest.class);
                startActivity(in);

            }
        });

        view_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),View_Request.class);
                startActivity(in);
            }
        });

        leave_Balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),Leave_Balance.class);
                startActivity(in);
            }
        });

        approve_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),Approve_Request.class);
                startActivity(in);
            }
        });

        leave_Calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),Leave_Calender.class);
                startActivity(in);
            }
        });
        return v;
    }
}
