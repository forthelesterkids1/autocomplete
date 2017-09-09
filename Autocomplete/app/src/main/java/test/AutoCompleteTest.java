package test;

import com.autocomplete.data.AutocompleteDataSource;
import com.autocomplete.model.AutocompleteItem;
import com.autocomplete.util.AutoCompleteMatcher;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AutoCompleteTest {

    @Test
    public void testAutoCompleteReturnsACorrectValue(){

    }

    @Test
    public void testAutoCompleteReturnsNull(){
        AutocompleteItem autocompleteItem = new AutoCompleteMatcher().matchStrings("abcd", "accc");
        assertNull(autocompleteItem);

    }

    @Test
    public void testAutoCompleteReturnsValue(){
        AutocompleteItem autocompleteItem = new AutoCompleteMatcher().matchStrings("abc", "aaaaa");
        assertNotNull(autocompleteItem);

    }

    public AutocompleteDataSource getAutoCompleteDataSource(){
        String abcd = "abcd";
        List<String> list = new ArrayList<>();
        list.add(abcd);
        AutocompleteDataSource autocompleteDataSource = new AutocompleteDataSource(null, list);
        return autocompleteDataSource;
    }
}
