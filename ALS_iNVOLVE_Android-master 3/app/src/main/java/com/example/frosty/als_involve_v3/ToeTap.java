package com.example.frosty.als_involve_v3;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by troybrown on 11/21/17.
 */

public class ToeTap {
    private int UID;
    public int toeTaps_10;
    public int toeTaps_20;
    public int toeTaps_30;
    public int toeTapsTotal;
    public String toeDate;

    public ToeTap(){

    }

    protected ToeTap(int toeTaps_10, int toeTaps_20, int toeTaps_30, int toeTapsTotal, String toeDate){
        this.toeTaps_10 = toeTaps_10;
        this.toeTaps_20 = toeTaps_20;
        this.toeTaps_30 = toeTaps_30;
        this.toeTapsTotal = toeTapsTotal;
        this.toeDate = toeDate;
    }



    public ToeTap createTestToeTap() {

        String currentDate = new SimpleDateFormat("yyyy-mm-dd").format(new Date());

        return new ToeTap(42,73,126,183, currentDate);
    }
}
