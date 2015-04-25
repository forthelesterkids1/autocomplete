package com.sample.model;

/**
 * Created by christopherlester on 4/24/15.
 */
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

    public void setLowerIndex(int lowerIndex) {
        this.lowerIndex = lowerIndex;
    }

    public int getUpperIndex() {
        return upperIndex;
    }

    public void setUpperIndex(int upperIndex) {
        this.upperIndex = upperIndex;
    }
}
