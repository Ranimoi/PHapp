package com.example.phapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pHreading extends AppCompatActivity {

    protected Button drinkableWaterbutton;
    protected Button agriculturebutton;
    protected Button otherbutton;
    protected TextView pHpromptTextView;
    protected int value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phreading);
        drinkableWaterbutton = (Button) findViewById(R.id.drinkableWaterbutton);
        agriculturebutton = (Button) findViewById(R.id. agriculturebutton);
        otherbutton = (Button) findViewById(R.id.otherbutton);
        pHpromptTextView = (TextView) findViewById(R.id.pHpromptTextView);
        drinkableWaterbutton.setOnClickListener(onClickdrinkableWaterbutton);
        agriculturebutton.setOnClickListener(onClickagriculturebutton);
        otherbutton.setOnClickListener(onClickotherbutton);

        //enable the up button
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar1);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("pH selection");


    }

    //button on click to start reading the pH
    private Button.OnClickListener onClickdrinkableWaterbutton = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            value=0;
            startPHCurrentReadingActivity();
        }


    };
    //button on click to start reading the pH
    private Button.OnClickListener onClickagriculturebutton = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            value=1;
            startPHCurrentReadingActivity();
        }


    };
    //button on click to start reading the pH
    private Button.OnClickListener onClickotherbutton = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            value=2;
            startPHCurrentReadingActivity();

        }


    };

    private void startPHCurrentReadingActivity() {
        //takes you to the phcurrentReadingActivity
        Intent intent = new Intent(this, pHCurrentReading.class);// create an intent to move from one activity to the other (current activity, next one)
        intent.putExtra("ButtonSelected",value);
        startActivity(intent);
    }
}