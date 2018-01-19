package com.example.frosty.als_involve_v3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ToeTapTest extends AppCompatActivity {

    Button toeTapbutton, saveButton;

    TextView tapTextView, timerTextView;

    int side;

    int taps;

    int flag = 0;

    private int tapsResult_10, tapsResult_20, tapsResult_30, tapsResultTotal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.toe_tap_test_tip);

    }


    //the timer, grabs taps at intervals 10, 20, 30, and 40 seconds
    long startTime = 0;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        /**
         * timer that records results for tap test at 10, 20, 30, and 40 seconds
         */

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            switch(seconds){
                case 10:
                    tapsResult_10 = Integer.parseInt(tapTextView.getText().toString());
                    break;
                case 20:
                    tapsResult_20 = Integer.parseInt(tapTextView.getText().toString());
                    break;
                case 30:
                    tapsResult_30 = Integer.parseInt(tapTextView.getText().toString());
                    break;
                case 40:
                    tapsResultTotal = Integer.parseInt(tapTextView.getText().toString());
                    timeOut();
                    break;
            }

            timerTextView.setText(String.format("%02d: %02d", seconds, Math.round((double)(millis - (seconds*1000))/10)));

            if(seconds<40){
                timerHandler.postDelayed(this, 100);
            } else {
                timerTextView.setText("Time Up!");
                saveButton.setText("Save Results");
                flag = 3;
            }
        }
    };

    @Override
    public void onBackPressed() {

        Intent returnFromToeTapTest = new Intent();


        //return an empty data set, handled in results processing in main activity
        int[] results = {};

        returnFromToeTapTest.putExtra("tapsResults", results);

        setResult(RESULT_OK, returnFromToeTapTest);

        finish();
    }

    //called when the timer finishes
    public void timeOut(){
        toeTapbutton.setEnabled(false);
        saveButton.setEnabled(true);
    }

    //iterate taps
    public void toeTap(View view) {
        taps++;
        String setText = Integer.toString(taps);
        tapTextView.setText(setText);
    }

    //called from bottom button on tap test
    public void toeTapButtonHandler(View view){
        if(flag==0) {
            startToeTapTestTimer(view);
            flag = 1;
        }
        else if(flag==1){
            pauseTest(view);
            flag = 2;
        } else if(flag==2){
            resetTest(view);
            flag = 0;
        } else if(flag==3){
            returnFromToeTapTest(view);
        }

    }


    //starts the timer and enables buttons
    public void startToeTapTestTimer(View view){

        saveButton.setText("Pause Test");

        toeTapbutton.setEnabled(true);

        //start test
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    //pause the test
    public void pauseTest(View view){
        saveButton.setText("Reset Test");

        timerHandler.removeCallbacks(timerRunnable);
    }

    //reset the test
    public void resetTest(View view){
        saveButton.setText("Start Test");
        toeTapTipStart(view);
        taps = 0;
    }

    //End the test and sends results back to main activity
    public void returnFromToeTapTest(View view) {

        Intent returnFromToeTapTest = new Intent();

        //store in array
        int[] results = {side, tapsResult_10, tapsResult_20, tapsResult_30, tapsResultTotal};

        DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());

        String[] sides = {"left", "right"};

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String UID = myDb.getUID();

        boolean inserted = myDb.insertToeTapData(UID, sides[results[0]], results[1], results[2], results[3], results[4], currentDate);

        if(inserted) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
        }

        //return the array and return to main activity
        returnFromToeTapTest.putExtra("tapsResults", results);

        setResult(RESULT_OK, returnFromToeTapTest);

        finish();
    }

    public void showDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ToeTapTest.this);
        builder1.setMessage("Please select which foot you will be testing.");
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



    //come to test page after start on tip
    public void toeTapTipStart(View view){

        setContentView(R.layout.toe_tap_test);

        //getting button
        toeTapbutton = (Button) findViewById(R.id.toe_tap_test_button);
        saveButton = (Button)findViewById(R.id.toe_tap_test_save_button);

        toeTapbutton.setEnabled(false);

        //getting textviews
        tapTextView = (TextView) findViewById(R.id.toe_tap_num_taps);
        timerTextView = (TextView) findViewById(R.id.toe_tap_test_timer_textview);

        if(!isFinishing()){
            showDialog();
        }
    }
}