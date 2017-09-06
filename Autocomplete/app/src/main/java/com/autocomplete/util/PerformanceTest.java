package com.autocomplete.util;

import android.util.Log;

public class PerformanceTest {

    private static final String TAG = PerformanceTest.class.getName();

    public static void printDuration(long operationStart, long operationEnd){
        long diff = operationEnd - operationStart;
        Log.i(TAG, "Duration:" + (diff / 1000000) + " ms");
    }
}
