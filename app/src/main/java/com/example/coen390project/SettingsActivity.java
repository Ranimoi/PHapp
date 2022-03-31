package com.example.coen390project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    protected Button datePickerbutton;
    protected TextView DatetextView;

    protected Button startDatebutton;
    protected Button endDatebutton;
    protected Button savebutton;
    protected TextView startDateTextView;
    protected TextView endDateTextView;
    protected int choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //enable the up button
        Toolbar myToolbar = (Toolbar) findViewById(R.id.Settingstoolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Settings");

        //date picker
        startDatebutton = findViewById(R.id.startDatebutton);
        endDatebutton = findViewById(R.id.endDatebutton);
        startDateTextView = findViewById(R.id.startDatetextView);
        endDateTextView = findViewById(R.id.endDatetextView);
        savebutton = findViewById(R.id.savebutton);

        startDatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
                choice=1;
                savebutton.setEnabled(true);
            }
        });
        endDatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
                choice=2;
                savebutton.setEnabled(true);
            }
        });
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //convert the date into integer, substract the end date with the start date and see if it is >0
                //if not then the start date is not before the end date therefore it should not be saved
                String [] startdate= (startDateTextView.getText().toString()).split("/");
                String [] enddate= (endDateTextView.getText().toString()).split("/");
                int startdateYear =Integer.parseInt(startdate[0]);
                int startdatemonth =Integer.parseInt(startdate[1]);
                int startdateDay =Integer.parseInt(startdate[2]);
                int enddateYear =Integer.parseInt(enddate[0]);
                int enddatemonth =Integer.parseInt(enddate[1]);
                int enddateDay =Integer.parseInt(enddate[2]);


                if ((enddateDay-startdateDay)>0&&(enddatemonth-startdatemonth)>=0&&(enddateYear-startdateYear)>=0)
                {
                   //toast successful save output a toast message
                    Toast.makeText(SettingsActivity.this, "Dates saved", Toast.LENGTH_SHORT).show();
                    savebutton.setEnabled(false);
                    //send the dates to the databasehelper!!!
                    //need a start date and end date column
                    //also a function that converts the dates to integers to select the range of dates to display
                    DatabaseHelper dbHelper = new DatabaseHelper(SettingsActivity.this);
                    String startDate= startDateTextView.getText().toString();
                    String endDate= endDateTextView.getText().toString();
                    dbHelper.insertDateRange(new DateRange(startDate,endDate));

                }
                else if ((enddateDay-startdateDay)<0&&(enddatemonth-startdatemonth)>=0&&(enddateYear-startdateYear)>=0)
                {
                    //toast successful save output a toast message
                    Toast.makeText(SettingsActivity.this, "Dates saved", Toast.LENGTH_SHORT).show();
                    savebutton.setEnabled(false);
                    //send the dates to the databasehelper!!!
                    //need a start date and end date column
                    //also a function that converts the dates to integers to select the range of dates to display
                    DatabaseHelper dbHelper = new DatabaseHelper(SettingsActivity.this);
                    String startDate= startDateTextView.getText().toString();
                    String endDate= endDateTextView.getText().toString();
                    dbHelper.insertDateRange(new DateRange(startDate,endDate));

                }
                else
                {
                    //toast successful save output a toast message
                    Toast.makeText(SettingsActivity.this, "Dates NOT saved", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
    private void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR)
                ,Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        //set todays date as the max date that can be selected
        datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String Date=year+"/"+(month+1)+"/"+dayOfMonth;
        if(choice==1) {
            startDateTextView.setText(Date);
        }
        if (choice==2)
        {
            endDateTextView.setText(Date);
        }


    }
    @Override
    protected void onStart() {
        super.onStart();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        if (!dbHelper.getDateRange().isEmpty ()){
            String startDate= "";
            String endDate= "";
           List<DateRange> daterange = dbHelper.getDateRange();
           //get the last ones
               startDate = daterange.get(0).getStartDate();
               endDate = daterange.get(0).getEndDate();

            startDateTextView.setText(startDate);
            endDateTextView.setText(endDate);
            savebutton.setEnabled(false);
        }

    }

    //when you click the save button the start date should be smaller than the end date to save correctly
    //output a toast to say that its successfully saved if it is the save button should be enabled to false



        /*datePickerbutton = findViewById(R.id.datePickerbutton);
        DatetextView = findViewById(R.id.DatetextView);


        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().
                setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds())).build();




      //button on click to view the previous pH
        datePickerbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            materialDatePicker.show(getSupportFragmentManager(),"Tag_picker");
            materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    DatetextView.setText("Date range selected: "+ "\n"+materialDatePicker.getHeaderText());

                    DatabaseHelper dbHelper = new DatabaseHelper(SettingsActivity.this);
                    dbHelper.insertDateRange(new DateRange(materialDatePicker.getHeaderText()));


                }
            });


        }

    });
}
    @Override
    protected void onStart() {
        super.onStart();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        if (dbHelper.getDateRange() != null){
            DatetextView.setText("Date range selected: "+ "\n"+dbHelper.getDateRange());
        }

    }*/

}