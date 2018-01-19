package com.example.frosty.als_involve_v3;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by troybrown on 11/20/17.
 */

public class FingerTap {

    private int UID;
    public int leftTaps_10;
    public int rightTaps_10;
    public int leftTaps_20;
    public int rightTaps_20;
    public int leftTaps_30;
    public int rightTaps_30;
    public int leftTapsTotal;
    public int rightTapsTotal;
    public String fingerDate;

    public FingerTap(){

    }

    protected FingerTap(int leftTaps_10,int rightTaps_10,int leftTaps_20, int rightTaps_20,int leftTaps_30, int rightTaps_30, int leftTapsTotal, int rightTapsTotal, String fingerDate){
        this.leftTaps_10 = leftTaps_10;
        this.rightTaps_10 = rightTaps_10;
        this.leftTaps_20 = leftTaps_20;
        this.rightTaps_20 = rightTaps_20;
        this.leftTaps_30 = leftTaps_30;
        this.rightTaps_30 = rightTaps_30;
        this.leftTapsTotal = leftTapsTotal;
        this.rightTapsTotal = rightTapsTotal;
        this.fingerDate = fingerDate;
    }



    public FingerTap createTestFingerTap() {

        String currentDate = new SimpleDateFormat("yyyy-mm-dd").format(new Date());

        return new FingerTap(42,43,76,81,119,128,175,187, currentDate);
    }


}
