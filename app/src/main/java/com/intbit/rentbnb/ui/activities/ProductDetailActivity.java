package com.intbit.rentbnb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.DetailsListRecyclerViewAdapter;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.models.DetailsListItem;
import com.intbit.rentbnb.models.Offer;
import com.intbit.rentbnb.base.ApplicationConstants;
import com.intbit.rentbnb.support.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends RentbnbBaseActivity {

    private Offer offer;
    private ImageView productImageView;
    private List<DetailsListItem> data;
    DetailsListRecyclerViewAdapter detailsListRecyclerViewAdapter;
    RecyclerView detailsRecyclerView;
    Button rentNowButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);

        setupActionBar(getResources().getString(R.string.productDetails), ActionBarActivityLeftAction.ACTION_CLOSE, ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        productImageView = (ImageView) findViewById(R.id.activity_productdetail_productImageView);
        detailsRecyclerView = (RecyclerView) findViewById(R.id.activity_productDetail_details_recyclerView);
        rentNowButton = (Button) findViewById(R.id.activity_productdetail_rentButton);

        // Use a linear layout manager
        detailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // The row divider
        detailsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        rentNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, RentNowActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey(ApplicationConstants.OFFER_MODEL)) {
                offer = (Offer) bundle.getParcelable(ApplicationConstants.OFFER_MODEL);
                if (offer != null) {
                    updateUI(offer);
                }
            }
        }
    }

    private void updateUI(Offer offer) {
        String draw = offer.getProductUrl();
        int id = getResources().getIdentifier(draw, "drawable", getPackageName());
        if (id == 0) {
            productImageView.setImageResource(R.drawable.noimage);
        } else {
            productImageView.setImageResource(id);
        }
        populateData(offer);
    }

    private void populateData(Offer offer) {
        data = new ArrayList<DetailsListItem>();
        data.add(new DetailsListItem(getString(R.string.product_name), offer.getProductName(), getResources().getDrawable(R.drawable.ic_local_offer_black_48px)));
        data.add(new DetailsListItem(getString(R.string.product_price), "$ "+offer.getProductPrice(), getResources().getDrawable(R.drawable.ic_product_price)));
        data.add(new DetailsListItem(getString(R.string.posted_on), offer.getPostedDate(), getResources().getDrawable(R.drawable.calendar_icon)));
        data.add(new DetailsListItem(getString(R.string.product_description), offer.getProductDescription(), getResources().getDrawable(R.drawable.ic_description_black_48px)));

        detailsListRecyclerViewAdapter = new DetailsListRecyclerViewAdapter(data);
        detailsRecyclerView.setAdapter(detailsListRecyclerViewAdapter);
    }
}