package com.example.memvoca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CardListAdapter extends BaseAdapter {

    public ArrayList<CardListItem> boxListItems = new ArrayList<CardListItem>();

    @Override
    public int getCount() {
        return boxListItems.size();
    }

    @Override
    public Object getItem(int i) {
        return boxListItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_card_list, viewGroup, false);
        }

        TextView numTextView = (TextView) view.findViewById(R.id.tv_num);
        TextView wordTextView = (TextView) view.findViewById(R.id.tv_word);
        TextView meanTextView = (TextView) view.findViewById(R.id.tv_mean);

        CardListItem listViewItem = (CardListItem) getItem(position);

        numTextView.setText(listViewItem.getNumber().toString());
        wordTextView.setText(listViewItem.getWord());
        meanTextView.setText(listViewItem.getMean());

        return view;
    }

    public void addItem(CardListItem item){
        boxListItems.add(item);
    }
}