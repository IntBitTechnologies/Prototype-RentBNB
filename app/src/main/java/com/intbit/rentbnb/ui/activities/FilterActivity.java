package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;

/**
 * Created by sakthi on 2/1/17.
 */

public class FilterActivity extends RentbnbBaseActivity {

    Button categoriesButton, locationButton, priceButton;
    LinearLayout categoriesLayout,categories2Layout,priceLayout;
    RelativeLayout distanceLayout;
    FloatingActionButton selectFAB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        setupActionBar(getResources().getString(R.string.filter), ActionBarActivityLeftAction.ACTION_BACK,ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_RESET);

        intializeViews();

        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllLayouts();
                showCategoriesLayout();
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllLayouts();
                showLocationLayout();
            }
        });

        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllLayouts();
                showPriceLayout();
            }
        });

        selectFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void intializeViews() {
        categoriesButton = (Button) findViewById(R.id.activity_filter_categories_button);
        locationButton = (Button) findViewById(R.id.activity_filter_location_button);
        priceButton = (Button) findViewById(R.id.activity_filter_price_button);
        categoriesLayout = (LinearLayout) findViewById(R.id.activity_filter_categories_layout);
        categories2Layout = (LinearLayout) findViewById(R.id.activity_filter_categories2_layout);
        priceLayout = (LinearLayout) findViewById(R.id.activity_filter_price_layout);
        distanceLayout = (RelativeLayout) findViewById(R.id.activity_filter_layout_distance);
        selectFAB = (FloatingActionButton) findViewById(R.id.activity_filter_select_options_fab);

        hideAllLayouts();
    }

    private void hideAllLayouts() {
        categoriesLayout.setVisibility(View.GONE);
        categories2Layout.setVisibility(View.GONE);
        priceLayout.setVisibility(View.GONE);
        distanceLayout.setVisibility(View.GONE);
    }

    private void showLocationLayout() {
        locationButton.setActivated(true);
        distanceLayout.setVisibility(View.VISIBLE);
    }

    private void showPriceLayout() {
        priceLayout.setVisibility(View.VISIBLE);
    }

    private void showCategoriesLayout() {
        categoriesLayout.setVisibility(View.VISIBLE);
        categories2Layout.setVisibility(View.VISIBLE);
    }

}