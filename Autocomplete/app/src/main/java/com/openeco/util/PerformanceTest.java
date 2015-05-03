package com.openeco.util;

import android.util.Log;

import java.util.Date;

/**
 * Created by christopherlester on 5/3/15.
 */
public class PerformanceTest {

    private static final String TAG = PerformanceTest.class.getName();

    private static PerformanceTest mPerformanceTest = null;
    public static PerformanceTest getInstance(){
        if(mPerformanceTest == null){
            mPerformanceTest = new PerformanceTest();
        }
        return mPerformanceTest;
    }

    public void printDuration(Date start, Date end){
        long diff = end.getTime() - start.getTime();
        Log.i(TAG, "Duration:" + Long.toString(diff) + " ms");
    }
}
