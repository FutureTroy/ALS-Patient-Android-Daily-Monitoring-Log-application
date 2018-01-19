package com.example.frosty.als_involve_v3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by frosty on 10/7/17.
 */

public class GyroscopeActivity extends Activity {

    private SensorManager sensorManager;
    private Sensor accelerometer, magnetometer;
    private SensorEventListener sensorEventListener;

    private TextView degreeEnd;
    private TextView degreeStart;
    private Button degreeButton, saveButton;
    private TextView textView;

    float[] mGravity;
    float[] mGeomagnetic;
    float azimut;

    boolean started = false;

    int side, appendage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initializing the sensors
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        setContentView(R.layout.arm_rotation_test_tip);


        Button startTest = (Button) findViewById(R.id.arm_rotation_tip_start_button);

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTheLayout();
            }
        });

        //fucking magic
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                    mGravity = sensorEvent.values;
                if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                    mGeomagnetic = sensorEvent.values;
                if (mGravity != null && mGeomagnetic != null) {
                    float R[] = new float[9];
                    float I[] = new float[9];
                    boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
                    if (success) {
                        float orientation[] = new float[3];
                        SensorManager.getOrientation(R, orientation);
                        azimut = orientation[1]; // orientation contains: azimut, pitch and roll


                        try{
                            textView.setText(Double.toString(Math.round(Math.toDegrees((double)azimut))));

                        } catch (Exception e){
                            Log.e("RotationSensorSetText", e.getLocalizedMessage());
                        }
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    private void changeTheLayout() {


        setContentView(R.layout.arm_rotation_test);

        //initialize all the the view on screen we need
        degreeEnd = (TextView) findViewById(R.id.degreeEnd);
        degreeStart = (TextView) findViewById(R.id.degreeStart);
        degreeButton = (Button) findViewById(R.id.degreeButton);
        saveButton = (Button) findViewById(R.id.rotation_save_button);
        textView = (TextView) findViewById(R.id.textView);

        saveButton.setEnabled(false);

        if(!isFinishing()){
            showAppendageDialog();
        }

    }

    @Override
    public void onBackPressed() {

        Intent returnFromArmRotationTest = new Intent();

        //return an empty data
        float results = -10001;

        returnFromArmRotationTest.putExtra("rotationResult", results);

        setResult(RESULT_OK, returnFromArmRotationTest);

        finish();
    }

    //register listeners
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(sensorEventListener, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    //remove listeners
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    //start and stop the test
    public void startTest(View view) {
        if(!started) {
            degreeStart.setText(textView.getText());
            degreeButton.setText("Stop");
            started = true;
        } else {
            degreeEnd.setText(textView.getText());
            started=false;

            float start = Float.parseFloat(degreeStart.getText().toString());
            float end = Float.parseFloat(degreeEnd.getText().toString());
            float difference = end - start;
            degreeButton.setEnabled(false);
            saveButton.setEnabled(true);

            degreeButton.setText(Float.toString(difference));
        }

    }

    //end the intent
    public void returnFromRotationTest(View view) {

        Intent returnFromRotationTest = new Intent();

        //get result
        float rotation = Math.abs(Float.valueOf(degreeButton.getText().toString()));

        DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        //the dialogs before the test set integers that will chooses these correctly on insert to DB
        String[] appendages = {"Arm", "Leg"};
        String[] sides = {"Left", "Right"};

        String UID = myDb.getUID();
        boolean inserted = myDb.insertArmRotationData(UID, appendages[appendage], sides[side], rotation, currentDate);

        if(inserted)
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();


        //return result to main activity
        returnFromRotationTest.putExtra("rotationResult", rotation);

        setResult(RESULT_OK, returnFromRotationTest);

        finish();
    }


    public void showAppendageDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(GyroscopeActivity.this);
        builder1.setMessage("Will you be testing an arm or a leg?.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Leg",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        side = 1;
                        dialog.cancel();
                        showSideDialog();
                    }
                });

        builder1.setNegativeButton(
                "Arm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        side = 0;
                        dialog.cancel();
                        showSideDialog();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);

        alert11.show();
    }

    public void showSideDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(GyroscopeActivity.this);
        builder1.setMessage("Please select which side you will be testing.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Right",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        side = 1;
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Left",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        side = 0;
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);

        alert11.show();
    }

}
