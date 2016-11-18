package com.intbit.rentbnb.ui.activities;

import android.content.Context;
import android.content.Intent;
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

public class PostOfferStepFourFragment extends RentbnbBaseFragment {

    Button finishButton,postAnotherButton,shareButton;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_post_offer_step_4, container, false);

        mContext = getActivity().getApplicationContext();

        finishButton = (Button) v.findViewById(R.id.tab_post_offer_step_4_finish_button);
        postAnotherButton = (Button) v.findViewById(R.id.tab_post_offer_step_4_post_another_button);
        shareButton = (Button) v.findViewById(R.id.tab_post_offer_step_4_share_button);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        postAnotherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.setCurrentPage(1);
                ((PostOfferActivity) getActivity()).changefragmentalongStepProcess(1);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.share_subject));
                sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_description));
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        return v;
    }
}