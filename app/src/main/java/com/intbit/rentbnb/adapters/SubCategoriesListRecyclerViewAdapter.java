package com.intbit.rentbnb.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.models.Category;
import com.intbit.rentbnb.models.SubCategory;
import com.intbit.rentbnb.ui.activities.ProductsListActivity;

import java.util.List;

/**
 * Created by sakthivel on 14/11/16.
 */
public class SubCategoriesListRecyclerViewAdapter extends RecyclerView.Adapter<SubCategoriesListRecyclerViewAdapter.ViewHolder> {
    private List<SubCategory> mItems;
    public Context mContext;

    public SubCategoriesListRecyclerViewAdapter(List<SubCategory> data, Context context) {
        super();

        this.mItems = data;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem_category_recyclerview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.titleView.setText(mItems.get(i).getSubCategoryName());
        viewHolder.pictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductsListActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    public void remove(final int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public SubCategory getItem(int position) {
        return mItems != null ? mItems.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public ImageView pictureView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleView = (TextView) itemView.findViewById(R.id.rowitem_recyclerview_categorylist_title_textview);
            this.pictureView = (ImageView) itemView.findViewById(R.id.rowitem_recyclerview_categorylist_defaultimageview);
        }
    }
}