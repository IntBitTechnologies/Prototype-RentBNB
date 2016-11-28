package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.RequestsListRecyclerViewAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.models.Request;

import java.util.List;

public class OpenRequestsActivity extends RentbnbBaseActivity {

    private RecyclerView openRequestsRecyclerView;
    private RequestsListRecyclerViewAdapter requestsListRecyclerViewAdapter;
    private DataManager dataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_requests);

        setupActionBar(getResources().getString(R.string.open_requests), RentbnbBaseActivity.ActionBarActivityLeftAction.ACTION_CLOSE, RentbnbBaseActivity.ActionBarActivityRightAction.ACTION_NONE, RentbnbBaseActivity.ActionBarActivityRight2Action.ACTION_NONE);

        openRequestsRecyclerView = (RecyclerView) findViewById(R.id.activity_openReqests_recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        openRequestsRecyclerView.setLayoutManager(mLayoutManager);

        dataManager = new DataManager();

        dataManager.getAllOffersList();
        List<Request> requestList = dataManager.getAllOpenRequests();

        requestsListRecyclerViewAdapter = new RequestsListRecyclerViewAdapter(requestList, this);
        openRequestsRecyclerView.setAdapter(requestsListRecyclerViewAdapter);
    }
}