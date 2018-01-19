package com.example.frosty.als_involve_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by troybrown on 11/7/17.
 */

public class VitalsTest extends AppCompatActivity{
    Button updateButton;

    EditText weight, height, BPUp, BPLo, pulse, oxygen;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vitals_test);


        height = (EditText) findViewById(R.id.vitals_height);
        weight = (EditText) findViewById(R.id.vitals_weight);
        BPUp = (EditText) findViewById(R.id.vitals_bp_upper);
        BPLo = (EditText) findViewById(R.id.vitals_bp_lower);
        pulse = (EditText) findViewById(R.id.vitals_bpm);
        oxygen = (EditText) findViewById(R.id.vitals_oxygen);

    }


    @Override
    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "Vitals Cancelled", Toast.LENGTH_SHORT).show();

        super.onBackPressed();

    }

    public void returnFromVitalsTest(View view){
        Intent returnFromVitalsTest = new Intent();

        String heightText1 = height.getText().toString();
        String weightText = weight.getText().toString();
        String bpup = BPUp.getText().toString();
        String bplo = BPLo.getText().toString();
        String pul = pulse.getText().toString();
        String oxy = oxygen.getText().toString();



        String[] results = {heightText1, weightText, bpup, bplo, pul, oxy};

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        returnFromVitalsTest.putExtra("vitalsResults",results);

        DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());

        String UID = myDb.getUID();

        boolean inserted = myDb.insertVitalsData(UID, Float.valueOf(results[0]), Float.valueOf(results[1]), Integer.valueOf(results[2]), Integer.valueOf(results[3]), Integer.valueOf(results[4]), Float.valueOf(results[5]), currentDate);

        if(inserted) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
        }

        setResult(RESULT_OK, returnFromVitalsTest);
        finish();
    }
}
