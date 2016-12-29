package com.example.exceloid_android.quickhrmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

public class ClaimsFragment extends Fragment {
    PieChart pieChart;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View claim=inflater.inflate(R.layout.claimfragment,container,false);

        LinearLayout claimRequest=(LinearLayout)claim.findViewById(R.id.createClaimRequest);

        pieChart = (PieChart)claim.findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(3f, 0));
        entries.add(new Entry(2f, 1));
        entries.add(new Entry(10f, 2));
        entries.add(new Entry(5f, 3));

        PieDataSet dataset = new PieDataSet(entries, "");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Rejected: 3");
        labels.add("Pending: 1");
        labels.add("Created: 10");
        labels.add("Approved: 5");

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setDescription("");
        pieChart.setData(data);
        data.setDrawValues(false);

        pieChart.animateY(3000);

        //pieChart.saveToGallery("/sd/mychart.jpg", 85);

        claimRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getContext(),ClaimRequest.class);
                startActivity(in);
            }
        });
        return claim;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pieChartData();
    }

    public void pieChartData(){



    }@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pieChartData();
    }

    public void pieChartData(){



    }*/
}
