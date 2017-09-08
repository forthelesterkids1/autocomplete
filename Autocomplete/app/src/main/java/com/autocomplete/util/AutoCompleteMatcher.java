package com.autocomplete.util;

import com.autocomplete.model.AutocompleteItem;
import com.autocomplete.model.SelectedRange;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class AutoCompleteMatcher {

    private String comparisonString;
    private String matchSequence;
    private AutocompleteItem autocompleteItem;

    public AutoCompleteMatcher(String comparisonString, String matchSequence) {
        this.comparisonString = comparisonString;
        this.matchSequence = matchSequence;
        this.autocompleteItem = new AutocompleteItem(comparisonString, new ArrayList<SelectedRange>());
    }

    /**
     * public method to match strings
     *
     * @return populated Autocomplete autocompleteItem
     */
    public AutocompleteItem matchStrings() throws InvalidParameterException {
        return matchStrings(comparisonString, matchSequence);
    }

    private AutocompleteItem matchStrings(String compareString, String matchTerm) throws InvalidParameterException {
        if(matchTerm.length() == 0 || compareString.length() == 0){
            throw new InvalidParameterException("String must have length greater that 0");
        }

        return compareString.toLowerCase().indexOf(matchTerm.charAt(0)) == 0 ?
                matchIndexOf(comparisonString, matchSequence) : null;
    }

    private AutocompleteItem matchIndexOf(String compareString, String matchTerm) {
        int currentIndexOf = 0;
        for (int i = 0; i < matchTerm.length(); i++) {
            String subString = String.valueOf(matchTerm.charAt(i));
            int indexOf = compareString.toLowerCase().indexOf(subString.toLowerCase(), currentIndexOf);
            if (indexOf != -1) {
                int movingIndex = indexOf + 1;
                appendSelectedRange(indexOf, movingIndex);
                currentIndexOf = movingIndex;
            } else {
                return null;
            }

        }
        return autocompleteItem;
    }

    /**
     * Append the selected range based on the lower and upper indexes
     *
     * @param lower
     * @param upper
     */
    private void appendSelectedRange(int lower, int upper) {
        autocompleteItem.getSelectedRanges().add(new SelectedRange(lower, upper));
    }
}
