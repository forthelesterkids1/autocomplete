package com.sample.model;

import java.util.ArrayList;

/**
 * Created by christopherlester on 4/24/15.
 */
public class AutocompleteItem {

    private String mAutocompleteTerm;
    private ArrayList<SelectedRange> mSelectedRanges;

    public AutocompleteItem(String autocompleteTerm, ArrayList<SelectedRange> selectedRanges){
        this.mAutocompleteTerm = autocompleteTerm;
        this.mSelectedRanges = selectedRanges;
    }
    public String getAutocompleteTerm() {
        return mAutocompleteTerm;
    }

    public void setAutocompleteTerm(String mAutocompleteTerm) {
        this.mAutocompleteTerm = mAutocompleteTerm;
    }

    public ArrayList<SelectedRange> getSelectedRanges() {
        return mSelectedRanges;
    }

    public void setSelectedRanges(ArrayList<SelectedRange> mSelectedRanges) {
        this.mSelectedRanges = mSelectedRanges;
    }

}
