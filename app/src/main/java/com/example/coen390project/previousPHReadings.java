package com.example.coen390project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class previousPHReadings extends AppCompatActivity {
    protected ListView pHListView;
    protected TextView numberOfMeasurements;
    private static final String TAG = "previousPHReadings";
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

    //the listview loaded should match the dates chosen in the settings page!!
    //get the date range compare them with the measurement dates!
    //they should be greater or equal than the start date and smaller or equal than the end date

    protected void loadListView() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        //get the start date and end date
        if (!dbHelper.getDateRange().isEmpty ()) {
            String startDate = "";
            String endDate = "";
            List<DateRange> daterange = dbHelper.getDateRange();
            //get the last ones
            startDate = daterange.get(0).getStartDate();
            endDate = daterange.get(0).getEndDate();
            String[] start = startDate.split("/");
            String[] end = endDate.split("/");
            int startyear = Integer.parseInt(start[0]);
            int startmonth = Integer.parseInt(start[1]);
            int startday = Integer.parseInt(start[2]);
            int endyear = Integer.parseInt(end[0]);
            int endmonth = Integer.parseInt(end[1]);
            int endday = Integer.parseInt(end[2]);
            Log.d(TAG, "startyearBEFORE" + startyear);
            Log.d(TAG, "startyear/month/day" + startyear + "/" + startmonth + "/" + startday);
            Log.d(TAG, "endyear/month/day" + endyear + "/" + endmonth + "/" + endday);


            List<PH> pHValues = dbHelper.getAllValues();



            ArrayList<String> pHListText = new ArrayList<>();

            for (int i = 0; i < pHValues.size(); i++) {
                String temp = "";
                temp += pHValues.get(i).getPH_VALUE() + ", " + pHValues.get(i).getMEASUREMENT_DATE();
                String[] date = pHValues.get(i).getMEASUREMENT_DATE().split("[.,@\\s]");
                int datayear = Integer.parseInt(date[0]);
                int datamonth = Integer.parseInt(date[1]);
                int dataday = Integer.parseInt(date[2]);

                Log.d(TAG, "datayear/month/day" + datayear + "/" + datamonth + "/" + dataday);
                //only output the data in the range selected in the settings activity
                Log.d(TAG, "startyear" + startyear);
                if (datayear >= startyear && datayear <= endyear && datamonth >= startmonth && datamonth <= endmonth
                        && dataday >= startday && dataday <= endday) {
                    pHListText.add(temp);
                    numberOfMeasurements.setText(pHValues.size() + " Measurement");
                }
                else
                    numberOfMeasurements.setText("No measurements recorded for this time range");

            }
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pHListText);
            //pHListView = findViewById(R.id.pHListView);
            pHListView.setAdapter(arrayAdapter);
        }
        //else if no setting time range is selected output them all
        else
        {
            List<PH> pHValues = dbHelper.getAllValues();
            ArrayList<String> pHListText = new ArrayList<>();

            for (int i = 0; i < pHValues.size(); i++) {
                String temp = "";
                temp += "pH Value: " + pHValues.get(i).getPH_VALUE() + ", Reading Performed on " + pHValues.get(i).getMEASUREMENT_DATE();
                pHListText.add(temp);
            }
            numberOfMeasurements.setText(pHValues.size() + " Measurement");
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pHListText);
            //pHListView = findViewById(R.id.pHListView);
            pHListView.setAdapter(arrayAdapter);


        }

    }



}