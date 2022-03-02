package com.example.phapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    protected Button currentpHbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link the id to the UI
        currentpHbutton = findViewById(R.id.currentpHbutton);

        currentpHbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startcurrentpHActivity();
            }
        });
    }
        private void startcurrentpHActivity()
        {
            //takes you to the SettingsActivity
            Intent intent = new Intent (this, pHreading.class);// create an intent to move from one activity to the other (current activity, next one)
            startActivity(intent);
        }



}