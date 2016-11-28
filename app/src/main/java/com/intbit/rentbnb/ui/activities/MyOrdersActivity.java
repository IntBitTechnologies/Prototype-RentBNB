package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.OfferListRecyclerViewAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.models.Offer;

import java.util.List;

public class MyOrdersActivity extends RentbnbBaseActivity {

    private RecyclerView ordersRecyclerView;
    private OfferListRecyclerViewAdapter ordersListRecyclerViewAdapter;
    private DataManager dataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        setupActionBar(getResources().getString(R.string.my_orders), RentbnbBaseActivity.ActionBarActivityLeftAction.ACTION_CLOSE, RentbnbBaseActivity.ActionBarActivityRightAction.ACTION_NONE, RentbnbBaseActivity.ActionBarActivityRight2Action.ACTION_NONE);

        ordersRecyclerView = (RecyclerView) findViewById(R.id.activity_myOrders_recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        ordersRecyclerView.setLayoutManager(mLayoutManager);

        dataManager = new DataManager();

        dataManager.getAllOffersList();
        List<Offer> offerList = dataManager.getAllOffersList();

        ordersListRecyclerViewAdapter = new OfferListRecyclerViewAdapter(offerList, this);
        ordersRecyclerView.setAdapter(ordersListRecyclerViewAdapter);
    }
}