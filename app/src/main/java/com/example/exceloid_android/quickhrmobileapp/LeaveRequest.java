package com.example.exceloid_android.quickhrmobileapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Exceloid-Android on 17-12-2016.
 */

public class LeaveRequest extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View leave=inflater.inflate(R.layout.leaves_requests,container,false);
        return leave;
    }
}
