package com.example.phapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class pHCurrentReading extends AppCompatActivity {

    protected TextView pHtextView;
    protected Button readpHbutton;
    protected TextView informationTextView;
    protected TextView pHTextView;
    //list to store the values of the
    List<Integer> list = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phcurrent_reading);
        pHtextView = (TextView) findViewById(R.id.pHtextView);
        readpHbutton = (Button) findViewById(R.id.readpHbutton);
        informationTextView= (TextView) findViewById(R.id.informationTextView);
        pHTextView = (TextView) findViewById(R.id.pHTextView) ;
        readpHbutton.setOnClickListener(onClickreadpHbutton);

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


    protected List<Integer> generateRandomPH() {

        int min = 5, max = 7;
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(rnd.nextInt((max - min + 1) + min));
        }

        return list;
    }

    //button on click to start reading the pH
    private Button.OnClickListener onClickreadpHbutton = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            displaypH(list);
        }


    };


        protected void displaypH(List<Integer> list) {
            for (int i = 0; i < list.size(); i++) {

                int ph_value = list.get(i);
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
