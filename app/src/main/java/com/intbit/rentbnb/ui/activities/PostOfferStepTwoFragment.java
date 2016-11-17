package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseFragment;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferStepTwoFragment extends RentbnbBaseFragment {
    Button step3;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_post_offer_step_2, container, false);
        step3 = (Button) v.findViewById(R.id.tab_post_offer_step_2_next_button);

        step3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Do all Validations and Save Data

                //Trigger code for changing page
                Preferences.setCurrentPage(3);
                ((PostOfferActivity)getActivity()).changefragmentalongStepProcess(3);
            }
        });
        return v;
    }
}
