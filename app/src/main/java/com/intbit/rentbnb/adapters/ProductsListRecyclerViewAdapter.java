package com.intbit.rentbnb.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.models.Product;

import java.util.List;

/**
 * Created by sakthivel on 14/11/16.
 */
public class ProductsListRecyclerViewAdapter extends RecyclerView.Adapter<ProductsListRecyclerViewAdapter.ViewHolder> {
    private List<Product> mItems;
    public Context mContext;

    public ProductsListRecyclerViewAdapter(List<Product> data, Context context) {
        super();

        this.mItems = data;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem_productslist_recyclerview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.titleView.setText(mItems.get(i).getProductName());
        viewHolder.priceView.setText(mItems.get(i).getProductPrice());
    }

    public void remove(final int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public Product getItem(int position) {
        return mItems != null ? mItems.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView, priceView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleView = (TextView) itemView.findViewById(R.id.rowitem_productlist_name_TextView);
            this.priceView = (TextView) itemView.findViewById(R.id.rowitem_productlist_price_TextView);
        }
    }
}