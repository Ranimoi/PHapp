package com.example.phapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView datetime;
    protected Button currentpHbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datetime = findViewById(R.id.Datetime);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEEE yyy-MMM-dd hh:mm:ss a");
        String dateTime = format.format(calendar.getTime());
        datetime.setText(dateTime);

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