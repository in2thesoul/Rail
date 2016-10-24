package com.into.rail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by USER on 10/24/2016.
 */
public class StationsActivity extends AppCompatActivity {
    protected ArrayList<Station> stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        this.stations = bundle.getParcelableArrayList("STATIONS");
    }

}
