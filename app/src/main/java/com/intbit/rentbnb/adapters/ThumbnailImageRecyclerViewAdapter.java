package com.intbit.rentbnb.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.intbit.rentbnb.R;

/**
 * Created by sakthivel on 14/11/16.
 */
public class ThumbnailImageRecyclerViewAdapter extends RecyclerView.Adapter<ThumbnailImageRecyclerViewAdapter.ViewHolder> {

    public Context mContext;

    public ThumbnailImageRecyclerViewAdapter(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem_thumbnails_recyclerview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        /*String draw = mItems.get(i).getProductUrl();
        int id = mContext.getResources().getIdentifier(draw, "drawable", mContext.getPackageName());
        if (id == 0) {

        } else {
            viewHolder.thumbnailImageView.setImageResource(id);
        }*/
        viewHolder.thumbnailImageView.setImageResource(R.drawable.add_icon);
    }

    private void addThumbnail(int position) {
        notifyItemInserted(position + 1);
    }

    public void remove(final int position) {
        //mItems.remove(position);
        notifyItemRemoved(position);
    }

    /*public Offer getItem(int position) {
        return mItems != null ? mItems.get(position) : null;
    }*/

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnailImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.thumbnailImageView = (ImageView) itemView.findViewById(R.id.rowItem_thumbnails_ImageView);
        }
    }

}