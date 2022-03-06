package com.example.coen390project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected Button currentpHbutton;
    protected Button dataBaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link the id to the UI
        currentpHbutton = findViewById(R.id.currentpHbutton);
        dataBaseButton = findViewById(R.id.dataBaseButton);

        currentpHbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startcurrentpHActivity();
            }
        });

        dataBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PHHistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void startcurrentpHActivity()
    {
        //takes you to the SettingsActivity
        Intent intent = new Intent (this, pHreading.class);// create an intent to move from one activity to the other (current activity, next one)
        startActivity(intent);
    }


    //Change
}
