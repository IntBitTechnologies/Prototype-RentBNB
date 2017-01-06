package com.intbit.rentbnb.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseFragment;
import com.intbit.rentbnb.support.Preferences;
import com.intbit.rentbnb.support.RentBnbOnFragmentSelectedListener;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferStepTwoFragment extends RentbnbBaseFragment implements RentBnbOnFragmentSelectedListener {
    private Button step3NextButton;
    EditText dailyRentPriceEditText;
    private Activity mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_post_offer_step_2, container, false);

        step3NextButton = (Button) view.findViewById(R.id.tab_post_offer_step_2_next_button);
        step3NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Do all Validations and Save Data

                //Trigger code for changing page
                Preferences.setCurrentPage(3);
                ((PostOfferActivity)getActivity()).changefragmentalongStepProcess(3);
            }
        });

        return view;
    }

    @Override
    public void changeViewType() {

    }

    @Override
    public void onFragmentSelected(FragmentManager fragmentManager, ViewPager viewPager, int position, Fragment fragment) {

    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_post_offer_step_2);

        setupActionBar(getResources().getString(R.string.activity_title_post_an_offer), ActionBarActivityLeftAction.ACTION_CLOSE,ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        step3NextButton = (Button) findViewById(R.id.tab_post_offer_step_3_next_button);
        dailyRentPriceEditText = (EditText) findViewById(R.id.tab_post_offer_step_3_dailyRent_price_EditText);
        dailyRentPriceEditText.requestFocus();

        step3NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostOfferStepTwoFragment.this, PostOfferStepThreeFragment.class);
                startActivity(intent);
            }
        });
    }*/

}