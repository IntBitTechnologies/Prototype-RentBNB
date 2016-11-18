package com.intbit.rentbnb.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.OfferListRecyclerViewAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.models.Offer;
import com.intbit.rentbnb.base.ApplicationConstants;
import com.intbit.rentbnb.support.RecyclerItemClickListener;

import java.util.List;

public class OffersListActivity extends RentbnbBaseActivity {

    private RecyclerView productsRecyclerView;
    private OfferListRecyclerViewAdapter offerListRecyclerViewAdapter;
    private DataManager dataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_list);

        setupActionBar(getResources().getString(R.string.products), ActionBarActivityLeftAction.ACTION_CLOSE, ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        productsRecyclerView = (RecyclerView) findViewById(R.id.activity_products_list_recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        productsRecyclerView.setLayoutManager(mLayoutManager);

        dataManager = new DataManager();

        List<Offer> offerList = dataManager.getAllOffersList();

        offerListRecyclerViewAdapter = new OfferListRecyclerViewAdapter(offerList, this);
        productsRecyclerView.setAdapter(offerListRecyclerViewAdapter);
        productsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(OffersListActivity.this, ProductDetailActivity.class);
                        Offer offer = offerListRecyclerViewAdapter.getItem(position);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(ApplicationConstants.OFFER_MODEL, offer);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                })
        );
    }


}