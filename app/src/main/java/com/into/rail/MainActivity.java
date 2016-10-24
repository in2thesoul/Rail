package com.into.rail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rail.into.com.R;


public class MainActivity extends Activity {
    private ArrayList<Station> stations = new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getStations();
    }

    public void onClickButtonStation(View view) {
        Intent intent = new Intent(this, StationsListActivity.class);
        Bundle data = new Bundle();
        data.putParcelableArrayList("STATIONS", stations);
        intent.putExtras(data);
        startActivity(intent);
    }

    public void onClickClosestStationButton(View view) {
        Intent intent = new Intent(this, ClosestStationActivity.class);
        Bundle data = new Bundle();
        data.putParcelableArrayList("STATIONS", stations);
        intent.putExtras(data);
        startActivity(intent);
    }

    private void getStations() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://mar.rail.co.il/v01/stations/";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, responseListener, errorListener);

        queue.add(jsonRequest);
    }

    private Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONArray jsonStations = response.getJSONObject("Stations").getJSONArray("Station");
                for (int i = 0; i < jsonStations.length(); ++i) {
                    JSONObject jsonStation = jsonStations.getJSONObject(i);
                    stations.add(Station.parseJson(jsonStation));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d(TAG, response.toString());

        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, error.toString());
        }
    };
}

