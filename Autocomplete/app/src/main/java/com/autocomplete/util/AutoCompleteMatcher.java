package com.autocomplete.util;

import com.autocomplete.model.AutocompleteItem;
import com.autocomplete.model.SelectedRange;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class AutoCompleteMatcher {
    
    public AutocompleteItem matchStrings(String compareString, String matchTerm) throws InvalidParameterException {
        if(matchTerm.length() == 0 || compareString.length() == 0){
            throw new InvalidParameterException("String must have length greater that 0");
        }

        return compareString.toLowerCase().indexOf(matchTerm.charAt(0)) == 0 ?
                matchIndexOf(compareString, matchTerm) : null;
    }

    private AutocompleteItem matchIndexOf(String compareString, String matchTerm) {
        AutocompleteItem item = new AutocompleteItem(compareString, new ArrayList<SelectedRange>());
        int currentIndexOf = 0;
        for (int i = 0; i < matchTerm.length(); i++) {
            String subString = String.valueOf(matchTerm.charAt(i));
            int indexOf = compareString.toLowerCase().indexOf(subString.toLowerCase(), currentIndexOf);
            if (indexOf != -1) {
                int movingIndex = indexOf + 1;
                item.getSelectedRanges().add(new SelectedRange(indexOf, movingIndex));
                currentIndexOf = movingIndex;
            } else {
                return null;
            }

        }
        return item;
    }
}
