package com.example.coen390project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    protected Button currentpHbutton;
    protected TextView AccessTimetextView;
    protected TextView welcometextView;
    protected Button previouspHButton;
    protected Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link the id to the UI
        currentpHbutton = findViewById(R.id.currentpHbutton);
        currentpHbutton.setOnClickListener(onClickcurrentpHbutton);
        previouspHButton = findViewById(R.id.previouspHButton);
        settingsButton = findViewById(R.id.settingsButton);
        currentpHbutton.setOnClickListener(onClickcurrentpHbutton);
        previouspHButton.setOnClickListener(onClickpreviouspHButton);
        settingsButton.setOnClickListener(onClicksettingsButton);
        AccessTimetextView= findViewById(R.id.AccessTimetextView);
    }
    @Override
    protected void onStart() {
        super.onStart();
        ;
        // get the latest reading ph date
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        String DatePH = "";
        if (dbHelper.getLastReadingDate() != null){
            DatePH = (dbHelper.getLastReadingDate()).toString();
        }

        if (DatePH != null) {
            AccessTimetextView.setText("Last pH reading done: " + DatePH);
        } else {
            AccessTimetextView.setText("No pH reading done yet");
        }

    }

    //button on click to start reading the pH
    private Button.OnClickListener onClickcurrentpHbutton= new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            startcurrentpHActivity();
        }

    };
    private void startcurrentpHActivity()
    {
        //takes you to the SettingsActivity
        Intent intent = new Intent (this, pHreading.class);// create an intent to move from one activity to the other (current activity, next one)
        startActivity(intent);
    }
    //button on click to view the previous pH
    private Button.OnClickListener onClickpreviouspHButton= new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            startpreviouspHActivity();
        }

    };
    private void startpreviouspHActivity()
    {
        //takes you to the SettingsActivity
        Intent intent = new Intent (this, previousPHReadings.class);// create an intent to move from one activity to the other (current activity, next one)
        startActivity(intent);
    }
    //button to go to the settings activity
    private Button.OnClickListener onClicksettingsButton= new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            startsettingsActivity();
        }

    };
    private void startsettingsActivity()
    {
        //takes you to the SettingsActivity
        Intent intent = new Intent (this, SettingsActivity.class);// create an intent to move from one activity to the other (current activity, next one)
        startActivity(intent);
    }



}