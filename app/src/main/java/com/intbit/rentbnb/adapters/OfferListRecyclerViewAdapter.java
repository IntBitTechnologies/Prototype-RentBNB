package com.intbit.rentbnb.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.models.Offer;

import java.util.List;

/**
 * Created by sakthivel on 14/11/16.
 */
public class OfferListRecyclerViewAdapter extends RecyclerView.Adapter<OfferListRecyclerViewAdapter.ViewHolder> {
    private List<Offer> mItems;
    public Context mContext;

    public OfferListRecyclerViewAdapter(List<Offer> data, Context context) {
        super();

        this.mItems = data;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem_offerslist_recyclerview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.titleView.setText(mItems.get(i).getProductName());
        viewHolder.priceView.setText("$ " + mItems.get(i).getProductPrice());
        viewHolder.dateView.setText("Posted on " + mItems.get(i).getPostedDate());

        String draw = mItems.get(i).getProductUrl();
        int id = mContext.getResources().getIdentifier(draw, "drawable", mContext.getPackageName());
        if (id == 0) {
            viewHolder.thumbnailImageView.setImageResource(R.drawable.noimage);
        } else {
            viewHolder.thumbnailImageView.setImageResource(id);
        }
    }

    public void remove(final int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public Offer getItem(int position) {
        return mItems != null ? mItems.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView, dateView, priceView;
        public ImageView thumbnailImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleView = (TextView) itemView.findViewById(R.id.rowitem_productlist_name_TextView);
            this.priceView = (TextView) itemView.findViewById(R.id.rowitem_productlist_price_TextView);
            this.dateView = (TextView) itemView.findViewById(R.id.rowitem_productList_date_textView);
            this.thumbnailImageView = (ImageView) itemView.findViewById(R.id.rowItem_productList_thumbnail_ImageView);
        }
    }
}