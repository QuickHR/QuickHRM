package com.example.exceloid_android.quickhrmobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewProfile extends AppCompatActivity {

    Button profileDetails,jobDetails;
    LinearLayout profileDetailsLayout,jobDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        profileDetails=(Button)findViewById(R.id.profileDetails);
        jobDetails=(Button)findViewById(R.id.jobDetails);
        profileDetailsLayout=(LinearLayout)findViewById(R.id.profileDetailsLayout);
        jobDetailsLayout=(LinearLayout)findViewById(R.id.jobDetailsLayout);

        profileDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (profileDetailsLayout.getVisibility() == View.VISIBLE) {
                    profileDetailsLayout.setVisibility(View.GONE);
                    profileDetails.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_control_point_black_24dp, 0);
                } else {
                    profileDetailsLayout.setVisibility(View.VISIBLE);
                    profileDetails.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_remove_circle_outline_black_24dp, 0);
                }
            }
        });

        jobDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jobDetailsLayout.getVisibility() == View.VISIBLE) {
                    jobDetailsLayout.setVisibility(View.GONE);
                    jobDetails.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_control_point_black_24dp, 0);
                } else {
                    jobDetailsLayout.setVisibility(View.VISIBLE);
                    jobDetails.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_remove_circle_outline_black_24dp, 0);
                }
            }
        });
    }
}
