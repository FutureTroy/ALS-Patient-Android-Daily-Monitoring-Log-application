package com.example.frosty.als_involve_v3;

/**
 * Created by bhigg on 11/14/2017.
 */

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

public class SupplementsTest extends AppCompatActivity{
    Button updateButton;

    EditText med1, med2, med3, dose1, dose2, dose3;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.supplement_test);


        med1 = (EditText) findViewById(R.id.vitals_med_name_1);
        med2 = (EditText) findViewById(R.id.vitals_med_name_2);
        med3 = (EditText) findViewById(R.id.vitals_med_name_3);
        dose1 = (EditText) findViewById(R.id.vitals_med_dose_1);
        dose2 = (EditText) findViewById(R.id.vitals_med_dose_2);
        dose3 = (EditText) findViewById(R.id.vitals_med_dose_3);

    }

    //Did not add med 2/3 and dose 2/3
    //Only med 1 and dose 1


    @Override
    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    public void returnFromSupplementsTest(View view){

        String medET1 = med1.getText().toString();
        String medET2 = med2.getText().toString();
        String medET3 = med3.getText().toString();
        String doseET1 = dose1.getText().toString();
        String doseET2 = dose2.getText().toString();
        String doseET3 = dose3.getText().toString();

        DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String UID = myDb.getUID();

        boolean inserted = myDb.insertSupplementsData(UID, medET1, Integer.parseInt(doseET1), currentDate);

        if(inserted) {
            Toast.makeText(this, "Supplement 1 Saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Save Failed, Supplement 1", Toast.LENGTH_SHORT).show();
        }

        if(!medET2.equals("") && !doseET2.equals("")){
            inserted = myDb.insertSupplementsData("1", medET2, Integer.parseInt(doseET2), currentDate);

            if(inserted) {
                Toast.makeText(this, "Supplement 2 Saved", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Save Failed, Supplement 2", Toast.LENGTH_SHORT).show();
            }
        }

        if(!medET3.equals("") && !doseET3.equals("")){
            inserted = myDb.insertSupplementsData("1", medET3, Integer.parseInt(doseET3), currentDate);

            if(inserted) {
                Toast.makeText(this, "Supplement 3 Saved", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Save Failed, Supplement 3", Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }
}
