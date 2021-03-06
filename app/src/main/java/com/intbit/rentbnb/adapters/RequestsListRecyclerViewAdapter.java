package com.intbit.rentbnb.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.models.Request;

import java.util.List;

/**
 * Created by sakthivel on 14/11/16.
 */
public class RequestsListRecyclerViewAdapter extends RecyclerView.Adapter<RequestsListRecyclerViewAdapter.ViewHolder> {
    private List<Request> mItems;
    public Context mContext;

    public RequestsListRecyclerViewAdapter(List<Request> data, Context context) {
        super();

        this.mItems = data;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem_requestlist_recyclerview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.titleTextView.setText(mItems.get(i).getRequestedBy());
        viewHolder.priceTextView.setText(mItems.get(i).getRequestedProduct());
        viewHolder.dateTextView.setText(mItems.get(i).getRequestedDate());
    }

    public void remove(final int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public Request getItem(int position) {
        return mItems != null ? mItems.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, titleTextView, dateTextView, priceTextView, categoryTextView, descriptionTextView, fromToDateTextView;
        public ImageView profileImageView, calendarImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nameTextView = (TextView) itemView.findViewById(R.id.rowitem_requestList_name_text_view);
            this.titleTextView = (TextView) itemView.findViewById(R.id.rowitem_requestList_title_text_view);
            this.priceTextView = (TextView) itemView.findViewById(R.id.rowitem_requestList_amount_textView);
            this.dateTextView = (TextView) itemView.findViewById(R.id.rowitem_requestList_posted_date_TextView);
            this.categoryTextView = (TextView) itemView.findViewById(R.id.rowitem_requestList_category_textView);
            this.descriptionTextView = (TextView) itemView.findViewById(R.id.rowitem_requestList_product_description_TextView);
            this.fromToDateTextView = (TextView) itemView.findViewById(R.id.rowitem_requestList_from_to_date_TextView);
            this.profileImageView = (ImageView) itemView.findViewById(R.id.rowitem_requestList_profile_image_view);
            this.calendarImageView = (ImageView) itemView.findViewById(R.id.posted_calendar_imageView);
        }
    }
}