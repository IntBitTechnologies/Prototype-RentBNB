package com.intbit.rentbnb.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.intbit.rentbnb.R;

import java.util.ArrayList;

/**
 * Created by sakthi on 11/1/17.
 */

public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private final Context activity;
    private ArrayList<String> asr;
    private ArrayList<String> icons;

    public CustomSpinnerAdapter(Context context, ArrayList<String> asr, ArrayList<String> categoriesIcon) {
        this.asr = asr;
        activity = context;
        icons = categoriesIcon;
    }

    public int getCount() {
        return asr.size();
    }

    public Object getItem(int i) {
        return asr.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    //List Item View
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_spinneritem, parent, false);

        TextView categoryName_textView = (TextView) v.findViewById(R.id.rowitem_spinneritem_name_textview);
        categoryName_textView.setText(asr.get(position));

        ImageView categoryIcon_imageView = (ImageView) v.findViewById(R.id.rowitem_spinneritem_icon_imageview);
        if (position == 0) {
            categoryIcon_imageView.setVisibility(View.GONE);
        } else {
            String draw = icons.get(position - 1);
            int id = activity.getResources().getIdentifier(draw, "drawable", activity.getPackageName());
            if (id == 0) {
                categoryIcon_imageView.setImageResource(R.drawable.noimage);
            } else {
                categoryIcon_imageView.setImageResource(id);
            }
        }
        //v.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL | Gravity.CENTER);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        v.setLayoutParams(layoutParams);

        return v;
    }

    //Title Header View
    public View getView(int i, View view, ViewGroup viewgroup) {
        TextView txt = new TextView(activity);
        txt.setGravity(Gravity.CENTER);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(16);
        //txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_detect_current_location_icon, 0);
        txt.setText(asr.get(i));
        txt.setTextColor(Color.parseColor("#000000"));
        return txt;
    }

}