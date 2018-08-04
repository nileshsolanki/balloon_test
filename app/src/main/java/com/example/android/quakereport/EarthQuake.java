package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EarthQuake {

    private  double qMagnitude;

    private String qPlace;

    private long qDate;

    private String mUrl;

    public EarthQuake(double magnitude, String place, long date, String url){

        qMagnitude = magnitude;

        qPlace = place;

        qDate = date;

        mUrl = url;

    }

    public double getMagnitude(){
        return qMagnitude;
    }

    public String getPlace(){
        return qPlace;
    }

    public long getDate(){
        return qDate;
    }

    public String getUrl(){
        return mUrl;
    }
}
