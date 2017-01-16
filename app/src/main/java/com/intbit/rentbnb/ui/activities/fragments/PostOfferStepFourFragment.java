package com.intbit.rentbnb.ui.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseFragment;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferStepFourFragment extends RentbnbBaseFragment {
    Context mContext;
    private Button postOfferButton;
    RelativeLayout distanceLayout,distanceFeesLayout;
    CheckBox deliveryCheckbox;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_post_offer_step_4, container, false);

        initializeViews(v);

        postOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Offer Posted Successfully", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        deliveryCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    showDistanceLayout();
                } else {
                    hideDistanceLayout();
                }
            }
        });

        return v;
    }

    private void hideDistanceLayout() {
        distanceLayout.setVisibility(View.GONE);
        distanceFeesLayout.setVisibility(View.GONE);
    }

    private void showDistanceLayout() {
        distanceLayout.setVisibility(View.VISIBLE);
        distanceFeesLayout.setVisibility(View.VISIBLE);
    }

    private void initializeViews(View v) {
        mContext = getActivity().getApplicationContext();
        postOfferButton = (Button) v.findViewById(R.id.tab_post_offer_step_4_next_button);
        distanceLayout = (RelativeLayout) v.findViewById(R.id.maximum_distance_relative_layout);
        distanceFeesLayout = (RelativeLayout) v.findViewById(R.id.distance_fees_relative_layout);
        deliveryCheckbox = (CheckBox) v.findViewById(R.id.tab_post_offer_step4_delivery_checkbox);
    }

    @Override
    public void changeViewType() {
    }
}