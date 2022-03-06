package com.example.coen390project;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class PHHistoryActivity extends AppCompatActivity {
    protected ListView pHListView;
    protected TextView numberOfMeasurements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ph_history_activity);
        pHListView = findViewById(R.id.pHListView);
        numberOfMeasurements = (TextView) findViewById(R.id.numberOfMeasurements);


        loadListView();


        //enable the up button
        Toolbar phHistoryToolbar = (Toolbar) findViewById(R.id.phHistoryToolbar);
        setSupportActionBar(phHistoryToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar bb = getSupportActionBar();

        // Enable the Up button
        bb.setDisplayHomeAsUpEnabled(true);
        bb.setTitle("pH Log");
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.ph_history_activity);
        loadListView();
    }

    protected void loadListView() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<PH> pHValues = dbHelper.getAllValues();

        numberOfMeasurements.setText(pHValues.size() + " Measurements");

        ArrayList<String> pHListText = new ArrayList<>();

        for (int i = 0; i < pHValues.size(); i++) {
            String temp = "";
            temp += pHValues.get(i).getPH_VALUE() + ", " + pHValues.get(i).getMEASUREMENT_DATE();

            pHListText.add(temp);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pHListText);
        pHListView = findViewById(R.id.pHListView);
        pHListView.setAdapter(arrayAdapter);
    }

        @Override
        protected void onDestroy() {
        super.onDestroy();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
    }
}
