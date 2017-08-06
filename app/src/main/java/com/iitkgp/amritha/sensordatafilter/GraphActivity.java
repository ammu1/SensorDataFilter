package com.iitkgp.amritha.sensordatafilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {
    ArrayList<Float> floatPointList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        floatPointList = new ArrayList<Float>();
        floatPointList = (ArrayList<Float>) getIntent().getSerializableExtra("sensorXData");

        DataPoint dataPoints[] = new DataPoint[floatPointList.size()];
        for (int i=0;i<floatPointList.size();i++){
            DataPoint point = new DataPoint(i, floatPointList.get(i));
            dataPoints[i] = point;
        }


        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        graph.addSeries(series);

    }
}
