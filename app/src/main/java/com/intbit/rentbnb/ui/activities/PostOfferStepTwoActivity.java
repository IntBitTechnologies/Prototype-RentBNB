package com.intbit.rentbnb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferStepTwoActivity extends RentbnbBaseActivity {
    private Button step3NextButton;
    EditText dailyRentPriceEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_post_offer_step_2);

        setupActionBar(getResources().getString(R.string.activity_title_post_an_offer), ActionBarActivityLeftAction.ACTION_CLOSE,ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        step3NextButton = (Button) findViewById(R.id.tab_post_offer_step_3_next_button);
        dailyRentPriceEditText = (EditText) findViewById(R.id.tab_post_offer_step_3_dailyRent_price_EditText);
        dailyRentPriceEditText.requestFocus();

        step3NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostOfferStepTwoActivity.this, PostOfferStepThreeActivity.class);
                startActivity(intent);
            }
        });
    }

}