package com.openeco.data;

import com.openeco.model.AutocompleteItem;

/**
 * Created by christopherlester on 5/2/15.
 */
public interface Matchable {

    public void foundMatch(AutocompleteItem item);

    public void clearMatches();
}
