package com.intbit.rentbnb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;

/**
 * Created by Adiba on 13/01/2017.
 */

public class NewRequestActivity extends RentbnbBaseActivity {

    Button rentButton,sellButton,rentToOwnButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);

        setupActionBar(getResources().getString(R.string.new_request), ActionBarActivityLeftAction.ACTION_CLOSE, ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        rentButton = (Button) findViewById(R.id.new_request_rent_button);
        rentToOwnButton = (Button) findViewById(R.id.new_request_rent_to_own_button);
        sellButton = (Button) findViewById(R.id.new_request_sell_button);

        rentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });

        rentToOwnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });

    }

    private void goToNextActivity() {
        Intent intent = new Intent(NewRequestActivity.this, PostOfferActivity.class);
        startActivity(intent);
        finish();
    }

}
