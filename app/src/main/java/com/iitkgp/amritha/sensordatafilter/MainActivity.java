package com.iitkgp.amritha.sensordatafilter;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    TextView title, tvx, tvy, tvz;
    Button btnRead, btnStart, btnStop;
    RelativeLayout layout;

    private ArrayList<Float> sensorXData, sensorYData, sensorZData, timeData;

    private BufferedWriter mBufferedWriter;
    private BufferedReader mBufferedReader;
    private float x;
    private float y;
    private float z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        //get layout
        // layout = (RelativeLayout) findViewById(R.id.relative);

        //get textviews
        title = (TextView) findViewById(R.id.name);
        tvx = (TextView) findViewById(R.id.xval);
        tvy = (TextView) findViewById(R.id.yval);
        tvz = (TextView) findViewById(R.id.zval);

        btnStart = (Button) findViewById(R.id.button1);
        btnStop = (Button) findViewById(R.id.button2);
        btnRead = (Button) findViewById(R.id.button3);

        sensorXData = new ArrayList<Float>();
        sensorYData = new ArrayList<Float>();
        sensorZData = new ArrayList<Float>();

        //filteredXData = new double[10];


        btnRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GraphActivity.class);
                i.putExtra("sensorXData", sensorXData);
                i.putExtra("sensorYData", sensorYData);

                i.putExtra("sensorZData", sensorZData);
                startActivity(i);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];

            sensorXData.add(x);
            sensorYData.add(y);
            sensorZData.add(z);

            // acc= String.valueOf(x) + ", " + String.valueOf(y) + ", " + String.valueOf(z);

            tvx.setText("X = " + String.valueOf(x));
            tvy.setText("Y = " + String.valueOf(y));
            tvz.setText("Z = " + String.valueOf(z));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
