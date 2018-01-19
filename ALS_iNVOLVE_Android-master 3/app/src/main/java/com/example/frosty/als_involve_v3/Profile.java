package com.example.frosty.als_involve_v3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import android.util.Base64;
import android.widget.EditText;
//turns out it was android.util, so possibly others might work if necessary

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Profile extends AppCompatActivity {

    Button save = (Button) findViewById(R.id.save); //R = root, id = ? and "save" is whatever the ID is of object

    String fNameS, lNameS, sexS, raceS, countryS, diagDateS, startDateS, heightS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);

        //get the strings from database, decrypt and display them here************************

        save.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        EditText fName = (EditText) findViewById(R.id.fName);
                                        EditText lName = (EditText) findViewById(R.id.lName);
                                        EditText sex = (EditText) findViewById(R.id.sex);
                                        EditText race = (EditText) findViewById(R.id.race);
                                        EditText country = (EditText) findViewById(R.id.country);

                                        EditText diagDate = (EditText) findViewById(R.id.diagDate); //possibly should be a date input field instead? field grab will be same/similar
                                        EditText startDate = (EditText) findViewById(R.id.startDate); //possibly should be a date input field instead? field grab will be same/similar

                                        EditText height = (EditText) findViewById(R.id.height);

                                        fNameS = fName.getText().toString();
                                        lNameS = lName.getText().toString();
                                        sexS = sex.getText().toString();
                                        raceS = race.getText().toString();
                                        countryS = country.getText().toString();
                                        heightS = height.getText().toString();


                                        //both dates here.


                                        //then encrypt the strings and send to database.

                                    }
                                }

        );

    }


}

//the class that handles the encryption and decryption. also writes it to file.
class ALSEncryption {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void main(String[] args) throws IOException {

        FileInputStream in = null; // might not be needed
        FileOutputStream out = null; // might not be needed

        try {
            //call encryption and export here, as well as import and decryption from database if not used in android app

        }
        catch(Exception e){/*maybe put something here*/}
    }

    public static void setKey(String encryptKey){

        MessageDigest hash = null;
        try {

            key = encryptKey.getBytes("UTF-8");
            hash = MessageDigest.getInstance("SHA-256");// is currently, and has to be 256 bit encryption
            key = hash.digest(key);
            key = Arrays.copyOf(key, 32);// 256 bit is 32 bytes long for the array copy (drop to 16 if doesn't work)

            secretKey = new SecretKeySpec(key, "AES");

        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){ //^ v the difference im unsure of
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt, String key){ //might need to be static?

        try {

            setKey(key);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding"); // should use GCM mode
            cipher.init(Cipher.ENCRYPT_MODE,  secretKey);

            String result;
            result = Base64.encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")),1);
            //encodeToString(byte[] input, int flags) byte[] = ?? , flags = NO_PADDING <- (might need to be 1)
            //i think this is what i need to do instead of the result = XX above
            return result;

        } catch(Exception e){
            System.out.println("Error while encrypting.");
        }
        return null;
    }

    public byte[] decrypt(String strToDecrypt, String key){ //might need to be static?

        try{

            setKey(key);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding"); // should use GCM mode
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] returned;
            returned = Base64.decode(cipher.doFinal(strToDecrypt.getBytes("UTF-8")),1);
            //return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            //the byte array should work, if not let me know.
            return returned;

        } catch(Exception e){
            System.out.println("Error while decrypting.");
        }
        return null;
    }
}

/* the sites im looking at

http://www.developer.com/ws/android/encrypting-with-android-cryptography-api.html <- super important
https://stackoverflow.com/questions/29858103/why-doesnt-android-studio-recognize-base64-encodebase64
https://developer.android.com/reference/android/util/Base64.html <- important
https://stackoverflow.com/questions/7360403/base-64-encode-and-decode-example-code
https://stackoverflow.com/questions/33995233/android-aes-encryption-decryption-using-gcm-mode-in-android
https://stackoverflow.com/questions/6788018/android-encryption-decryption-with-aes
https://stackoverflow.com/questions/4322182/base64-encoder-and-decoder
https://stackoverflow.com/questions/6448307/encrypt-and-decrypt-a-string-in-android
https://stackoverflow.com/questions/40123319/easy-way-to-encrypt-decrypt-string-in-android

 */

