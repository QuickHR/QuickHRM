package com.example.exceloid_android.quickhrmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Exceloid-Android on 18-12-2016.
 */

public class ClaimsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View claim=inflater.inflate(R.layout.claimfragment,container,false);

        LinearLayout claimRequest=(LinearLayout)claim.findViewById(R.id.createClaimRequest);

        claimRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),ClaimRequest.class);
                startActivity(in);
            }
        });
        return claim;
    }
}
