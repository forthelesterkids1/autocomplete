package com.sample.util;

import com.sample.model.AutocompleteItem;
import com.sample.model.SelectedRange;

import java.util.ArrayList;

/**
 * Created by christopherlester on 4/24/15.
 */
public class AutoCompleteMatcher {

    private String mMasterCompareString;
    private String mMasterSearchString;
    private AutocompleteItem mItem;

    public AutoCompleteMatcher(String masterCompareString, String masterSearchString) {
        this.mMasterCompareString = masterCompareString;
        this.mMasterSearchString = masterSearchString;
        this.mItem = new AutocompleteItem(masterCompareString, new ArrayList<SelectedRange>());
    }

    /**
     * Algortihm to compare strings and match ranges
     * @param compareString
     * @param searchTerm
     * @return AutocompleteItem with selectedRanges
     */
    public AutocompleteItem stringsMatch(String compareString, String searchTerm) {
        for (int i = 0; i < mMasterSearchString.length(); i++){
            String ns = mMasterSearchString.substring(i, i + 1);
            if (ns.equalsIgnoreCase(compareString.substring(0, 1))) {
                appendSelectedRange(0, 1);
                continue;
            }
            else {
                int compareIndex = mMasterCompareString.length() - compareString.length();
                String newCompare = new String(mMasterCompareString.substring(compareIndex + 1));
                if(!appendNextRange(findNextMatchingChar(newCompare, ns))){
                    return null;
                }
            }
        }
        return mItem;

    }

    /**
     * Find the next matching string in the sequence
     * @param compareString
     * @param searchTerm
     * @return matched String
     */
    private String findNextMatchingChar(String compareString, String searchTerm) {
        for (int i = 0; i < compareString.length(); i++) {
            if (compareString.substring(i, i + 1).equalsIgnoreCase(searchTerm)) {
                String newCompareString = new String(compareString.substring(i));
                return newCompareString;
            }
        }
        return null;
    }

    /**
     * Appends the range using the upper and lower bound for the selected text
     * @param currentString
     * @return boolean if the range is appended or not
     */
    private boolean appendNextRange(String currentString){
        if(currentString == null || mMasterCompareString == null) {
            return false;
        }
        else{
            int lower = mMasterCompareString.length() - currentString.length();
            int upper = lower + 1;
            appendSelectedRange(lower, upper);
            return true;
        }
    }

    /**
     * Append the selected range based on the lower and upper indexes
     * @param lower
     * @param upper
     */
    private void appendSelectedRange(int lower, int upper) {
        if (mItem.getSelectedRanges() == null) {
            mItem.setSelectedRanges(new ArrayList<SelectedRange>());
        }
        mItem.getSelectedRanges().add(new SelectedRange(lower, upper));
    }
}
