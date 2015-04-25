package com.sample.util;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.StyleSpan;

import com.sample.model.AutocompleteItem;
import com.sample.model.SelectedRange;

public class SelectedRangeFormatter {

    public Spannable formatAutoCompleteItemAsSpannableText(AutocompleteItem autocompleteItem)
    {
        return formatAutoCompleteItemAsSpannableText(autocompleteItem, Typeface.BOLD_ITALIC);
    }

    public Spannable formatAutoCompleteItemAsSpannableText(AutocompleteItem autocompleteItem, int typeFaceStyle)
    {
        String stringToFormat = autocompleteItem.getAutocompleteTerm();
        Spannable selectedSpan = Spannable.Factory.getInstance().newSpannable(stringToFormat);

        for(SelectedRange selectedRange: autocompleteItem.getSelectedRanges()) {
            selectedSpan.setSpan(new StyleSpan(typeFaceStyle),
                    selectedRange.getLowerIndex(),
                    selectedRange.getUpperIndex(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return selectedSpan;
    }

}
