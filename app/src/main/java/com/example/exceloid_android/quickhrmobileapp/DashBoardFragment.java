package com.example.exceloid_android.quickhrmobileapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DashBoardFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View dash=inflater.inflate(R.layout.fragment_dash_board,container,false);
        return dash;
    }
}
