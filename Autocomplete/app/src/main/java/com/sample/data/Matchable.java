package com.sample.data;

import com.sample.model.AutocompleteItem;

/**
 * Created by christopherlester on 5/2/15.
 */
public interface Matchable {

    public void foundMatch(AutocompleteItem item);

    public void clearMatches();
}
