package com.autocomplete.data;

import com.autocomplete.model.AutocompleteItem;

public interface Matchable {

    public void foundMatch(AutocompleteItem item);

    public void clearMatches();
}
