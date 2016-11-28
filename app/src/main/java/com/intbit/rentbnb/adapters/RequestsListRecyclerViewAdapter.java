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
        viewHolder.titleView.setText(mItems.get(i).getRequestedBy());
        viewHolder.priceView.setText(mItems.get(i).getRequestedProduct());
        viewHolder.dateView.setText("Posted on : " + mItems.get(i).getRequestedDate());
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
        public TextView titleView, dateView, priceView;
        //public ImageView thumbnailImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleView = (TextView) itemView.findViewById(R.id.rowitem_requestList_name_TextView);
            this.priceView = (TextView) itemView.findViewById(R.id.rowitem_requestList_price_TextView);
            this.dateView = (TextView) itemView.findViewById(R.id.rowitem_requestList_date_textView);
            //this.thumbnailImageView = (ImageView) itemView.findViewById(R.id.rowItem_requestList_thumbnail_ImageView);
        }
    }
}