package com.intbit.rentbnb.ui.activities.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseFragment;
import com.intbit.rentbnb.support.Preferences;
import com.intbit.rentbnb.support.RentBnbOnFragmentSelectedListener;
import com.intbit.rentbnb.ui.activities.PostOfferActivity;

/**
 * Created by Adiba on 25/01/2017.
 */

public class PostOfferStepCoverageFragment extends RentbnbBaseFragment implements RentBnbOnFragmentSelectedListener {
    private Activity mContext;
    private TextInputLayout insuranceLayout;
    private Button step_coverage_next_button;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_post_offer_step_coverage, container, false);

        insuranceLayout = (TextInputLayout) view.findViewById(R.id.tab_post_offer_step_coverage_insurance_layout);
        step_coverage_next_button = (Button) view.findViewById(R.id.tab_post_offer_step_coverage_next_button);

        return view;
    }

    @Override
    public void onFragmentSelected(FragmentManager fragmentManager, ViewPager viewPager, int position, Fragment fragment) {
        if (fragment instanceof PostOfferStepOneFragment) {

        }
    }

    @Override
    public void changeViewType() {

    }
}
