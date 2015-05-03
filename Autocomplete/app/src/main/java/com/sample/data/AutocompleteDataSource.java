package com.sample.data;

import android.os.AsyncTask;

import com.sample.model.AutocompleteItem;
import com.sample.util.AutoCompleteMatcher;
import com.sample.util.PerformanceTest;
import com.sample.util.SelectedRangeFormatter;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by christopherlester on 5/2/15.
 */
public class AutocompleteDataSource {

    private List<String> mAutocompleteDataSource;
    private Matchable mMatchable;
    private MatchingTask mMatchingTask;
    private SelectedRangeFormatter mSelectedRangeFormatter = new SelectedRangeFormatter();

    public AutocompleteDataSource(Matchable matchable, List<String> dataSource) {
        this.mMatchable = matchable;
        setupAutocompleteDataSource(dataSource);
    }

    public void setupAutocompleteDataSource(List<String> dataSource) {
        Collections.sort(dataSource);
        setAutocompleteDataSource(dataSource);
    }

    public void setAutocompleteDataSource(List<String> autocompleteDataSource) {
        this.mAutocompleteDataSource = autocompleteDataSource;
    }

    public void matchTerms(String matchTerm) {
        if (mMatchingTask == null) {
            startMatch(matchTerm);

        } else {
            mMatchingTask.cancel(true);
            startMatch(matchTerm);
        }
    }

    private void startMatch(String searchTerm) {
        mMatchingTask = new MatchingTask();
        mMatchingTask.execute(searchTerm);
    }

    class MatchingTask extends AsyncTask<String, AutocompleteItem, Void> {

        @Override
        protected Void doInBackground(String... matchTerms) {

            String matchTerm = matchTerms[0];

            if (matchTerm == null) {
                return null;
            }

            Date operationStart = new Date();

            for (String compareString : mAutocompleteDataSource) {

                AutoCompleteMatcher autoCompleteMatcher = new AutoCompleteMatcher(compareString, matchTerm);
                AutocompleteItem autocompleteItem = autoCompleteMatcher.matchStrings();
                if (autocompleteItem != null && autocompleteItem.getSelectedRanges() != null) {
                    autocompleteItem.setSpannableRange(mSelectedRangeFormatter.formatAutoCompleteItemAsSpannableText(autocompleteItem));
                    publishProgress(autocompleteItem);
                }
            }

            Date operationEnd = new Date();
            PerformanceTest.getInstance().printDuration(operationStart, operationEnd);

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
