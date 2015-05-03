package com.sample.async;

import android.os.AsyncTask;

import com.sample.data.Searchable;
import com.sample.util.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by christopherlester on 4/24/15.
 */
public class DataLoaderTask extends AsyncTask<Void, Void, ArrayList<String>> {

    private Searchable mSearchable;

    public DataLoaderTask(Searchable searchable)
    {
        this.mSearchable = searchable;
    }

    /**
     * Load random list of strings in the background
     * @param obj
     * @return ArrayList<String> of random strings
     */
    @Override
    protected ArrayList<String> doInBackground(Void... obj) {

        final int randomStringLength = 30;
        final int randomStringListSize = 10000;

        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        return randomStringGenerator.loadRandomAlphanumericStrings(randomStringLength, randomStringListSize);
    }

    /**
     * Post the list to the UI thread after it has been loaded
     * @param searchableList
     */
    protected void onPostExecute(ArrayList<String> searchableList) {
        mSearchable.updateSearchableStringsList(searchableList);
    }

}
