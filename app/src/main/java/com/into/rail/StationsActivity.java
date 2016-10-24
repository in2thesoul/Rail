package com.into.rail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import rail.into.com.R;

public class StationsActivity extends AppCompatActivity {
    private static final String TAG = "StationsActivity";
    ArrayList<Station> stations;
    ArrayList<String> stationNames = new ArrayList<>();
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);

        Bundle bundle = getIntent().getExtras();
        stations = bundle.getParcelableArrayList("STATIONS");

        for (Station station : stations) {
            stationNames.add(station.getHeName());
        }
        adapter = new ArrayAdapter<>(
                this, R.layout.activity_stations, R.id.list_text_view, stationNames);
        ListView listView = (ListView) findViewById(R.id.stations_list);
        listView.setAdapter(adapter);
    }


}

