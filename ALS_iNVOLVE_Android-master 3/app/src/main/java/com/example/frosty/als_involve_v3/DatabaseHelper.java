package com.example.frosty.als_involve_v3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by frosty on 10/7/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * These are all the string used in the dataabse.  It contains all table and column names.
     * This way if we need to change a column name all we need to do is change this one variable.
     */


    //DATABASE
    public static final String DATABASE_NAME = "ALS_inVolve.db";

    //FINGER TAPS TABLE
    public static final String FINGER_TAP_TABLE_NAME = "finger_tap_table";
    public static final String FINGER_TAP_COL_1 = "NUM";
    public static final String FINGER_TAP_COL_2 = "UID";
    public static final String FINGER_TAP_COL_3 = "LEFT_TAPS_10";
    public static final String FINGER_TAP_COL_4 = "RIGHT_TAPS_10";
    public static final String FINGER_TAP_COL_5 = "LEFT_TAPS_20";
    public static final String FINGER_TAP_COL_6 = "RIGHT_TAPS_20";
    public static final String FINGER_TAP_COL_7 = "LEFT_TAPS_30";
    public static final String FINGER_TAP_COL_8 = "RIGHT_TAPS_30";
    public static final String FINGER_TAP_COL_9 = "LEFT_TAPS_TOTAL";
    public static final String FINGER_TAP_COL_10 = "RIGHT_TAPS_TOTAL";
    public static final String FINGER_TAP_COL_11 = "DATE";

    //TOE TAPS TABLE
    public static final String TOE_TAP_TABLE_NAME = "toe_tap_table";
    public static final String TOE_TAP_COL_1 = "NUM";
    public static final String TOE_TAP_COL_2 = "UID";
    public static final String TOE_TAP_COL_3 = "SIDE";
    public static final String TOE_TAP_COL_4 = "TOE_TAPS_10";
    public static final String TOE_TAP_COL_5 = "TOE_TAPS_20";
    public static final String TOE_TAP_COL_6 = "TOE_TAPS_30";
    public static final String TOE_TAP_COL_7 = "TOE_TAPS_TOTAL";
    public static final String TOE_TAP_COL_8 = "DATE";

    //ARM ROTATION TABLE
    public static final String ARM_ROTATION_TABLE_NAME = "arm_rotation_table";
    public static final String ARM_ROTATION_COL_1 = "NUM";
    public static final String ARM_ROTATION_COL_2 = "UID";
    public static final String ARM_ROTATION_COL_3 = "APPENDAGE";
    public static final String ARM_ROTATION_COL_4 = "SIDE";
    public static final String ARM_ROTATION_COL_5 = "ROTATION";
    public static final String ARM_ROTATION_COL_6 = "DATE";

    //VOICE TEST TABLE
    public static final String VOICE_TEST_TABLE_NAME = "voice_test_table";
    public static final String VOICE_TEST_COL_1 = "NUM";
    public static final String VOICE_TEST_COL_2 = "UID";
    public static final String VOICE_TEST_COL_3 = "RESULT";
    public static final String VOICE_TEST_COL_4 = "DATE";

    //VITALS TABLE
    public static final String VITALS_TABLE_NAME = "vitals_table";
    public static final String VITALS_COL_1 = "NUM";
    public static final String VITALS_COL_2 = "UID";
    public static final String VITALS_COL_3 = "WEIGHT";
    public static final String VITALS_COL_4 = "HEIGHT";
    public static final String VITALS_COL_5 = "PULSE";
    public static final String VITALS_COL_6 = "BLOOD_PRESSURE_UPPER";
    public static final String VITALS_COL_7 = "BLOOD_PRESSURE_LOWER";
    public static final String VITALS_COL_8 = "OXYGEN";
    public static final String VITALS_COL_9 = "DATE";


    //SUPPLEMENTS TABLE
    public static final String SUPPLEMENT_TABLE_NAME = "supplement_table";
    public static final String SUPPLEMENT_COL_1 = "NUM";
    public static final String SUPPLEMENT_COL_2 = "UID";
    public static final String SUPPLEMENT_COL_3 = "NAME";
    public static final String SUPPLEMENT_COL_4 = "AMOUNT";
    public static final String SUPPLEMENT_COL_5 = "START_DATE";

    //PROFILE TABLE
    public static final String PROFILE_TABLE_NAME = "profile_table";
    public static final String PROFILE_COL_1 = "NUM";
    public static final String PROFILE_COL_2 = "UID";
    public static final String PROFILE_COL_3 = "FNAME";
    public static final String PROFILE_COL_4 = "LNAME";
    public static final String PROFILE_COL_5 = "AGE";
    public static final String PROFILE_COL_6 = "HEIGHT";
    public static final String PROFILE_COL_7 = "START_DATE";
    public static final String PROFILE_COL_8 = "DIAGNOSIS";

    //USER TABLE
    public static final String USER_TABLE_NAME = "user_table";
    public static final String USER_COL_1 = "NUM";
    public static final String USER_COL_2 = "UID";
    public static final String USER_COL_3 = "USERNAME";
    public static final String USER_COL_4 = "PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //TODO double check syntax on all tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create finger tap table with columns num, UID, left_taps_10, right_taps_10,
        // left_taps_20, right_taps_20, left_taps_30, right_taps_30, left_taps_total,
        // right_taps_total, and date
        db.execSQL("CREATE TABLE " + FINGER_TAP_TABLE_NAME + " (" +
                FINGER_TAP_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FINGER_TAP_COL_2 + " TEXT, " +
                FINGER_TAP_COL_3 + " INTEGER, " +
                FINGER_TAP_COL_4 + " INTEGER, " +
                FINGER_TAP_COL_5 + " INTEGER, " +
                FINGER_TAP_COL_6 + " INTEGER, " +
                FINGER_TAP_COL_7 + " INTEGER, " +
                FINGER_TAP_COL_8 + " INTEGER, " +
                FINGER_TAP_COL_9 + " INTEGER, " +
                FINGER_TAP_COL_10 + " INTEGER, " +
                FINGER_TAP_COL_11 + " DATE);"
        );

        //create toe tap table with columns num, UID, toe_taps_10, toe_taps_20, toe_taps_30,
        //toe_taps_total, and date
        db.execSQL("CREATE TABLE " + TOE_TAP_TABLE_NAME + " (" +
                TOE_TAP_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TOE_TAP_COL_2 + " TEXT, " +
                TOE_TAP_COL_3 + " TEXT, " +
                TOE_TAP_COL_4 + " INTEGER, " +
                TOE_TAP_COL_5 + " INTEGER, " +
                TOE_TAP_COL_6 + " INTEGER, " +
                TOE_TAP_COL_7 + " INTEGER, " +
                TOE_TAP_COL_8 + " DATE);"

        );

        //Create arm rotation table with columns num, UID, side, rotation, and date
        db.execSQL("CREATE TABLE " + ARM_ROTATION_TABLE_NAME + " (" +
                ARM_ROTATION_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ARM_ROTATION_COL_2 + " TEXT, " +
                ARM_ROTATION_COL_3 + " TEXT, " +
                ARM_ROTATION_COL_4 + " TEXT, " +
                ARM_ROTATION_COL_5 + " FLOAT, " +
                ARM_ROTATION_COL_6 + " DATE);"
        );

        //create voice test table with columns num, UID, result, and date
        db.execSQL("CREATE TABLE " + VOICE_TEST_TABLE_NAME + " (" +
                VOICE_TEST_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VOICE_TEST_COL_2 + " TEXT, " +
                VOICE_TEST_COL_3 + " FLOAT, " +
                VOICE_TEST_COL_4 + " DATE);"
        );

        //create vitals table with columns num, UID, weight, height, pulse, blood pressure upper,
        //blood pressure lower, and date
        db.execSQL("CREATE TABLE " + VITALS_TABLE_NAME + "(" +
                VITALS_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VITALS_COL_2 + " TEXT, " +
                VITALS_COL_3 + " FLOAT, " +
                VITALS_COL_4 + " FLOAT, " +
                VITALS_COL_5 + " INTEGER, " +
                VITALS_COL_6 + " INTEGER, " +
                VITALS_COL_7 + " INTEGER, " +
                VITALS_COL_8 + " FLOAT, " +
                VITALS_COL_9 + " DATE);"
        );

        //create supplements table with columns num, UID, name, amount, and start date
        db.execSQL("CREATE TABLE " + SUPPLEMENT_TABLE_NAME + " (" +
                SUPPLEMENT_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SUPPLEMENT_COL_2 + " TEXT, " +
                SUPPLEMENT_COL_3 + " TEXT, " +
                SUPPLEMENT_COL_4 + " FLOAT, " +
                SUPPLEMENT_COL_5 + " DATE);"
        );

        //Create profile table with columns num, UID, Fname, Lname, age, height,
        //start Date, and diagnosis date
        db.execSQL("CREATE TABLE " + PROFILE_TABLE_NAME + " (" +
                PROFILE_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PROFILE_COL_2 + " TEXT, " +
                PROFILE_COL_3 + " TEXT, " +
                PROFILE_COL_4 + " TEXT, " +
                PROFILE_COL_5 + " INTEGER, " +
                PROFILE_COL_6 + " FLOAT, " +
                PROFILE_COL_7 + " DATE, " +
                PROFILE_COL_8 + " DATE);"
        );

        //create user table
        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + " (" +
                USER_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COL_2 + " TEXT, " +
                USER_COL_3 + " TEXT, " +
                USER_COL_4 + " TEXT);"
        );
    }

    //this runs when you change the version number on you database, hopefully useful in future
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    //create new row in finger tap table
    public boolean insertFingerTapData(
            String UID, int leftTaps_10, int rightTaps_10, int leftTaps_20, int rightTaps_20,
            int leftTaps_30, int rightTaps_30, int leftTapsTotal, int rightTapsTotal,
            String Date){

        //get the database
        SQLiteDatabase db = this.getWritableDatabase();

        //use contentValues object to tell insert which colums to fill with which data
        ContentValues contentValues = new ContentValues();
        contentValues.put(FINGER_TAP_COL_2, UID);
        contentValues.put(FINGER_TAP_COL_3, leftTaps_10);
        contentValues.put(FINGER_TAP_COL_4, rightTaps_10);
        contentValues.put(FINGER_TAP_COL_5, leftTaps_20);
        contentValues.put(FINGER_TAP_COL_6, rightTaps_20);
        contentValues.put(FINGER_TAP_COL_7, leftTaps_30);
        contentValues.put(FINGER_TAP_COL_8, rightTaps_30);
        contentValues.put(FINGER_TAP_COL_9, leftTapsTotal);
        contentValues.put(FINGER_TAP_COL_10, rightTapsTotal);
        contentValues.put(FINGER_TAP_COL_11, Date);

        //db.insert tries the insert and returns -1 if it is unsuccessful
        long result = db.insert(FINGER_TAP_TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    //create new row in toe tap table
    public boolean insertToeTapData(
            String UID, String side, int taps_10, int taps_20, int taps_30, int tapsTotal,
            String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TOE_TAP_COL_2, UID);
        contentValues.put(TOE_TAP_COL_3, side);
        contentValues.put(TOE_TAP_COL_4, taps_10);
        contentValues.put(TOE_TAP_COL_5, taps_20);
        contentValues.put(TOE_TAP_COL_6, taps_30);
        contentValues.put(TOE_TAP_COL_7, tapsTotal);
        contentValues.put(TOE_TAP_COL_8, date);
        long result = db.insert(TOE_TAP_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //create new row in arm rotation table
    public boolean insertArmRotationData(String UID, String appendage, String side, float rotation, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARM_ROTATION_COL_2, UID);
        contentValues.put(ARM_ROTATION_COL_3, appendage);
        contentValues.put(ARM_ROTATION_COL_4, side);
        contentValues.put(ARM_ROTATION_COL_5, rotation);
        contentValues.put(ARM_ROTATION_COL_6, date);
        long result = db.insert(ARM_ROTATION_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //TODO check what kind of data will be returned with this test
    //TODO make sure this works
    //create new row in the voice table
    public boolean insertVoiceData(String UID, float data, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VOICE_TEST_COL_2, UID);
        contentValues.put(VOICE_TEST_COL_3, data);
        contentValues.put(VOICE_TEST_COL_4, date);
        long result = db.insert(VOICE_TEST_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //TODO check data types
    //TODO make sure this works
    //create new row in vitals table
    public boolean insertVitalsData(
            String UID, float weight, float height, int pulse, int bloodUpper, int bloodLower, float oxygen, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VITALS_COL_2, UID);
        contentValues.put(VITALS_COL_3, weight);
        contentValues.put(VITALS_COL_4, height);
        contentValues.put(VITALS_COL_5, pulse);
        contentValues.put(VITALS_COL_6, bloodUpper);
        contentValues.put(VITALS_COL_7, bloodLower);
        contentValues.put(VITALS_COL_8, oxygen);
        contentValues.put(VITALS_COL_9, date);
        long result = db.insert(VITALS_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //TODO check data types,
    //create new row in the supplements table
    public boolean insertSupplementsData(String UID, String name, int amount, String startDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUPPLEMENT_COL_2, UID);
        contentValues.put(SUPPLEMENT_COL_3, name);
        contentValues.put(SUPPLEMENT_COL_4, amount);
        contentValues.put(SUPPLEMENT_COL_5, startDate);
        long result = db.insert(SUPPLEMENT_TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //create new row in profile table
    public boolean insertNewProfile(String UID, String fname, String lname, int age, int height, String startDate, String diagnosis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COL_2, UID);
        contentValues.put(PROFILE_COL_3, fname);
        contentValues.put(PROFILE_COL_4, lname);
        contentValues.put(PROFILE_COL_5, age);
        contentValues.put(PROFILE_COL_6, height);
        contentValues.put(PROFILE_COL_7,startDate);
        contentValues.put(PROFILE_COL_8, diagnosis);
        long result = db.insert(PROFILE_TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //TODO check database
    public boolean createNewUser(String UID, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_2, UID);
        contentValues.put(USER_COL_3, username);
        contentValues.put(USER_COL_4, password);
        long result = db.insert(USER_TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public User getUser(String uid){
        User user = new User();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT FNAME, LNAME, AGE, HEIGHT, START_DATE, DIAGNOSIS FROM profile_table WHERE UID = ?", new String[] {uid});

        if(c.moveToFirst()){
            user.fname = c.getString(0);
            user.lname = c.getString(1);
            user.age = c.getInt(2);
            user.height = c.getInt(3);
            user.startDate = c.getString(4);
            user.diagnosisDate = c.getString(5);
            System.out.print("Profile Loaded");
        } else {
            System.out.print("failed to get profile");
        }

        db.close();
        return user;
    }

    public String getUID(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("Select UID from user_table where num=?;", new String[] {"1"});

        String UID;

        if(c.moveToFirst()){
            UID = c.getString(0);
        }
        else{
            UID = "-1";
        }

        db.close();
        return UID;

    }

    public FingerTap getFingerTap(String uid){
        FingerTap fingertap = new FingerTap();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT LEFT_TAPS_10, RIGHT_TAPS_10, LEFT_TAPS_20, RIGHT_TAPS_20, LEFT_TAPS_30, RIGHT_TAPS_30, LEFT_TAPS_TOTAL, RIGHT_TAPS_TOTAL, DATE FROM finger_tap_table WHERE UID = ?", new String[] {uid});

        if(c.moveToFirst()){
            fingertap.leftTaps_10 = c.getInt(0);
            fingertap.rightTaps_10 = c.getInt(1);
            fingertap.leftTaps_20 = c.getInt(2);
            fingertap.rightTaps_20 = c.getInt(3);
            fingertap.leftTaps_30 = c.getInt(4);
            fingertap.rightTaps_30 = c.getInt(5);
            fingertap.leftTapsTotal = c.getInt(6);
            fingertap.rightTapsTotal = c.getInt(7);
            System.out.print("finger results loaded");
        } else {
            System.out.print("failed to load results");
        }

        return fingertap;
    }

    public ToeTap getToeTap(String uid){
        ToeTap toetap = new ToeTap();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT TOE_TAPS_10, TOE_TAPS_20, TOE_TAPS_30, TOE_TAPS_TOTAL, DATE FROM finger_tap_table WHERE UID = ?", new String[] {uid});

        if(c.moveToFirst()){
            toetap.toeTaps_10 = c.getInt(0);
            toetap.toeTaps_20 = c.getInt(1);
            toetap.toeTaps_30 = c.getInt(2);
            toetap.toeTapsTotal = c.getInt(3);
            System.out.print("Toe results loaded");
        } else {
            System.out.print("failed to load results");
        }

        return toetap;
    }
}