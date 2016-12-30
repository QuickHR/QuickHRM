package com.example.exceloid_android.quickhrmobileapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.DecoDrawEffect;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

/**
 * Created by Exceloid-Android on 17-12-2016.
 */

public class LeaveRequest extends Fragment {

    LinearLayout leave_Request, view_request, leave_Balance, approve_Request, leave_Calender;
    private int mBackIndex;
    private int mSeries1Index;
    private int mSeries2Index;
    private int mSeries3Index;
    private final float mSeriesMax = 50f;
    TextView textPercentage, textToGo;
    private DecoView mDecoView;
    PieChart pieChart;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View leave = inflater.inflate(R.layout.leaves_requests, container, false);

        leave_Request = (LinearLayout) leave.findViewById(R.id.createLeaveRequest);
        view_request = (LinearLayout) leave.findViewById(R.id.viewRequest);
        leave_Balance = (LinearLayout) leave.findViewById(R.id.leaveBalance);
        approve_Request = (LinearLayout) leave.findViewById(R.id.approveRequests);
        pieChart = (PieChart)leave.findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(10f, 0));
        entries.add(new Entry(5f, 1));
        entries.add(new Entry(3f, 2));
        entries.add(new Entry(2f, 3));

        PieDataSet dataset = new PieDataSet(entries, "");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Created");
        labels.add("Approved");
        labels.add("Leave Balance");
        labels.add("View Requests");

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setDescription("");
        pieChart.setData(data);
        data.setDrawValues(false);

        pieChart.animateY(3000);

        leave_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getContext(), CreateLeaveRequest.class);
                startActivity(in);
            }
        });

        view_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getContext(), ViewRequest.class);
                startActivity(in);
            }
        });

        leave_Balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getContext(), LeaveBalance.class);
                startActivity(in);
            }
        });

        approve_Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getContext(), ApproveRequest.class);
                startActivity(in);
            }
        });
        return leave;
    }

     /*mDecoView = (DecoView) leave.findViewById(R.id.dynamicArcView);
        textPercentage = (TextView) leave.findViewById(R.id.textPercentage);
        textToGo = (TextView) leave.findViewById(R.id.textRemaining);

    private void createBackSeries() {
        SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#058785"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(true)
                .build();

        mBackIndex = mDecoView.addSeries(seriesItem);
    }

   createBackSeries();
    createDataSeries2();
    createDataSeries3();

    createEvents();

    private void createDataSeries2() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#bf2850"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();

        mSeries2Index = mDecoView.addSeries(seriesItem);
    }

    private void createDataSeries3() {
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#68961e"))
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .build();

        mSeries3Index = mDecoView.addSeries(seriesItem);
    }

    private void createEvents() {
        mDecoView.executeReset();

        mDecoView.addEvent(new DecoEvent.Builder(50.0f)
                .setIndex(mSeries1Index)
                .setDelay(0)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_OUT)
                .setIndex(mSeries2Index)
                .setDuration(1000)
                .setEffectRotations(1)
                .setDelay(2000)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(25.3f)
                .setIndex(mSeries2Index)
                .setDelay(3000)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(DecoDrawEffect.EffectType.EFFECT_SPIRAL_OUT)
                .setIndex(mSeries3Index)
                .setDuration(1000)
                .setEffectRotations(1)
                .setDelay(4000)
                .build());

        mDecoView.addEvent(new DecoEvent.Builder(9.36f).setIndex(mSeries3Index).setDelay(5000).build());
    }*/
}
