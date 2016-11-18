package com.intbit.rentbnb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.OfferListRecyclerViewAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.models.Offer;

import java.util.List;

public class MyListingActivity extends RentbnbBaseActivity {

    private RecyclerView listingRecyclerView;
    private DataManager dataManager;
    private OfferListRecyclerViewAdapter offerListRecyclerViewAdapter;
    FloatingActionButton newListingFAB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylisting);

        setupActionBar(getResources().getString(R.string.my_listing), RentbnbBaseActivity.ActionBarActivityLeftAction.ACTION_CLOSE, RentbnbBaseActivity.ActionBarActivityRightAction.ACTION_NONE, RentbnbBaseActivity.ActionBarActivityRight2Action.ACTION_NONE);

        newListingFAB = (FloatingActionButton) findViewById(R.id.fab_new_post);
        newListingFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyListingActivity.this, PostOfferActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        dataManager = new DataManager();

        listingRecyclerView = (RecyclerView) findViewById(R.id.activity_myListing_recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        listingRecyclerView.setLayoutManager(mLayoutManager);

        dataManager.getAllOffersList();
        List<Offer> offerList = dataManager.getAllOffersList();

        offerListRecyclerViewAdapter = new OfferListRecyclerViewAdapter(offerList, this);
        listingRecyclerView.setAdapter(offerListRecyclerViewAdapter);
    }
}