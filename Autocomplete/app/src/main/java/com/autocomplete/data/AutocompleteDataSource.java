package com.autocomplete.data;

import android.os.AsyncTask;

import com.autocomplete.model.AutocompleteItem;
import com.autocomplete.util.AutoCompleteMatcher;
import com.autocomplete.util.PerformanceTest;
import com.autocomplete.util.SelectedRangeFormatter;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AutocompleteDataSource {

    private Collection<? extends String> autocompleteDataSource;
    private Matchable matchable;
    private MatchingTask matchingTask;
    private SelectedRangeFormatter selectedRangeFormatter = new SelectedRangeFormatter();

    public AutocompleteDataSource(Matchable matchable, List<String> dataSource) {
        this.matchable = matchable;
        setupAutocompleteDataSource(dataSource);
    }

    public void setupAutocompleteDataSource(List<String> dataSource) {
        Collections.sort(dataSource);
        setAutocompleteDataSource(dataSource);
    }

    public void setAutocompleteDataSource(List<String>autocompleteDataSource) {
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

            Date operationStart = new Date();

            for (String compareString : autocompleteDataSource) {

                AutoCompleteMatcher autoCompleteMatcher = new AutoCompleteMatcher(compareString, matchTerm);
                AutocompleteItem autocompleteItem = autoCompleteMatcher.matchStrings();
                if (autocompleteItem != null && autocompleteItem.getSelectedRanges() != null) {
                    autocompleteItem.setSpannableRange(selectedRangeFormatter.formatAutoCompleteItemAsSpannableText(autocompleteItem));
                    publishProgress(autocompleteItem);
                }
            }

            Date operationEnd = new Date();
            PerformanceTest.getInstance().printDuration(operationStart, operationEnd);

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
