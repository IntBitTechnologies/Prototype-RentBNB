package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;

public class RequestProductActivity extends RentbnbBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_product);

        setupActionBar(getResources().getString(R.string.request_product), RentbnbBaseActivity.ActionBarActivityLeftAction.ACTION_CLOSE, RentbnbBaseActivity.ActionBarActivityRightAction.ACTION_NONE, RentbnbBaseActivity.ActionBarActivityRight2Action.ACTION_NONE);


    }
}