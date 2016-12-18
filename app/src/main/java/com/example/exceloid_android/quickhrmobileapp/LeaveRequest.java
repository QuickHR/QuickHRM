package com.example.exceloid_android.quickhrmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Exceloid-Android on 17-12-2016.
 */

public class LeaveRequest extends Fragment {

    LinearLayout leave_Request,view_request,leave_Balance,approve_Request,leave_Calender;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View leave=inflater.inflate(R.layout.leaves_requests,container,false);

        leave_Request=(LinearLayout)leave.findViewById(R.id.createLeaveRequest);
        view_request=(LinearLayout)leave.findViewById(R.id.viewRequest);
        leave_Balance=(LinearLayout)leave.findViewById(R.id.leaveBalance);
        approve_Request=(LinearLayout)leave.findViewById(R.id.approveRequests);

        leave_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),CreateLeaveRequest.class);
                startActivity(in);

            }
        });

        view_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),ViewRequest.class);
                startActivity(in);
            }
        });

        leave_Balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),LeaveBalance.class);
                startActivity(in);
            }
        });

        approve_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getContext(),ApproveRequest.class);
                startActivity(in);
            }
        });
        return leave;
    }
}
