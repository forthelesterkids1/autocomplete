package com.sample.data;

import android.database.Cursor;
import android.os.AsyncTask;

import com.sample.model.AutocompleteItem;
import com.sample.util.AutoCompleteMatcher;
import com.sample.util.SelectedRangeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by christopherlester on 5/2/15.
 */
public class AutocompleteDataSource {

    private List<String> mAutocompleteDataSource;
    private Matchable mMatchable;
    private MatchingTask mMatchingTask;
    private SelectedRangeFormatter mSelectedRangeFormatter = new SelectedRangeFormatter();

    public AutocompleteDataSource(Matchable matchable) {
        this.mMatchable = matchable;
    }

    public AutocompleteDataSource(Matchable matchable, List<String> dataSource) {
        this(matchable);
        setAutocompleteDataSource(dataSource);
    }

    public AutocompleteDataSource(Matchable matchable, Map<String, String> dataSource) {
        this(matchable);
        setAutocompleteDataSource(dataSource);
    }

    public AutocompleteDataSource(Matchable matchable, Set<String> dataSource) {
        this(matchable);
        setAutocompleteDataSource(dataSource);
    }

    public AutocompleteDataSource(Matchable matchable, Cursor dataSource) {
        this(matchable);
        setAutocompleteDataSource(dataSource);
    }

    public void setupAutocompleteDataSource(List<String> dataSource) {
        Collections.sort(dataSource);
        setAutocompleteDataSource(dataSource);
    }

    public void setAutocompleteDataSource(Cursor dataSource) {
        List<String> autocompleteDataSource = new ArrayList<String>();
        while(dataSource.moveToNext()){
            mAutocompleteDataSource.add((dataSource.getString(0)));
        }
        setupAutocompleteDataSource(autocompleteDataSource);
    }

    public void setAutocompleteDataSource(Set<String> dataSource) {
        List<String> autocompleteDataSource = new ArrayList<String>();
        for(String dataItem:dataSource){
            autocompleteDataSource.add(dataItem);
        }

        setupAutocompleteDataSource(autocompleteDataSource);
    }

    public void setAutocompleteDataSource(Map<String, String> dataSource) {
        List<String> autocompleteDataSource = new ArrayList<String>();
        Iterator it = dataSource.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            autocompleteDataSource.add((String)pair.getValue());
        }

        setupAutocompleteDataSource(autocompleteDataSource);
    }

    public void setAutocompleteDataSource(List<String> autocompleteDataSource) {
        this.mAutocompleteDataSource = autocompleteDataSource;
    }

    public void matchTerms(String searchTerm){
        if(mMatchingTask == null){
            mMatchingTask = new MatchingTask();
            mMatchingTask.execute(searchTerm);
        }
        else {
            mMatchingTask.cancel(true);
            mMatchingTask = new MatchingTask();
            mMatchingTask.execute(searchTerm);
        }

    }

    class MatchingTask extends AsyncTask<String, AutocompleteItem, Void> {

        private final String TAG = MatchingTask.class.getName();

        @Override
        protected Void doInBackground(String... searchTerms) {

            String searchTerm = searchTerms[0];
            for (String compareString : mAutocompleteDataSource) {

                AutoCompleteMatcher autoCompleteMatcher = new AutoCompleteMatcher(compareString, searchTerm);
                AutocompleteItem autocompleteItem = autoCompleteMatcher.matchStrings();
                if (autocompleteItem != null && autocompleteItem.getSelectedRanges() != null) {
                    autocompleteItem.setSpannableRange(mSelectedRangeFormatter.formatAutoCompleteItemAsSpannableText(autocompleteItem));
                    publishProgress(autocompleteItem);
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            mMatchable.clearMatches();
        }

        @Override
        protected void onProgressUpdate(AutocompleteItem... autocompleteItems) {
            mMatchable.foundMatch(autocompleteItems[0]);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }
}
