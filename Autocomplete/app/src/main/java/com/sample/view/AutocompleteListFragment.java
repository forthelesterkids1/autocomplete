package com.sample.view;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.sample.adapter.AutocompleteAdapter;
import com.sample.async.DataLoaderTask;
import com.sample.data.Searchable;
import com.sample.model.AutocompleteItem;
import com.sample.model.SelectedRange;
import com.sample.util.AutoCompleteMatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by christopherlester on 4/24/15.
 */
public class AutocompleteListFragment extends Fragment implements Searchable {

    private final String TAG = AutocompleteListFragment.class.getName();
    private List<String> mCompareStrings = new ArrayList<>();
    private ListView mResultsList;
    private EditText mSearchableText;

    public AutocompleteListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autocomplete, container, false);
        mSearchableText = (EditText) view.findViewById(R.id.search_term);
        mResultsList = (ListView) view.findViewById(R.id.autocomplete_list);
        mResultsList.setAdapter(new AutocompleteAdapter(getActivity(), R.id.autocomplete_list, new ArrayList<AutocompleteItem>()));

        addKeyListeners();
        loadSearchableStrings();

        return view;
    }

    private void addKeyListeners() {
        mSearchableText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                matchTerms(mSearchableText.getText().toString());
            }

        });
    }

    private void loadSearchableStrings() {
        new DataLoaderTask(this).execute();
    }

    @Override
    public void updateSearchableStringsList(List<String> searchableStringsList) {
        this.mCompareStrings = searchableStringsList;
    }


    private void matchTerms(String searchTerm) {
        ArrayList<AutocompleteItem> matchList = new ArrayList<>();
        AutoCompleteMatcher autoCompleteMatcher = new AutoCompleteMatcher();

        for (String compareString : mCompareStrings) {
            AutocompleteItem autocompleteItem = autoCompleteMatcher.stringsMatch(compareString, searchTerm);
            if (autocompleteItem != null) {
                matchList.add(autocompleteItem);
            }
        }
        updateAdapter(matchList);
    }

    private void updateAdapter(ArrayList<AutocompleteItem> autocompleteItems) {
        ((AutocompleteAdapter) mResultsList.getAdapter()).searchResultsChanged(autocompleteItems);
    }
}
