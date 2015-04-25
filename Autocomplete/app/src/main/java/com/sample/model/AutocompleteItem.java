package com.sample.model;

import android.util.Range;

import java.util.ArrayList;

/**
 * Created by christopherlester on 4/24/15.
 */
public class AutocompleteItem {

    private String autocompleteTerm;
    private ArrayList<SelectedRange> selectedRanges;

    public String getAutocompleteTerm() {
        return autocompleteTerm;
    }

    public void setAutocompleteTerm(String autocompleteTerm) {
        this.autocompleteTerm = autocompleteTerm;
    }

    public ArrayList<SelectedRange> getSelectedRanges() {
        return selectedRanges;
    }

    public void setSelectedRanges(ArrayList<SelectedRange> selectedRanges) {
        this.selectedRanges = selectedRanges;
    }

}
