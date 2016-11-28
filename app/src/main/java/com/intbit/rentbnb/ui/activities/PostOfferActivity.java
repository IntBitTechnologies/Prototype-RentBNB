package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.support.Preferences;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferActivity extends RentbnbBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_offer);
        setupActionBar(getResources().getString(R.string.activity_title_post_an_offer), ActionBarActivityLeftAction.ACTION_CLOSE,ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        Preferences.clearCurrentpage();
    }

}