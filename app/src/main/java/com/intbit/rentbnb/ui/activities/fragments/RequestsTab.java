package com.intbit.rentbnb.ui.activities.fragments;

/**
 * Created by sakthi on 8/12/15.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.CategoriesListRecyclerViewAdapter;
import com.intbit.rentbnb.adapters.OfferListRecyclerViewAdapter;
import com.intbit.rentbnb.adapters.RequestsListRecyclerViewAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentBnbEnums;
import com.intbit.rentbnb.base.RentBnbEnvironment;
import com.intbit.rentbnb.base.RentbnbBaseFragment;
import com.intbit.rentbnb.models.Category;
import com.intbit.rentbnb.models.Offer;
import com.intbit.rentbnb.models.Request;
import com.intbit.rentbnb.support.RentBnbOnFragmentSelectedListener;

import java.util.List;

public class RequestsTab extends RentbnbBaseFragment implements RentBnbOnFragmentSelectedListener {
    private RecyclerView mRecyclerView;
    private Activity mContext;
    private ImageView noDataImageView;
    private DataManager dataManager;
    private RequestsListRecyclerViewAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_requests, container, false);

        initializeViews(view);

        return view;
    }

    private void initializeViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.activity_Requests_recyclerView);
        noDataImageView = (ImageView) view.findViewById(R.id.global_noDataFoundImageView);

        mRecyclerView.setHasFixedSize(true);

        dataManager = new DataManager();

        setHasOptionsMenu(true);
    }

    @Override
    public void onFragmentSelected(FragmentManager fragmentManager, ViewPager viewPager, int position, Fragment fragment) {
        if (fragment instanceof RequestsTab) {
            getData();
        }
    }

    public void getData() {
        List<Request> requestList = dataManager.getAllOpenRequests();

        mAdapter = new RequestsListRecyclerViewAdapter(requestList, mContext);
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void changeViewType() {

    }
}