package com.intbit.rentbnb.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.models.Category;
import com.intbit.rentbnb.ui.activities.ProductsListActivity;
import com.intbit.rentbnb.ui.activities.SubCategoryActivity;

import java.util.List;

/**
 * Created by sakthivel on 14/11/16.
 */
public class CategoriesListRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesListRecyclerViewAdapter.ViewHolder> {
    private List<Category> mItems;
    public Context mContext;

    public CategoriesListRecyclerViewAdapter(List<Category> data, Context context) {
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
        viewHolder.titleView.setText(mItems.get(i).getCategoryName());
        viewHolder.cateroriesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SubCategoryActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    public void remove(final int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public Category getItem(int position) {
        return mItems != null ? mItems.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public ImageView pictureView;
        public LinearLayout cateroriesLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleView = (TextView) itemView.findViewById(R.id.rowitem_recyclerview_categorylist_title_textview);
            this.pictureView = (ImageView) itemView.findViewById(R.id.rowitem_recyclerview_categorylist_defaultimageview);
            this.cateroriesLinearLayout = (LinearLayout) itemView.findViewById(R.id.rowitem_recyclerview_categorylist_linearlayout);
        }
    }
}