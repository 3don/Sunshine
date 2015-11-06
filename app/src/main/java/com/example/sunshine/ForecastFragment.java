package com.example.sunshine;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);
        ArrayList <String> list = new ArrayList<>();
        list.add("Today-Sunny-88/63");
        list.add("Tomorrow-Foggy-70/46");
        list.add("Weds-Cloudy-72/63");
        list.add("Thurs-Rainy-64/51");
        list.add("Fri-Foggy-70/46");
        list.add("Sat-Sunny-76/68");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.list_item_forecast, R.id.list_item_forecast_textview, list);
        ListView listView = (ListView) rootview.findViewById(R.id.listView_forecast);
        listView.setAdapter(adapter);



        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String forecastJsonStr = null;

        try{
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream==null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line=reader.readLine())!=null){
                buffer.append(line + "\n");


            if (buffer.length()==0) {
                return null;
            }
            forecastJsonStr=buffer.toString();
        }}
        catch (IOException e) {
            Log.e("MainActivityFragment", "Error", e);
            return null;
        }
        finally {
            if (urlConnection!=null) {
                urlConnection.disconnect();
            }
            if (reader!=null){
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivityFragment", "Error closing stream", e);
                }
            }
        }

        return rootview;
    }

    public class FetchWeatherTask extends AsyncTask <Void, Void , Void>
    {
        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        @Override
        protected Void doInBackground(Void... params) {
            return null;

        }
    }
}
