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
    private final int randomStringLength = 20;
    private final int randomStringListSize = 1000;

    public DataLoaderTask(Searchable searchable)
    {
        this.mSearchable = searchable;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... obj) {

        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        return randomStringGenerator.loadRandomAlphanumericStrings(randomStringLength, randomStringListSize);
    }

    protected void onPostExecute(ArrayList<String> searchableList) {
        mSearchable.updateSearchableStringsList(searchableList);
    }

}
