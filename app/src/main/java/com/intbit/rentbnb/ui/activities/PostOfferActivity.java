package com.intbit.rentbnb.ui.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.support.StepsView;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferActivity extends RentbnbBaseActivity {
    StepsView mStepsView;
    String[] labels = {"1", "2", "3", "4"};
    int stepProcess = 1;
    boolean isFirstTime = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_offer);
        setupActionBar(getResources().getString(R.string.activity_title_post_an_offer), ActionBarActivityLeftAction.ACTION_CLOSE,ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);
        mStepsView = (StepsView) findViewById(R.id.stepsView);

        Preferences.clearCurrentpage();
        moveToNext();
    }

    @Override
    public void getData() {

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
        FragmentManager fm = getFragmentManager();
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