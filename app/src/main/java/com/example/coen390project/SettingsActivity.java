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
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class SettingsActivity extends AppCompatActivity {

    protected Button datePickerbutton;
    protected TextView DatetextView;

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


        datePickerbutton = findViewById(R.id.datePickerbutton);
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
                    DatetextView.setText(materialDatePicker.getHeaderText());
                }
            });


        }

    });
}
}