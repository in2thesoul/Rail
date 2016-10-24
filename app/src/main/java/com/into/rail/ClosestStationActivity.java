package com.into.rail;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;


import rail.into.com.R;

public class ClosestStationActivity extends StationsActivity implements LocationListener {
    private LocationManager locationManager;
    TextView closestStationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closest_station);

        closestStationTextView = (TextView) findViewById(R.id.closest_station_text_view);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Station closestStation = stations.get(0);
        float shortestDistance = location.distanceTo(closestStation.getLocation());

        for (Station station : this.stations) {
            float stationDistance;

            if (station.getLocation().getLatitude() == 0 &&
                    station.getLocation().getLongitude() == 0) {
                continue;
            }

            stationDistance = location.distanceTo(station.getLocation());

            if (stationDistance < shortestDistance) {
                shortestDistance = stationDistance;
                closestStation = station;
            }
        }

        closestStationTextView.setText(closestStation.getHeName());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
