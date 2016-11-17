package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseFragment;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferStepFourFragment extends RentbnbBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_post_offer_step_4, container, false);
        return v;
    }
}
