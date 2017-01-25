package com.intbit.rentbnb.ui.activities.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseFragment;
import com.intbit.rentbnb.support.Preferences;
import com.intbit.rentbnb.ui.activities.PostOfferActivity;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferStepThreeFragment extends RentbnbBaseFragment {
    Button step4;
    Activity mActivity;
    Context mContext;
    RelativeLayout weeklyLayout, monthlyLayout, addRatesLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_post_offer_step_3, container, false);

        step4 = (Button) v.findViewById(R.id.tab_post_offer_step_3_next_button);
        addRatesLayout = (RelativeLayout) v.findViewById(R.id.weekly_monthly_rate_relative_layout);
        monthlyLayout = (RelativeLayout) v.findViewById(R.id.monthly_rate_relative_layout);
        weeklyLayout = (RelativeLayout) v.findViewById(R.id.weekly_rate_relative_layout);

        mContext = getActivity().getApplicationContext();

        step4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Trigger code for changing page
                Preferences.setCurrentPage(4);
                ((PostOfferActivity)getActivity()).changefragmentalongStepProcess(4);
            }
        });

        addRatesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weeklyLayout.isShown()) {
                    hideRatesLayout();
                } else {
                    showRatesLayout();
                }
            }
        });

        return v;
    }

    private void showRatesLayout() {
        monthlyLayout.setVisibility(View.VISIBLE);
        weeklyLayout.setVisibility(View.VISIBLE);
    }

    private void hideRatesLayout() {
        monthlyLayout.setVisibility(View.GONE);
        weeklyLayout.setVisibility(View.GONE);
    }

    @Override
    public void changeViewType() {

    }

}
