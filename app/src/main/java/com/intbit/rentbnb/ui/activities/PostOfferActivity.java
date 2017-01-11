package com.intbit.rentbnb.ui.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.support.Preferences;
import com.intbit.rentbnb.support.StepsView;
import com.intbit.rentbnb.ui.activities.fragments.PostOfferStepFourFragment;
import com.intbit.rentbnb.ui.activities.fragments.PostOfferStepOneFragment;
import com.intbit.rentbnb.ui.activities.fragments.PostOfferStepThreeFragment;
import com.intbit.rentbnb.ui.activities.fragments.PostOfferStepTwoFragment;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferActivity extends RentbnbBaseActivity {

    StepsView mStepsView;
    String[] labels = {"Photo", "Details", "Price", "Finish"};
    int stepProcess = 1;
    boolean isFirstTime = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_offer);
        setupActionBar(getResources().getString(R.string.activity_title_new_request), ActionBarActivityLeftAction.ACTION_CLOSE,ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        mStepsView = (StepsView) findViewById(R.id.stepsView);

        Preferences.clearCurrentpage();
        moveToNext();
    }

    public void moveToNext() {
        if (Preferences.getCurrentPage() != 0) {
            stepProcess = Preferences.getCurrentPage() + 1;
        } else {
            stepProcess = 1;
        }

        if (isFirstTime) {
            isFirstTime = false;
            changefragmentalongStepProcess(stepProcess);
        }
    }

    public void changefragmentalongStepProcess(int pos) {
        //FragmentManager fm = getFragmentManager();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        mStepsView.setLabels(labels)
                .setBarColorIndicator(getResources().getColor(R.color.primary_light))
                .setProgressColorIndicator(getResources().getColor(R.color.primary))
                .setLabelColorIndicator(getResources().getColor(R.color.primary_dark))
                .drawView();
        switch (pos) {
            case 1:
                mStepsView.setCompletedPosition(0);
                PostOfferStepOneFragment stepOne = new PostOfferStepOneFragment();
                ft.replace(R.id.stepFragment, stepOne);
                ft.commit();
                break;
            case 2:
                mStepsView.setCompletedPosition(1);
                PostOfferStepTwoFragment stepTwo = new PostOfferStepTwoFragment();
                ft.replace(R.id.stepFragment, stepTwo);
                ft.commit();
                break;
            case 3:
                mStepsView.setCompletedPosition(2);
                PostOfferStepThreeFragment stepThree = new PostOfferStepThreeFragment();
                ft.replace(R.id.stepFragment, stepThree);
                ft.commit();
                break;
            case 4:
                mStepsView.setCompletedPosition(3);
                PostOfferStepFourFragment stepFour = new PostOfferStepFourFragment();
                ft.replace(R.id.stepFragment, stepFour);
                ft.commit();
                break;
        }
    }


}