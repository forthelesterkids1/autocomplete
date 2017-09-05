package com.autocomplete.async;

import android.content.Context;
import android.os.AsyncTask;

import com.autocomplete.data.Searchable;
import com.autocomplete.util.RandomStringGenerator;

import java.util.Collection;

public class DataLoaderTask extends AsyncTask<Void, Void, Collection<String>> {

    private Searchable searchable;
    private Context context;

    public DataLoaderTask(Searchable searchable, Context context)
    {
        this.searchable = searchable;
        this.context = context;
    }

    /**
     * Load random list of strings in the background
     * @param obj
     * @return Collection<String> of random strings
     */
    @Override
    protected Collection<String> doInBackground(Void... obj) {

        RandomStringGenerator randomStringGenerator = new RandomStringGenerator(context);
        return randomStringGenerator.loadRandomAlphanumericStrings(30, 1000);
    }

    /**
     * Post the list to the UI thread after it has been loaded
     * @param searchableList
     */
    protected void onPostExecute(Collection<String> searchableList) {
        searchable.updateSearchableStringsList(searchableList);
    }

}
