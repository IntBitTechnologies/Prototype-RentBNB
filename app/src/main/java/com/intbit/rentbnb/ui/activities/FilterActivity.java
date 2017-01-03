package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;

/**
 * Created by sakthi on 2/1/17.
 */

public class FilterActivity extends RentbnbBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        setupActionBar(getResources().getString(R.string.filter), ActionBarActivityLeftAction.ACTION_CLOSE,ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);
    }

}