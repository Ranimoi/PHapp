package com.example.coen390project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.util.ArrayList;
import java.util.List;

public class previousPHReadings extends AppCompatActivity {
    protected ListView pHListView;
    protected TextView numberOfMeasurements;
    protected GraphView phGraphView;

    private static final String TAG = "previousPHReadings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_phreadings);
        pHListView = findViewById(R.id.ListViewPH);
        numberOfMeasurements = (TextView) findViewById(R.id.NumberReadingstextView);
        phGraphView = findViewById(R.id.phGraphView);
        pHListView.setVisibility(View.VISIBLE);
        phGraphView.setVisibility(View.INVISIBLE);
        loadListView();



        //enable the up button
        Toolbar myToolbar = (Toolbar) findViewById(R.id.previousPHtoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("previous pH");

    }
    //show the toolbar with the settings dots
    //default if the button is click then we will have menu_settings_2 showing

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }
    //the listview loaded should match the dates chosen in the settings page!!
    //get the date range compare them with the measurement dates!
    //they should be greater or equal than the start date and smaller or equal than the end date

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        //get the courses from the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        //hold an array list string of all the profiles that we want to display
        ArrayList<String> profileListText = new ArrayList<>();
        int j = 1;

        switch (item.getItemId()) {
            case R.id.list_button:
                phGraphView.setVisibility(View.INVISIBLE);
                pHListView.setVisibility(View.VISIBLE);
                loadListView();
                return true;




        case R.id.graph_button:
            pHListView.setVisibility(View.INVISIBLE);
            numberOfMeasurements.setVisibility(View.INVISIBLE);
            phGraphView.setVisibility(View.VISIBLE);
            loadGraphView();
            return true;



        default:
        // If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);

    }
}


    protected void loadGraphView(){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<PH> pHValues = dbHelper.getAllValues();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0,1),
                new DataPoint(1,3)
//            for (int i = 0; i < pHValues.size(); i++)
//            {
//                String[] date = pHValues.get(i).getMEASUREMENT_DATE().split("[.,@\\s]");
//                int datayear = Integer.parseInt(date[0]);
//                int datamonth = Integer.parseInt(date[1]);
//                int dataday = Integer.parseInt(date[2]);
//                new DataPoint(pHValues.get(i).getPH_VALUE(),datayear);
//            }
        });
    }

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