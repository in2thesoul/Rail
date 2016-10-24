package com.into.rail;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import rail.into.com.R;

public class StationsListActivity extends StationsActivity {
    private static final String TAG = "StationsListActivity";
    ArrayList<String> stationNames = new ArrayList<>();
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);

        for (Station station : this.stations) {
            stationNames.add(station.getHeName());
        }
        adapter = new ArrayAdapter<>(
                this, R.layout.activity_stations, R.id.list_text_view, stationNames);
        ListView listView = (ListView) findViewById(R.id.stations_list);
        listView.setAdapter(adapter);
    }


}

