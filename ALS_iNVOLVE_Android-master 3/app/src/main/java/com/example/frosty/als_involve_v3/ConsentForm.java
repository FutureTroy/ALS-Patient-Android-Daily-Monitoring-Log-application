package com.example.frosty.als_involve_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ConsentForm extends AppCompatActivity {

    Button selection1, selection2, next;

    RadioGroup buttongroup;

    TextView text1, text2, text3;

    //TextView title = (TextView) findViewById(R.id.title);
    //might use later
    String paragraph1, paragraph2, paragraph3; // used to alter the textviews. may be unnecessary.
    String buttonText1, buttonText2; //used to set the

    int CA1=0, CA2=0, CA3=0, CA4=0, CA5=0; //CA means correct answer. 0=unused, 1=correct, 2=incorrect
    int pageCount=0; //used to know when 'next' should read the CA's and either go to main screen or restart.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_consent_form);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); */ //just in case this is necessary


        selection1 = (Button) findViewById(R.id.button1); //top radio button
        selection2 = (Button) findViewById(R.id.button2); //bottom radio button
        next = (Button) findViewById(R.id.next); // the button on the bottom center of screen. "next"
        buttongroup = (RadioGroup) findViewById(R.id.consent_radio_group);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);

        selection1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //the order of correct answers are selection1, selection1, selection2, selection1, selection2.
                if(pageCount==0 && (CA1==0 || CA1==2)) { //if selection2 was pressed or neither was pressed then pressing selection1 makes it correct.
                    CA1=1; //correct
                }
                if(pageCount==1 && (CA2==0 || CA2==2)) { //if selection2 was pressed or neither was pressed then pressing selection1 makes it correct.
                    CA2=1; // correct
                }
                if(pageCount==3 && (CA3==0 || CA3==1)) { //if selection2 was pressed or neither was pressed then pressing selection1 makes it incorrect.
                    CA3=2; //incorrect
                }
                if(pageCount==4 && (CA4==0 || CA4==2)) { //if selection2 was pressed or neither was pressed then pressing selection1 makes it correct.
                    CA4=1; //correct
                }
                if(pageCount==5 && (CA5==0 || CA5==1)) { //if selection2 was pressed or neither was pressed then pressing selection1 makes it incorrect.
                    CA5=2; //incorrect
                }
            } //the pageCount numbers need to change as necessary with the legalese text and pages. These should match the below groups.
        });

        selection2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //the order of correct answers are selection1, selection1, selection2, selection1, selection2.
                if(pageCount==0 && (CA1==0 || CA1==1)) { //if selection1 was pressed or neither was pressed then pressing selection2 makes it incorrect.
                    CA1=2; //incorrect
                }
                if(pageCount==1 && (CA2==0 || CA2==1)) { //if selection1 was pressed or neither was pressed then pressing selection2 makes it incorrect.
                    CA2=2; //incorrect
                }
                if(pageCount==3 && (CA3==0 || CA3==2)) { //if selection1 was pressed or neither was pressed then pressing selection2 makes it correct.
                    CA3=1; //correct
                }
                if(pageCount==4 && (CA4==0 || CA4==1)) { //if selection1 was pressed or neither was pressed then pressing selection2 makes it incorrect.
                    CA4=2; //incorrect
                }
                if(pageCount==5 && (CA5==0 || CA5==2)) { //if selection1 was pressed or neither was pressed then pressing selection2 makes it correct.
                    CA5=1; //correct
                }
            } //the pageCount numbers need to change as necessary with the legalese text and pages. These should match the above groups.
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pageCount++;
                if(pageCount == 1) { //the first actual question in relation to legality.
                    paragraph1="The privacy of your information is our top priority. Your personal information will never be shared and will be encrypted on this device.";
                    text1.setText(paragraph1);
                    paragraph2="We ask for your personal information so that we can create a Global Unique ID (GUID) to allow cross-referencing for research and studies while maintaining your privacy.";
                    text2.setText(paragraph2);
                    paragraph3="The GUID has been adopted across the ALS research community and we expend every effort to ensure your personal information is only accessible by you.\nWhich statement best fits our personal information policy for this study?" ;
                    text3.setText(paragraph3);

                    buttonText1 = "My personal information will be protected and never shared.";
                    selection1.setText(buttonText1);
                    buttonText2 = "Anyone will be able to see my personal information.";
                    selection2.setText(buttonText2);

                    buttongroup.clearCheck();

                    selection1.setSelected(false);
                    selection2.setSelected(false);
                }
                if(pageCount == 2) {
                    paragraph1="Global research is critical to finding treatments for ALS. Your provided research data will be shared our research partners within the ALS/MND community. Several of these are listed below.";
                    text1.setText(paragraph1);
                    paragraph2="AuralAnalytics, Origent Data Sciences, NEALS Northeast ALS Consortium, CDC, ATSDR (Agency for Toxic Substances and Disease Registry, NIH (National Institutes of Health).";
                    text2.setText(paragraph2);
                    paragraph3="You may also choose to share your research data with anyone you wish. Private sharing of your data is at your own discretion and your own risk";
                    text3.setText(paragraph3);

                    buttongroup.clearCheck();

                    selection1.setVisibility(view.INVISIBLE);
                    selection2.setVisibility(view.INVISIBLE);
                }
                if(pageCount == 3) { //the second actual question in relation to legality.
                    paragraph1="We do not recommend that you share or do not share your data, as it is entirely your choice and responsibility. We take no responsibility for the choices made by you or others based on this sharing of information.";
                    text1.setText(paragraph1);
                    paragraph2="Any and all medical decisions should still be made by consulting a licensed medical professional, with that professional performing their own assessments.";
                    text2.setText(paragraph2);
                    paragraph3="The ALS Monitoring App is purely for personal reference and research data collection. It is not a diagnostic tool.\nWho will you share your research data with?";
                    text3.setText(paragraph3);

                    selection1.setVisibility(view.VISIBLE); //might not be needed.
                    selection2.setVisibility(view.VISIBLE); //might not be needed.

                    buttonText1 = "My data will be shared only with global researchers.";
                    selection1.setText(buttonText1);
                    buttonText2 = "My data will be available for global ALS research and anyone I send it to.";
                    selection2.setText(buttonText2);

                    buttongroup.clearCheck();

                    selection1.setSelected(false);
                    selection2.setSelected(false);
                }
                if(pageCount == 4) {
                    paragraph1="The purpose of this study is to gather long term observational data regarding symptom progression of ALS/MND. As such there may be updates to application to further support this effort.";
                    text1.setText(paragraph1);
                    paragraph2="Any and all NON-PERSONAL data you provide after your consent will always remain a part of the data repository.";
                    text2.setText(paragraph2);
                    paragraph3="You may choose at any point to leave the study by simply deleting the application from your device.\nHow long will the study last?";
                    text3.setText(paragraph3);

                    buttonText1 = "Any and all NON-PERSONAL data will always be part of the study.";
                    selection1.setText(buttonText1);
                    buttonText2 = "When i leave the study, my data will be removed as well.";
                    selection2.setText(buttonText2);

                    buttongroup.clearCheck();

                    selection1.setSelected(false);
                    selection2.setSelected(false);
                }
                if(pageCount == 5) {
                    paragraph1="Previously, there has been no simple method for people to monitor their changing symptoms other than a 20yrd old subject survey. Now, mobile apps empower people to provide valuable research data and perform self monitoring.";
                    text1.setText(paragraph1);
                    paragraph2="The ALS Monitoring App is meant to gather symptom progression data over the long term and help you monitor your progression more closely as well.";
                    text2.setText(paragraph2);
                    paragraph3="Additionally, it can help patients and clinical support understand more about ALS/MND.\n How will this benefit ALS/MND patients?";
                    text3.setText(paragraph3);

                    buttonText1 = "Only I will benefit from the use of this app.";
                    selection1.setText(buttonText1);
                    buttonText2 = "It will help others understand ALS through my research data.";
                    selection2.setText(buttonText2);

                    buttongroup.clearCheck();

                    selection1.setSelected(false);
                    selection2.setSelected(false);
                }
                if(pageCount == 6) {
                    if(CA1==1 && CA2==1 && CA3==1 && CA4==1 && CA5==1){

                        //added Toasts for debugging
                        Toast.makeText(getApplicationContext(), "All correct", Toast.LENGTH_SHORT).show(); //for debugging
                        finish();
                    }
                    else {
                        pageCount = 0;
                        paragraph1="Thank you for downloading the ALS Monitoring App. Before we begin we need to confirm your understanding and agreement to participate in this research study.";
                        text1.setText(paragraph1);
                        paragraph2="Read each selection carefully, as afterward you will be presented with a choice between several statements. Select the statement that best fits what you read.";
                        text2.setText(paragraph2);
                        paragraph3="You will need to select the correct statement from each selection in order to ensure your understanding of and consent to participation in the study. Once you have made your choice, click 'Next' at the bottom of each screen.";
                        text3.setText(paragraph3);

                        buttonText1 = "I agree";
                        selection1.setText(buttonText1);
                        buttonText2 = "I disagree";
                        selection2.setText(buttonText2);

                        buttongroup.clearCheck();

                        selection1.setSelected(false);
                        selection2.setSelected(false);

                        //debuggin toast
                        Toast.makeText(getApplicationContext(),"Incorrect Answer(s)", Toast.LENGTH_SHORT).show(); //for debugging
                    }
                }
                //the above resets the textviews and the button texts in each if statement
            }
        });
    }

}