package com.example.coen390project;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PHHistoryActivity extends MainActivity{
    protected ListView pHListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ph_history_activity);
        pHListView = findViewById(R.id.pHListView);
        loadListView();
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


        ArrayList<String> pHListText = new ArrayList<>();

        for (int i = 0; i < pHValues.size(); i++) {
            String temp = "";
            temp += pHValues.get(i).getPH_VALUE() + ", " + pHValues.get(i).getMEASUREMENT_DATE();

            pHListText.add(temp);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pHListText);
        pHListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
    }
}
