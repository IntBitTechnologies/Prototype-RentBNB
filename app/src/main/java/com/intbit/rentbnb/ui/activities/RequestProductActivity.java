package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;

public class RequestProductActivity extends RentbnbBaseActivity {
    private Button sellButton, rentButton, bothButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_product);

        setupActionBar(getResources().getString(R.string.request), RentbnbBaseActivity.ActionBarActivityLeftAction.ACTION_CLOSE, RentbnbBaseActivity.ActionBarActivityRightAction.ACTION_NONE, RentbnbBaseActivity.ActionBarActivityRight2Action.ACTION_NONE);

        initializeViews();

        sellButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleButtonSelection(sellButton);
                return true;
            }
        });

        rentButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleButtonSelection(rentButton);
                return true;
            }
        });

        bothButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleButtonSelection(bothButton);
                return true;
            }
        });
    }

    private void toggleButtonSelection(Button button) {
        clearAllButtonStates();

        if (button == sellButton) {
            sellButton.setPressed(true);
        } else if (button == rentButton) {
            rentButton.setPressed(true);
        } else if (button == bothButton) {
            bothButton.setPressed(true);
        }
    }

    private void clearAllButtonStates() {
        sellButton.setPressed(false);
        rentButton.setPressed(false);
        bothButton.setPressed(false);
    }

    private void initializeViews() {
        sellButton = (Button) findViewById(R.id.activity_request_product_sell_button);
        rentButton = (Button) findViewById(R.id.activity_request_product_rent_button);
        bothButton = (Button) findViewById(R.id.activity_request_product_both_button);
    }


}