package com.openeco.async;

import android.content.Context;
import android.os.AsyncTask;

import com.openeco.data.Searchable;
import com.openeco.util.RandomStringGenerator;

import java.util.ArrayList;

/**
 * Created by christopherlester on 4/24/15.
 */
public class DataLoaderTask extends AsyncTask<Void, Void, ArrayList<String>> {

    private Searchable mSearchable;
    private Context mContext;

    public DataLoaderTask(Searchable searchable, Context context)
    {
        this.mSearchable = searchable;
        this.mContext = context;
    }

    /**
     * Load random list of strings in the background
     * @param obj
     * @return ArrayList<String> of random strings
     */
    @Override
    protected ArrayList<String> doInBackground(Void... obj) {

        RandomStringGenerator randomStringGenerator = new RandomStringGenerator(mContext);
        return randomStringGenerator.loadPackageNames();
    }

    /**
     * Post the list to the UI thread after it has been loaded
     * @param searchableList
     */
    protected void onPostExecute(ArrayList<String> searchableList) {
        mSearchable.updateSearchableStringsList(searchableList);
    }

}
