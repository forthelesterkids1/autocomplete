package com.autocomplete.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.autocomplete.adapter.AutocompleteAdapter;
import com.autocomplete.async.DataLoaderTask;
import com.autocomplete.data.AutocompleteDataSource;
import com.autocomplete.data.Matchable;
import com.autocomplete.data.Searchable;
import com.autocomplete.model.AutocompleteItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopherlester on 4/24/15.
 */
public class AutocompleteListFragment extends Fragment implements Searchable {

    private ListView mResultsList;
    private EditText mMatchableText;
    private AutocompleteDataSource mAutocompleteDataSource;

    public AutocompleteListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autocomplete, container, false);
        mMatchableText = (EditText)view.findViewById(R.id.search_term);
        mMatchableText.setEnabled(false);
        mResultsList = (ListView)view.findViewById(R.id.autocomplete_list);
        mResultsList.setAdapter(new AutocompleteAdapter(getActivity(), R.id.autocomplete_list, new ArrayList<AutocompleteItem>()));

        addKeyListeners();
        loadSearchableStrings();
        return view;
    }

    /**
     * Listen for keypressed on soft keyboard then search string
     */
    private void addKeyListeners() {
        mMatchableText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                matchTerms(mMatchableText.getText().toString());
            }

        });
    }

    /**
     * Load strings in the background
     */
    private void loadSearchableStrings() {
        new DataLoaderTask(this, getActivity()).execute();
    }

    /**
     * Set search strings to be used for autocomplete
     * @param matchableStringsList
     */
    @Override
    public void updateSearchableStringsList(List<String> matchableStringsList) {
        mAutocompleteDataSource = new AutocompleteDataSource((Matchable)mResultsList.getAdapter(),
                matchableStringsList);
        mMatchableText.setEnabled(true);
    }

    /**
     *
     * @param matchTerm term to match against arraylist
     */
    private void matchTerms(String matchTerm) {
        mAutocompleteDataSource.matchTerms(matchTerm);
    }

}
