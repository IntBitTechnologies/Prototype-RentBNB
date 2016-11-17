package com.intbit.rentbnb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.models.DetailsListItem;

import java.util.List;

/**
 * Created by sakthivel on 17/11/15.
 */
public class DetailsListRecyclerViewAdapter extends RecyclerView.Adapter<DetailsListRecyclerViewAdapter.ViewHolder> {
    List<DetailsListItem> mItems;

    public DetailsListRecyclerViewAdapter(List<DetailsListItem> mItems) {
        super();
        this.mItems = mItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_details_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.titleView.setText(mItems.get(position).getTitle());
        viewHolder.valueView.setText(mItems.get(position).getValue());
        viewHolder.imageView.setImageDrawable(mItems.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleView;
        public TextView valueView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.customDetailsRow_title_textview);
            valueView = (TextView) itemView.findViewById(R.id.customDetailsRow_value_textview);
            imageView = (ImageView) itemView.findViewById(R.id.customDetailsRow_image_imageView);
        }
    }

}
