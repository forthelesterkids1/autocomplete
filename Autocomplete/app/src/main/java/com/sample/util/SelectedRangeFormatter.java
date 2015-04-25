package com.sample.util;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.sample.model.AutocompleteItem;
import com.sample.model.SelectedRange;

public class SelectedRangeFormatter {

    public Spannable formatAutoCompleteItemAsSpannableText(AutocompleteItem autocompleteItem)
    {
        return formatAutoCompleteItemAsSpannableText(autocompleteItem, Color.BLUE);
    }

    public Spannable formatAutoCompleteItemAsSpannableText(AutocompleteItem autocompleteItem, int color)
    {
        String stringToFormat = autocompleteItem.getAutocompleteTerm();
        Spannable selectedSpan = Spannable.Factory.getInstance().newSpannable(stringToFormat);

        if(autocompleteItem.getSelectedRanges() != null) {

            for (SelectedRange selectedRange : autocompleteItem.getSelectedRanges()) {
                selectedSpan.setSpan(new ForegroundColorSpan(color),
                        selectedRange.getLowerIndex(),
                        selectedRange.getUpperIndex(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return selectedSpan;
    }

}
