package com.example.frosty.als_involve_v3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * Created by frosty on 11/9/17.
 */

public class Login extends Activity {

    DatabaseHelper myDb;

    EditText usernameEdit;
    EditText passwordEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_screen);

        usernameEdit = findViewById(R.id.login_screen_user_editText);
        passwordEdit = findViewById(R.id.login_screen_password_editText);

        myDb = new DatabaseHelper(this);

        checkForUsers();


    }

    @Override
    public void onBackPressed(){
        //TODO do nothing?
    }

    public void checkForUsers(){
        SQLiteDatabase db = myDb.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM USER_TABLE;", new String[] {});
        //no users in table
        if(!c.moveToFirst()){
            db.close();

            Intent startRegistration = new Intent(getApplicationContext(), NewUser.class);

            startActivity(startRegistration);
        }
    }

    public void loginUser(View view) {

        String username = usernameEdit.getText().toString().toLowerCase();
        String password = passwordEdit.getText().toString();

        myDb.getReadableDatabase();

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String tables ="user_table";

        qb.setTables(tables);

        Cursor c = qb.query(myDb.getReadableDatabase(), null, null, null, null, null, null, null, null);

        //get move cursor to row one
        boolean nextRow = true;
        c.moveToPosition(0);

        //get first username in db
        String nameCheck = c.getString(2).toLowerCase();

        //iterate over usernames if not equal
        while(nextRow && !nameCheck.equals(username)){

            nextRow = c.moveToNext();
            nameCheck = c.getString(2);
        }

        //TODO password hash
        //checks if username is in the database
        if(nameCheck.equals(username)){

            String passCheck = c.getString(3);

            //check if password is correct
            if(passCheck.equals(password)){
                myDb.close();

                //return intent with the user's id
                Intent returnFromLogin = new Intent();
                String user = c.getString(1); // get UID

                returnFromLogin.putExtra("user", user);

                setResult(RESULT_OK, returnFromLogin);

                finish();

            //incorrect password
            } else {
                Toast.makeText(getApplicationContext(), "Please make sure Email and Password are correct.", Toast.LENGTH_SHORT).show();
                myDb.close();
            }

        //username is not registered
        } else {
            Toast.makeText(getApplicationContext(), "Please make sure Email and Password are correct.", Toast.LENGTH_SHORT).show();
            myDb.close();
        }
    }
}
