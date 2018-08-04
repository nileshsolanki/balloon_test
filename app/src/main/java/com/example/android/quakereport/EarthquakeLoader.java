package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.net.URL;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String mUrl;

    public EarthquakeLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    @Override
    public Object loadInBackground() {
        if(mUrl == null){
            return null;
        }

        List<EarthQuake> earthquakes =  QueryUtils.fetchEarthQuakeData(mUrl);
        return earthquakes;
    }
}
