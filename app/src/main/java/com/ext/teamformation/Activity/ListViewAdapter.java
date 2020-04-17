package com.ext.teamformation.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ext.teamformation.Modal.PeopleModal;
import com.ext.teamformation.Modal.ProjectModal;
import com.ext.teamformation.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<PeopleModal> list = null;
    private ArrayList<PeopleModal> arraylist;

    public ListViewAdapter(Context context, List<PeopleModal> list) {
        mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }


    public class ViewHolder {
        TextView name;
        TextView lang;
        TextView exp;
        TextView rate;
        RatingBar ratebar;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public PeopleModal getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.info, null);

            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.t3);
            holder.lang = (TextView) view.findViewById(R.id.t4);
            holder.exp = (TextView) view.findViewById(R.id.t5);
            holder.rate = (TextView) view.findViewById(R.id.t6);
            holder.ratebar = (RatingBar) view.findViewById(R.id.rate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(list.get(position).getPeopleName());
        holder.lang.setText(list.get(position).getPeopleLangauge());
        holder.exp.setText(list.get(position).getPeopleExperience());
        holder.rate.setText(list.get(position).getPeopleRatings());

        holder.ratebar.setRating( Float.parseFloat( list.get( position ).getPeopleRatings() ) );




        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(arraylist);
        } else {
            for (PeopleModal wp : arraylist) {
                if (wp.getPeopleName().toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.getPeopleLangauge().toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.getPeopleExperience().toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.getPeopleRatings().toLowerCase(Locale.getDefault()).contains(charText) ) {
                    list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}