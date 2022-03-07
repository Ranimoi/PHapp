package com.example.coen390project;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import  com.example.coen390project.DatabaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;

public class pHCurrentReading extends AppCompatActivity {

    protected TextView pHtextView;
    protected Button readpHbutton;
    protected TextView informationTextView;
    protected TextView pHTextView;
    protected Button saveButton;

    //list to store the values of the
    List<Float> list = new ArrayList<Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phcurrent_reading);
        pHtextView = (TextView) findViewById(R.id.pHtextView);
        readpHbutton = (Button) findViewById(R.id.readpHbutton);
        informationTextView= (TextView) findViewById(R.id.informationTextView);
        pHTextView = (TextView) findViewById(R.id.pHTextView) ;
        saveButton = (Button) findViewById(R.id.saveButton);
        readpHbutton.setOnClickListener(onClickreadpHbutton);
        saveButton.setOnClickListener(onClicksavebutton);


        //enable the up button
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("pH current Reading");


    }

    //when the activity starts
    @Override
    protected void onStart() {
        super.onStart();
        generateRandomPH();

    }

    //button on click to start reading the pH
    private Button.OnClickListener onClickreadpHbutton = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            displaypH(list);
        }
    };

    private Button.OnClickListener onClicksavebutton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Float ph_value;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                int leftLimit = 1;
                int rightLimit = 10;
                ph_value = leftLimit + (Float) (new Random().nextFloat() * (rightLimit - leftLimit));
            }
            catch (Exception e)
            {
                Toast.makeText(pHCurrentReading.this, "Operation Failed, missing or empty pH value" ,Toast.LENGTH_LONG).show();
                return;
            }

            String date = sdf.format(new Date());

            DatabaseHelper dbHelper = new DatabaseHelper(pHCurrentReading.this);
            if(!(ph_value < 0 || ph_value >14))
                dbHelper.insertpH(new PH(ph_value));
            else
            {
                Toast.makeText(pHCurrentReading.this, "Operation Failed, pH value is not within range" ,Toast.LENGTH_LONG).show();
            }
        }
    };


    protected List<Float> generateRandomPH() {

        Float min = 5.0f, max = 7.0f;
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(rnd.nextFloat()*((max - min + 1) + min));
        }

        return list;
    }


    protected void displaypH(List<Float> list) {
        for (int i = 0; i < list.size(); i++) {

            Float ph_value = list.get(i);
            //set the color of the circle ring based on the pH
            if (ph_value<=1 &&  0<=ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_red_dark));
            }
            if (ph_value<=3 &&  1<ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_red_light));
            }
            if (ph_value<=4 &&  3<ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_orange_dark));
            }
            if (ph_value<=5 &&  4<ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_orange_light));
            }
            if (ph_value<=7 &&  5<ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_green_light));
            }
            if (ph_value<=9 &&  7<ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_green_dark));
            }
            if (ph_value<=10 &&  9<ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_blue_bright));
            }
            if (ph_value<12 &&  11<=ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_blue_light));
            }
            if (ph_value<13 &&  12<=ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_blue_dark));
            }
            if (ph_value<=14 &&  13<=ph_value) {
                pHtextView.getBackground().setTint(getResources().getColor(android.R.color.holo_purple));
            }

            //output a toast message for the pH (acid, basic, alkalin)
            if (ph_value<7 &&  0<=ph_value) {
                pHTextView.setText("Acidic pH");
            }
            if (ph_value==7) {
                pHTextView.setText("Neutral pH");
            }
            if (ph_value<=14 &&  7<ph_value) {
                pHTextView.setText("Basic pH");
            }

            pHtextView.setText(String.valueOf(list.get(i)));

            Bundle bundle = getIntent().getExtras();

            if (bundle != null) {
                //need to remove the null in the list bc it causes errors in the sql query
                int buttonClicked = bundle.getInt("ButtonSelected");
                if (buttonClicked==0) {
                    // water should be at a pH of of 6.5 to 8.5.
                    if (ph_value<=8.5 &&  6.5<=ph_value)
                        informationTextView.setText("The water IS SAFE for drinking purposes since it's pH is in the range of 6.5 to 8.5");
                    else
                        informationTextView.setText("The water IS NOT SAFE for drinking purposes since it's pH is out of the range of 6.5 to 8.5");
                }

                if(buttonClicked==1) {
                    // water for crop production 5.0 and 7.0.
                    if (ph_value<=7 &&  5<=ph_value)
                        informationTextView.setText("The water IS SAFE for crop production since it's pH in the range of 5 to 7");
                    else
                        informationTextView.setText("The water IS  NOT SAFE for crop production since it's pH is out of the range of 5 to 7");

                }
                if(buttonClicked==2)
                    //chemical experiment/other
                    informationTextView.setText("Depending on the liquid measured the pH can be safe or not safe. Please look up online the normal pH for safe measure");

            }



        }



    }



}
