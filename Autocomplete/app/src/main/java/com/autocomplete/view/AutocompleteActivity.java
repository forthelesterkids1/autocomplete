package com.autocomplete.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class AutocompleteActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AutocompleteListFragment())
                    .commit();
        }
    }
}
