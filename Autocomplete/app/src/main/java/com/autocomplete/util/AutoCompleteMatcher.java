package com.autocomplete.util;

import com.autocomplete.model.AutocompleteItem;
import com.autocomplete.model.SelectedRange;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class AutoCompleteMatcher {

    private String masterCompareString;
    private String masterMatchString;
    private AutocompleteItem autocompleteItem;

    public AutoCompleteMatcher(String masterCompareString, String masterMatchString) {
        this.masterCompareString = masterCompareString;
        this.masterMatchString = masterMatchString;
        this.autocompleteItem = new AutocompleteItem(masterCompareString, new ArrayList<SelectedRange>());
    }

    /**
     * public method to match strings
     *
     * @return populated Autocomplete autocompleteItem
     */
    public AutocompleteItem matchStrings() {
        return stringsMatch(masterCompareString, masterMatchString);
    }

    private AutocompleteItem stringsMatch(String compareString, String matchTerm) throws InvalidParameterException {
        if(matchTerm.length() == 0 || compareString.length() == 0){
            throw new InvalidParameterException("String must have length greater that 0");
        }

        int indexOf = compareString.toLowerCase().indexOf(matchTerm.charAt(0));
        if (indexOf != 0) {
            throw new InvalidParameterException("Compare string must contain matchterm");
        }
        else {
            return matchIndexOf(masterCompareString, masterMatchString);

        }
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
