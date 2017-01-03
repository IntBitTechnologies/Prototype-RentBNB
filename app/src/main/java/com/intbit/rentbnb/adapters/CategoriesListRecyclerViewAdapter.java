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
import com.intbit.rentbnb.base.RentBnbEnums;
import com.intbit.rentbnb.models.Category;

import java.util.List;

/**
 * Created by sakthivel on 14/11/16.
 */
public class CategoriesListRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesListRecyclerViewAdapter.ViewHolder> {
    private List<Category> mItems;
    public Context mContext;
    public int viewType;

    public CategoriesListRecyclerViewAdapter(List<Category> data, Context context, int viewType) {
        super();

        this.mItems = data;
        this.mContext = context;
        this.viewType = viewType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        if (viewType == RentBnbEnums.Offers_View_Grid.toInt()) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem_category_gridview_recyclerview, viewGroup, false);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem_category_listview_recyclerview, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        //viewHolder.titleView.setText(mItems.get(i).getCategoryName());
        viewHolder.cateroriesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(mContext, SubCategoryActivity.class);
                mContext.startActivity(intent);*/
            }
        });

        String draw = mItems.get(i).getImageUrl();
        int id = mContext.getResources().getIdentifier(draw, "drawable", mContext.getPackageName());
        if (id == 0) {
            viewHolder.pictureView.setImageResource(R.drawable.noimage);
        } else {
            viewHolder.pictureView.setImageResource(id);
        }
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
            //this.titleView = (TextView) itemView.findViewById(R.id.rowitem_recyclerview_categorylist_title_textview);
            this.pictureView = (ImageView) itemView.findViewById(R.id.rowitem_recyclerview_categorylist_defaultimageview);
            this.cateroriesLinearLayout = (LinearLayout) itemView.findViewById(R.id.rowitem_recyclerview_categorylist_linearlayout);
        }
    }
}