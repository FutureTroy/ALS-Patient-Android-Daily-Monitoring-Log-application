package com.example.frosty.als_involve_v3;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by frosty on 10/9/17.
 */

public class User {

    //all info that relates to a user
    private int UID;
    private String email;
    public String fname;
    public String lname;
    public int age;
    public int height;
    public String startDate;
    public String diagnosisDate;

    public User(){

    }

    //user constructor with "full" data
    protected User(String email, String fname, String lname, int age, int height, String startDate, String diagnosisDate) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.height = height;
        this.startDate = startDate;
        this.diagnosisDate = diagnosisDate;
    }

    //fake user to use in main activity until we get users all set up
    public User createTestUser() {

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        return new User("Test@test.com", "Fake", "User", 100, 8, currentDate, "2000-12-20");
    }

}
