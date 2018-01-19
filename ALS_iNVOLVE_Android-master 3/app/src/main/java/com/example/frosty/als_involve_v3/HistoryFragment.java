package com.example.frosty.als_involve_v3;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by frosty on 10/8/17.
 */

public class HistoryFragment extends Fragment{

    private int lvalue10, lvalue20, lvalue30, lvaluetotal;
    private int rvalue10, rvalue20, rvalue30, rvaluetotal;

    private int tvalue10, tvalue20, tvalue30, tvaluetotal;

    private double armLvalue, armRvalue;

    private Button screenshotBtn;
    private LinearLayout historyLinFragment;

    //inflate the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MainActivity Context = (MainActivity) getActivity();

        View view = inflater.inflate(R.layout.history_fragment, container, false);

        //screenshotBtn = (Button) view.findViewById(R.id.screenshotBtn);

        historyLinFragment = (LinearLayout) view.findViewById(R.id.history_fragment);
        //findViews();

        //Creating Finger Test Results Display
        TextView l10 = (TextView) view.findViewById(R.id.left10txt);
        l10.setText(String.valueOf(Context.fingerResults.leftTaps_10));
        TextView l20 = (TextView) view.findViewById(R.id.left20txt);
        l20.setText(String.valueOf(Context.fingerResults.leftTaps_20));
        TextView l30 = (TextView) view.findViewById(R.id.left30txt);
        l30.setText(String.valueOf(Context.fingerResults.leftTaps_30));
        TextView ltotal = (TextView) view.findViewById(R.id.leftTotaltxt);
        ltotal.setText(String.valueOf(Context.fingerResults.leftTapsTotal));


        TextView r10 = (TextView) view.findViewById(R.id.right10txt);
        r10.setText(String.valueOf(Context.fingerResults.rightTaps_10));
        TextView r20 = (TextView) view.findViewById(R.id.right20txt);
        r20.setText(String.valueOf(Context.fingerResults.rightTaps_20));
        TextView r30 = (TextView) view.findViewById(R.id.right30txt);
        r30.setText(String.valueOf(Context.fingerResults.rightTaps_30));
        TextView rtotal = (TextView) view.findViewById(R.id.rightTotaltxt);
        rtotal.setText(String.valueOf(Context.fingerResults.rightTapsTotal));

        TextView fdate = (TextView) view.findViewById(R.id.fingerdatetxt);
        fdate.setText(Context.fingerResults.fingerDate);

        setFingerValues();
        GraphView fingerGraph = (GraphView) view.findViewById(R.id.fingerGraph);
        initFingerGraph(fingerGraph, lvalue10, lvalue20, lvalue30, lvaluetotal, rvalue10, rvalue20, rvalue30, rvaluetotal);




        //Creating Toe Test Results Display
        TextView t10 = (TextView) view.findViewById(R.id.toe10txt);
        t10.setText(String.valueOf(Context.toeResults.toeTaps_10));
        TextView t20 = (TextView) view.findViewById(R.id.toe20txt);
        t20.setText(String.valueOf(Context.toeResults.toeTaps_20));
        TextView t30 = (TextView) view.findViewById(R.id.toe30txt);
        t30.setText(String.valueOf(Context.toeResults.toeTaps_30));
        TextView ttotal = (TextView) view.findViewById(R.id.toeTotaltxt);
        ttotal.setText(String.valueOf(Context.toeResults.toeTapsTotal));

        TextView tdate = (TextView) view.findViewById(R.id.toedatetxt);
        tdate.setText(Context.toeResults.toeDate);

        setToeValues();
        GraphView toeGraph = (GraphView) view.findViewById(R.id.toegraph);
        initToeGraph(toeGraph, tvalue10,tvalue20,tvalue30,tvaluetotal);


        //Creating Arm Test Results Display
        TextView aLvalue = (TextView) view.findViewById(R.id.armLtxt);
        aLvalue.setText(String.valueOf(Context.lArmResults.armRotation));
        TextView aRvalue = (TextView) view.findViewById(R.id.armRtxt);
        aRvalue.setText(String.valueOf(Context.rArmResults.armRotation));

        TextView adate = (TextView) view.findViewById(R.id.armdatetxt);
        adate.setText(Context.lArmResults.armDate);

        setArmValues();
        GraphView armGraph = (GraphView) view.findViewById(R.id.armgraph);
        initArmGraph(armGraph, armLvalue, armRvalue);

        return view;
    }

    public void setFingerValues() {
        MainActivity Context = (MainActivity) getActivity();

        lvalue10 = (Context.fingerResults.leftTaps_10);
        lvalue20 = (Context.fingerResults.leftTaps_20);
        lvalue30 = (Context.fingerResults.leftTaps_30);
        lvaluetotal = (Context.fingerResults.leftTapsTotal);

        rvalue10 = (Context.fingerResults.rightTaps_10);
        rvalue20 = (Context.fingerResults.rightTaps_20);
        rvalue30 = (Context.fingerResults.rightTaps_30);
        rvaluetotal = (Context.fingerResults.rightTapsTotal);
    }

    public void setToeValues() {
        MainActivity Context = (MainActivity) getActivity();

        tvalue10 = (Context.toeResults.toeTaps_10);
        tvalue20 = (Context.toeResults.toeTaps_20);
        tvalue30 = (Context.toeResults.toeTaps_30);
        tvaluetotal = (Context.toeResults.toeTapsTotal);
    }

    public void setArmValues(){
        MainActivity Context = (MainActivity) getActivity();

        armLvalue = (Context.lArmResults.armRotation);
        armRvalue = (Context.rArmResults.armRotation);

    }

    public void initFingerGraph(GraphView graph, int lvalue10, int lvalue20, int lvalue30, int lvaluetotal, int rvalue10, int rvalue20, int rvalue30, int rvaluetotal) {

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 0),
                new DataPoint(10, lvalue10),
                new DataPoint(20, lvalue20),
                new DataPoint(30, lvalue30),
                new DataPoint(40, lvaluetotal)
        });
        series.setDrawBackground(true);
        //series.setAnimated(true);
        series.setDrawDataPoints(true);
        series.setTitle("Left Taps");
        series.setColor(Color.argb(255, 244, 71, 71));
        series.setBackgroundColor(Color.argb(100, 204, 119, 119));
        graph.addSeries(series);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 0),
                new DataPoint(10, rvalue10),
                new DataPoint(20, rvalue20),
                new DataPoint(30, rvalue30),
                new DataPoint(40, rvaluetotal)
        });
        series2.setDrawBackground(true);
        //series2.setAnimated(true);
        series2.setDrawDataPoints(true);
        series2.setTitle("Right Taps");
        series2.setColor(Color.argb(255, 71, 71, 250));
        series2.setBackgroundColor(Color.argb(100, 50, 40, 40));
        graph.addSeries(series2);

        //graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Taps");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Seconds (s)");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }

    public void initToeGraph(GraphView graph, int tvalue10, int tvalue20, int tvalue30, int tvaluetotal){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 0),
                new DataPoint(10, tvalue10),
                new DataPoint(20, tvalue20),
                new DataPoint(30, tvalue30),
                new DataPoint(40, tvaluetotal)
        });
        series.setDrawBackground(true);
        //series.setAnimated(true);
        series.setDrawDataPoints(true);
        series.setColor(Color.argb(255, 244, 71, 71));
        series.setBackgroundColor(Color.argb(100, 204, 119, 119));
        //graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Taps");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Seconds (s)");
        graph.addSeries(series);
    }

    public void initArmGraph(GraphView graph, double armLvalue, double armRvalue){


        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 0),
                new DataPoint(2, armLvalue),
                new DataPoint(3, 80),
                new DataPoint(4, 0)
        });
        series.setSpacing(30);
        //series.setAnimated(true);
        graph.addSeries(series);

        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 0),
                new DataPoint(2, 70),
                new DataPoint(3, armRvalue),
                new DataPoint(4, 0)
        });
        series2.setColor(Color.RED);
        series2.setSpacing(30);
        //series2.setAnimated(true);
        graph.addSeries(series2);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(6);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);

        series.setTitle("Left");
        series2.setTitle("Right");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Rotation(deg)");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Arms  |  Legs");
    }

    /*  Find all views Ids  */
    private void findViews() {

    }








    /*  Share Screenshot  */
    private void shareScreenshot(File file) {
        Uri uri = Uri.fromFile(file);//Convert file path into Uri for sharing
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        startActivity(Intent.createChooser(intent, "Sharing Screenshot"));
    }
}