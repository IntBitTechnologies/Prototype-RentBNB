package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.models.Offer;
import com.intbit.rentbnb.support.ApplicationConstants;

public class ProductDetailActivity extends RentbnbBaseActivity {

    private Offer offer;
    private ImageView productImageView;
    private TextView descriptionTextView, productNameTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);

        productImageView = (ImageView) findViewById(R.id.activity_productdetail_productImageView);
        descriptionTextView = (TextView) findViewById(R.id.activity_productdetail_descriptionTextView);
        productNameTextView = (TextView) findViewById(R.id.activity_productdetail_productNameTextView);

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
        productNameTextView.setText(offer.getProductName());
        descriptionTextView.setText(offer.getProductDescription());
    }

    @Override
    public void getData() {

    }

}
