package com.sample.util;

import com.sample.model.AutocompleteItem;
import com.sample.model.SelectedRange;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by christopherlester on 4/24/15.
 */
public class AutoCompleteMatcher {

    private int mSearchTermCurrentIndex = 0;
    private int mSearchTermCurrentShiftIndex = 0;
    private int mStringToCompareCurrentIndex = 0;
    private int mStringToCompareShiftIndex = 0;
    private ArrayList<SelectedRange> mSelectedRanges;

    public AutocompleteItem stringsMatch(String compareString, String searchTerm){
        AutocompleteItem item = new AutocompleteItem();
//        for(int i = 0; i < searchTerm.length(); i++){
//            String sub1 = getSubString(searchTerm, mSearchTermCurrentIndex, i);
//            String sub2 = getSubString(compareString, mSearchTermCurrentIndex, i);
//
//            if(subStringsAreEqual(sub1, sub2)){
//                appendSelectedRange(mStringToCompareCurrentIndex, i);
//            }
//        }
//        item.setSelectedRanges(mSelectedRanges);
        item.setAutocompleteTerm(compareString);
        return item;
    }

    private void appendSelectedRange(int lower, int upper){
        if(mSelectedRanges == null){
            mSelectedRanges = new ArrayList<>();
        }
        mSelectedRanges.add(new SelectedRange(lower, upper));
    }
    private boolean subStringsAreEqual(String sub1, String sub2){
        return sub1.equalsIgnoreCase(sub2);
    }
    private String getSubString(String substring, int start, int end){
        return substring.substring(start, end);
    }

    private void shiftIndex(int currentIndex, int forwardShift){
        currentIndex += forwardShift;
    }

}
