package com.example.coen390project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class previousPHReadings extends AppCompatActivity {
    protected ListView pHListView;
    protected TextView numberOfMeasurements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_phreadings);
        pHListView = findViewById(R.id.ListViewPH);
        numberOfMeasurements = (TextView) findViewById(R.id.NumberReadingstextView);



        //enable the up button
        Toolbar myToolbar = (Toolbar) findViewById(R.id.previousPHtoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("previous pH");

        loadListView();



    }


    protected void loadListView() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<PH> pHValues = dbHelper.getAllValues();

        numberOfMeasurements.setText(pHValues.size() + " Measurement");

        ArrayList<String> pHListText = new ArrayList<>();

        for (int i = 0; i < pHValues.size(); i++) {
            String temp = "";
            temp += pHValues.get(i).getPH_VALUE() + ", " + pHValues.get(i).getMEASUREMENT_DATE();

            pHListText.add(temp);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pHListText);
        //pHListView = findViewById(R.id.pHListView);
        pHListView.setAdapter(arrayAdapter);
    }



}