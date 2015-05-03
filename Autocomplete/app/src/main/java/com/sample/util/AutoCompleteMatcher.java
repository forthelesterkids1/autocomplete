package com.sample.util;

import com.sample.model.AutocompleteItem;
import com.sample.model.SelectedRange;

import java.util.ArrayList;

/**
 * Created by christopherlester on 4/24/15.
 */
public class AutoCompleteMatcher {

    private String mMasterCompareString;
    private String mMasterMatchString;
    private AutocompleteItem mItem;

    public AutoCompleteMatcher(String masterCompareString, String masterMatchString) {
        this.mMasterCompareString = masterCompareString;
        this.mMasterMatchString = masterMatchString;
        this.mItem = new AutocompleteItem(masterCompareString, new ArrayList<SelectedRange>());
    }

    /**
     * public method to match strings
     *
     * @return populated Autocomplete item
     */
    public AutocompleteItem matchStrings() {
        return stringsMatch(mMasterCompareString, mMasterMatchString);
    }

    private AutocompleteItem stringsMatch(String compareString, String matchTerm) {
        if(matchTerm.length() == 0 || compareString.length() == 0){
            return null;
        }

        int indexOf = compareString.toLowerCase().indexOf(matchTerm.charAt(0));
        if (indexOf != 0) {
            return null;
        }
        else {
            return matchIndexOf(mMasterCompareString, mMasterMatchString);

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
        return mItem;
    }

    /**
     * Append the selected range based on the lower and upper indexes
     *
     * @param lower
     * @param upper
     */
    private void appendSelectedRange(int lower, int upper) {
        mItem.getSelectedRanges().add(new SelectedRange(lower, upper));
    }
}
