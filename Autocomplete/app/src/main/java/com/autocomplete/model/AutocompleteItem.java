package com.autocomplete.model;

import android.text.Spannable;

import java.util.ArrayList;

public class AutocompleteItem {

    private String autocompleteTerm;
    private ArrayList<SelectedRange> selectedRanges;
    private Spannable spannableRange;

    public AutocompleteItem(String autocompleteTerm, ArrayList<SelectedRange> selectedRanges){
        this.autocompleteTerm = autocompleteTerm;
        this.selectedRanges = selectedRanges;
    }
    public String getAutocompleteTerm() {
        return autocompleteTerm;
    }

    public ArrayList<SelectedRange> getSelectedRanges() {
        return selectedRanges;
    }

    public void setSpannableRange(Spannable spannableRange){
        this.spannableRange = spannableRange;
    }

    public Spannable getSpannableRange(){
        return spannableRange;
    }

}
