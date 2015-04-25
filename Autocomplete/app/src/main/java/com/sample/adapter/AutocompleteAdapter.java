package com.sample.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sample.model.AutocompleteItem;
import com.sample.util.SelectedRangeFormatter;
import com.sample.view.R;

import java.util.List;

/**
 * Created by christopherlester on 4/24/15.
 */
public class AutocompleteAdapter extends ArrayAdapter<AutocompleteItem>{

    private final Activity mActivityContext;
    private List<AutocompleteItem> mAutocompleteList;
    private SelectedRangeFormatter mSelectedRangeFormatter;

    public AutocompleteAdapter(Activity activityContext, int resource, List<AutocompleteItem> autocompleteList) {
        super(activityContext, resource, autocompleteList);
        this.mAutocompleteList = autocompleteList;
        this.mActivityContext = activityContext;
        this.mSelectedRangeFormatter = new SelectedRangeFormatter();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final AutocompleteItem autocompleteItem = mAutocompleteList.get(position);

        final LayoutInflater inflater = mActivityContext.getLayoutInflater();
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.listview_autocomplete_element, null);
        }

        final ViewHolder viewHolder = new ViewHolder();
        convertView.setTag(viewHolder);

        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.autocompleteDisplayText.setText(mSelectedRangeFormatter.formatAutoCompleteItemAsSpannableText(autocompleteItem));

        return convertView;
    }

    public void searchResultsChanged(List<AutocompleteItem> autocompleteList) {
        this.mAutocompleteList.clear();
        this.mAutocompleteList = autocompleteList;
        notifyDataSetChanged();

    }
    static class ViewHolder
    {
        public TextView autocompleteDisplayText;
    }

}
