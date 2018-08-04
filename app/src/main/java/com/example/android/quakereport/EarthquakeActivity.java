/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<EarthQuake>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
        "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private EarthQuakeAdapter mAdapter;

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {


            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);



        }
        else{
            View LoadingIndicator = findViewById(R.id.progress_bar);
            LoadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet_connection);

        }
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
//
        earthquakeListView.setEmptyView(mEmptyStateTextView);
//        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthQuakeAdapter(
                this, new ArrayList<EarthQuake>());
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EarthQuake currentEarthQuake = mAdapter.getItem(i);

                Uri earthQuakeURI = Uri.parse(currentEarthQuake.getUrl());

                Intent in = new Intent(Intent.ACTION_VIEW,earthQuakeURI);

                startActivity(in);
            }
        });


        // Create a fake list of earthquake locations.
//        ArrayList<EarthQuake>  earthquakes = QueryUtils.extractEarthquakes();
//        ArrayList<EarthQuake> earthquakes = new ArrayList<>();
//        earthquakes.add(new EarthQuake("7.2","San Francisco",2111));
        //        earthquakes.add(new EarthQuake("7.2","San Francisco","Feb 2,2016"));
//        earthquakes.add(new EarthQuake("6.1","London","July 20,2015"));
//        earthquakes.add(new EarthQuake("3.2","Tokyo","Jun 13,2016"));
//        earthquakes.add(new EarthQuake("1.2","Mexico City","Jan 12,2018"));
//        earthquakes.add(new EarthQuake("2.2","Moscow","Mar 3,2017"));
//        earthquakes.add(new EarthQuake("3.2","Rio de Janeiro","May 4,2016"));
//        earthquakes.add(new EarthQuake("4.1","Paris","Dec 31,2017"));
//
//        // Find a reference to the {@link ListView} in the layout


        // Start the AsyncTask to fetch the earthquake data



//                EarthquakeAsyncTask task = new EarthquakeAsyncTask();



  //              task.execute(USGS_REQUEST_URL);
    }

    @Override
    public Loader<List<EarthQuake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthQuake>> loader, List<EarthQuake> earthQuakes) {
        // Clear the adapter of previous earthquake data

        mEmptyStateTextView.setText(R.string.no_earthquakes);

        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthQuakes != null && !earthQuakes.isEmpty()) {
            mAdapter.addAll(earthQuakes);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<EarthQuake>> loader) {
        mAdapter.clear();
    }

    /**



     +     * {@link AsyncTask} to perform the network request on a background thread, and then



     +     * update the UI with the list of earthquakes in the response.



     +     *



     +     * AsyncTask has three generic parameters: the input type, a type used for progress updates, and



     +     * an output type. Our task will take a String URL, and return an Earthquake. We won't do



     +     * progress updates, so the second generic is just Void.



     +     *



     +     * We'll only override two of the methods of AsyncTask: doInBackground() and onPostExecute().



     +     * The doInBackground() method runs on a background thread, so it can run long-running code



     +     * (like network activity), without interfering with the responsiveness of the app.



     +     * Then onPostExecute() is passed the result of doInBackground() method, but runs on the



     +     * UI thread, so it can use the produced data to update the UI.



     +     */
//    private class EarthquakeAsyncTask extends AsyncTask<String,Void,List<EarthQuake>>{
//
//
//        @Override
//        protected List<EarthQuake> doInBackground(String... strings) {
//
//            if(strings.length<1 || strings[0]==null){
//                return null;
//            }
//            List<EarthQuake> result = QueryUtils.fetchEarthQuakeData(strings[0]);
//            return result;
//
//        }
//
//        @Override
//        protected void onPostExecute(List<EarthQuake> result){
//                if(result!=null && !result.isEmpty())
//                mAdapter.clear();
//        }
//    }
}
