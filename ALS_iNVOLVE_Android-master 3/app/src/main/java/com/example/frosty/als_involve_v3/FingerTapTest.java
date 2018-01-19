package com.example.frosty.als_involve_v3;

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

/**
 * Created by frosty on 10/7/17.
 */

public class FingerTapTest extends AppCompatActivity {

    Button leftTapButton;
    Button rightTapbutton;
    Button userButton;

    TextView leftTapTextView;
    TextView rightTapTextView;
    TextView timerTextView;

    int flag = 0;

    int leftTaps = 0;
    int rightTaps = 0;

    private int leftTapsResult_10, rightTapsResult_10, leftTapsResult_20, rightTapsResult_20,
    leftTapsResult_30, rightTapsResult_30, leftTapsResultTotal, rightTapsResultTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        /**
         * set the view to the tip page, currently only way to start test, will need start button for
         * when tip is skipped
         *
         * We should check the checkpoints table associated with user ID to see if we should skip the tip
         *
         * If so just call the function that is called from the tip start button, without ever showing the
         * when tip is skipped
         */

        super.onCreate(savedInstanceState);

        setContentView(R.layout.finger_tap_test_tip);

    }


    //create the timer handler and the runnable it uses
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
                    leftTapsResult_10 = Integer.parseInt(leftTapTextView.getText().toString());
                    rightTapsResult_10 = Integer.parseInt(rightTapTextView.getText().toString());
                    break;
                case 20:
                    leftTapsResult_20 = Integer.parseInt(leftTapTextView.getText().toString());
                    rightTapsResult_20 = Integer.parseInt(rightTapTextView.getText().toString());
                    break;
                case 30:
                    leftTapsResult_30 = Integer.parseInt(leftTapTextView.getText().toString());
                    rightTapsResult_30 = Integer.parseInt(rightTapTextView.getText().toString());
                    break;
                case 40:
                    leftTapsResultTotal = Integer.parseInt(leftTapTextView.getText().toString());
                    rightTapsResultTotal = Integer.parseInt(rightTapTextView.getText().toString());
                    timeOut();
                    break;
            }

            timerTextView.setText(String.format("%02d: %02d", seconds, Math.round((double)(millis - (seconds*1000))/10)));

            if(seconds<40){
                timerHandler.postDelayed(this, 100);
            } else {
                timerTextView.setText("Time Up!");
                userButton.setText("Save Results");
                flag = 3;
            }
        }
    };

    @Override
    public void onBackPressed() {

        Intent returnFromFingerTapTest = new Intent();

        //return an empty data
        int[] results = {};

        returnFromFingerTapTest.putExtra("tapsResults", results);

        setResult(RESULT_OK, returnFromFingerTapTest);

        finish();
    }


    //iterate left taps
    public void leftTap(View view) {
        leftTaps++;
        String setText = Integer.toString(leftTaps);
        leftTapTextView.setText(setText);
    }

    //iterate right taps
    public void rightTap(View view) {
        rightTaps++;
        String setText = Integer.toString(rightTaps);
        rightTapTextView.setText(setText);
    }

    //called when the timer finishes
    public void timeOut(){
        leftTapButton.setEnabled(false);
        rightTapbutton.setEnabled(false);

    }

    //End the test and sends results to db, called by save button
    public void returnFromFingerTapTest(View view) {

        Intent returnFromFingerTapTest = new Intent();

        //store in array
        int[] results = {leftTapsResult_10, rightTapsResult_10, leftTapsResult_20, rightTapsResult_20,
                leftTapsResult_30, rightTapsResult_30, leftTapsResultTotal, rightTapsResultTotal};

        DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String UID = myDb.getUID();

        boolean inserted = myDb.insertFingerTapData(UID, results[0], results[1], results[2],
                results[3], results[4], results[5], results[6], results[7], currentDate);

        if(inserted) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
        }


        //return the array and return to main activity
        returnFromFingerTapTest.putExtra("tapsResults", results);

        setResult(RESULT_OK, returnFromFingerTapTest);

        finish();
    }

    //called from bottom button on tap test
    public void fingerTapButtonHandler(View view){
        if(flag==0) {
            startFingerTapTestTimer(view);
            flag = 1;
        }
        else if(flag==1){
            pauseTest(view);
            flag = 2;
        } else if(flag==2){
            resetTest(view);
            flag = 0;
        } else if(flag==3){
            returnFromFingerTapTest(view);
        }


    }

    //starts the timer and enables buttons
    public void startFingerTapTestTimer(View view){

        userButton.setText("Pause Test");

        leftTapButton.setEnabled(true);
        rightTapbutton.setEnabled(true);

        //start test
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    //pause the test
    public void pauseTest(View view){
        userButton.setText("Reset Test");

        timerHandler.removeCallbacks(timerRunnable);
    }

    //reset the test
    public void resetTest(View view){
        userButton.setText("Start Test");
        fingerTapTipStart(view);
        leftTaps = 0;
        rightTaps = 0;
    }


    /**
     * set the view to the finger tap test and gets all the needed buttons and textViews
     *
     * called from the start button on the tip page
     */

    public void fingerTapTipStart(View view) {

        setContentView(R.layout.finger_tap_test);

        //getting buttons
        leftTapButton = (Button) findViewById(R.id.left_tap_test_button);
        rightTapbutton = (Button) findViewById(R.id.right_tap_test_button);
        userButton = (Button) findViewById(R.id.finger_tap_test_start_save_button);

        //disable tap buttons
        leftTapButton.setEnabled(false);
        rightTapbutton.setEnabled(false);

        //getting textviews
        leftTapTextView = (TextView) findViewById(R.id.left_tap_textView);
        rightTapTextView = (TextView) findViewById(R.id.right_tap_textView);
        timerTextView = (TextView) findViewById(R.id.finger_tap_test_timer_textView);


    }


}
