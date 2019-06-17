package com.example.datnguyen.fitness.Others;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final int TIME_LIMIT_EASY = 10000;
    public static final int TIME_LIMIT_MEDIUM = 50000;
    public static final int TIME_LIMIT_HARD = 90000;
    public static final String API_ID = "c0e28b1d2b515d47102ee637e8991c16";
    public static final String API_YOUTUBE = "AIzaSyAx-KuBQz2DpXwSDQAbMpOtWri8ZGXqudI";
    public static Location current_location = null;

    //ForeCast
    public static final String RAIN_V1 = "10d";
    public static final String RAIN_V2 = "10n";


    public static String convertUnixToDate(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd EEE MM yyyy");
        String formatted = sdf.format(date);
        return  formatted;
    }

    public static String convertUnixToHour(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm ");
        String formatted = sdf.format(date);
        return  formatted;

    }
}
