package com.autocomplete.data;

import android.os.AsyncTask;
import android.util.Log;

import com.autocomplete.model.AutocompleteItem;
import com.autocomplete.util.AutoCompleteMatcher;
import com.autocomplete.util.PerformanceTest;
import com.autocomplete.util.SelectedRangeFormatter;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;

import static java.lang.System.nanoTime;

public class AutocompleteDataSource {

    private final String TAG = getClass().getName();
    private List<String> autocompleteDataSource;
    private Matchable matchable;
    private MatchingTask matchingTask;

    public AutocompleteDataSource(Matchable matchable, List<String> dataSource) {
        this.matchable = matchable;
        setupAutocompleteDataSource(dataSource);
    }

    public void setupAutocompleteDataSource(List<String> dataSource) {
        Collections.sort(dataSource);
        setAutocompleteDataSource(dataSource);
    }

    public void setAutocompleteDataSource(List<String> autocompleteDataSource) {
        this.autocompleteDataSource = autocompleteDataSource;
    }

    public void matchTerms(String matchTerm) {
        if (matchingTask == null) {
            startMatch(matchTerm);

        } else {
            matchingTask.cancel(true);
            startMatch(matchTerm);
        }
    }

    private void startMatch(String searchTerm) {
        matchingTask = new MatchingTask();
        matchingTask.execute(searchTerm);
    }

    class MatchingTask extends AsyncTask<String, AutocompleteItem, Void> {

        @Override
        protected Void doInBackground(String... matchTerms) {

            String matchTerm = matchTerms[0];

            if (matchTerm == null) {
                return null;
            }

            long operationStart = nanoTime();
            for (String compareString : autocompleteDataSource) {

                AutoCompleteMatcher autoCompleteMatcher = new AutoCompleteMatcher(compareString, matchTerm);
                try {
                    AutocompleteItem autocompleteItem = autoCompleteMatcher.matchStrings();
                    if ((autocompleteItem != null) && (autocompleteItem.getSelectedRanges() != null)) {
                        autocompleteItem.setSpannableRange(SelectedRangeFormatter.formatAutoCompleteItemAsSpannableText(autocompleteItem));
                        publishProgress(autocompleteItem);
                    }
                } catch (InvalidParameterException ipe){
                    Log.e(TAG, "Invalid parameter used: " + ipe.getStackTrace());
                }
            }
            long operationEnd = nanoTime();
            PerformanceTest.printDuration(operationStart, operationEnd);

            return null;
        }

        @Override
        protected void onPreExecute() {
            matchable.clearMatches();
        }

        @Override
        protected void onProgressUpdate(AutocompleteItem... autocompleteItems) {
            matchable.foundMatch(autocompleteItems[0]);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }
}
