package com.autocomplete.model;

public class SelectedRange {

    private int lowerIndex;
    private int upperIndex;

    public SelectedRange(int lowerIndex, int upperIndex)
    {
        this.lowerIndex = lowerIndex;
        this.upperIndex = upperIndex;
    }

    public int getLowerIndex() {
        return lowerIndex;
    }

    public int getUpperIndex() {
        return upperIndex;
    }

}
