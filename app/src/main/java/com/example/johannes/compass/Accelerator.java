package com.example.johannes.compass;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;
/**
 * Created by johannes on 2018-04-16.
 */

public class Accelerator extends AppCompatActivity implements SensorEventListener {

    private TextView xText, yText, zText, direction;
    private SensorManager mSensorManager;
    private Sensor mySensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerator);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        mySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        xText = findViewById(R.id.xText);
        yText = findViewById(R.id.yText);
        zText = findViewById(R.id.zText);
        direction = findViewById(R.id.direction);

        Intent i = getIntent();
        if ((mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) || (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) == null)) {
            noSensorsAlert();
        }




        Button btnClose = findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    public void noSensorsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Your device doesn't support the Compass.")
                .setCancelable(false)
                .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        xText.setText("X: " + event.values[0]);
        yText.setText("Y: " + event.values[1]);
        zText.setText("Z: " + event.values[2]);


        Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        String s = " ";

        if(event.values[2] > 4.9)
            s = "Rättvänd";


        //vänster
        if(event.values[0] > 4.9)
            s = "Vänster";

        //höger
        if(event.values[0] < -4.9)
            s = "Höger";

        //framåt
        if(event.values[1] > 4.9)
            s = "Framåt";

        //bakåt
        if(event.values[1] < -4.9)
            s = "Bakåt";

        //uppochned
        if(event.values[2] < -4.9){
            s = "Uppochned";
            v.vibrate(500);
        }




        direction.setText(s);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /*public void stop() {
        if (haveSensor) {
            mSensorManager.unregisterListener(this, mRotationV);
        }
        else {
            mSensorManager.unregisterListener(this, mAccelerometer);
            mSensorManager.unregisterListener(this, mMagnetometer);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }*/
/*
    @Override
    protected void onResume() {
        super.onResume();
        start_Accelerator();
    }*/
}