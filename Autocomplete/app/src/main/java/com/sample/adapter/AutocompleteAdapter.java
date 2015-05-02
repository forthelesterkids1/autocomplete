package com.sample.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sample.data.Matchable;
import com.sample.model.AutocompleteItem;
import com.sample.util.SelectedRangeFormatter;
import com.sample.view.R;

import java.util.List;

/**
 * Created by christopherlester on 4/24/15.
 */
public class AutocompleteAdapter extends ArrayAdapter<AutocompleteItem> implements Matchable {

    private final Activity mActivityContext;

    public AutocompleteAdapter(Activity activityContext, int resource, List<AutocompleteItem> autocompleteList) {
        super(activityContext, resource, autocompleteList);
        this.mActivityContext = activityContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final AutocompleteItem autocompleteItem = super.getItem(position);
        final ViewHolder viewHolder = new ViewHolder();

        final LayoutInflater inflater = mActivityContext.getLayoutInflater();
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.listview_autocomplete_element, null);
        }
        convertView.setTag(viewHolder);

        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.autocompleteDisplayText = (TextView) convertView.findViewById(R.id.autocomplete_item_text);
        holder.autocompleteDisplayText.setText(autocompleteItem.getSpannableRange());

        return convertView;
    }

    /**
     * update adapter with new search results
     * @param autocompleteList
     */
    public void searchResultsChanged(List<AutocompleteItem> autocompleteList) {
        clear();
        addAll(autocompleteList);
        notifyDataSetChanged();

    }

    @Override
    public void foundMatch(AutocompleteItem item) {
        add(item);
        notifyDataSetChanged();
    }

    public void clearMatches(){
        clear();
        notifyDataSetChanged();
    }

    static class ViewHolder
    {
        public TextView autocompleteDisplayText;
    }

}
