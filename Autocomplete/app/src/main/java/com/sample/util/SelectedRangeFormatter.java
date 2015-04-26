package com.sample.util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;

import com.sample.model.AutocompleteItem;
import com.sample.model.SelectedRange;

public class SelectedRangeFormatter {

    /**
     * Format highlights on strings
     * @param autocompleteItem
     * @return formatted spannable string
     */
    public Spannable formatAutoCompleteItemAsSpannableText(AutocompleteItem autocompleteItem)
    {
        return formatAutoCompleteItemAsSpannableText(autocompleteItem, Color.YELLOW);
    }

    /**
     * Format highlights on strings
     * @param autocompleteItem
     * @param color
     * @return formatted spannable string
     */
    public Spannable formatAutoCompleteItemAsSpannableText(AutocompleteItem autocompleteItem, int color)
    {
        String stringToFormat = autocompleteItem.getAutocompleteTerm();
        Spannable selectedSpan = Spannable.Factory.getInstance().newSpannable(stringToFormat);

        if(autocompleteItem.getSelectedRanges() != null) {

            for (SelectedRange selectedRange : autocompleteItem.getSelectedRanges()) {
                selectedSpan.setSpan(new BackgroundColorSpan(color),
                        selectedRange.getLowerIndex(),
                        selectedRange.getUpperIndex(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return selectedSpan;
    }

}
