package com.intbit.rentbnb.ui.activities.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseFragment;
import com.intbit.rentbnb.models.Category;
import com.intbit.rentbnb.support.Preferences;
import com.intbit.rentbnb.ui.activities.PostOfferActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferStepTwoFragment extends RentbnbBaseFragment {
    private Button step3NextButton;
    private Activity mContext;
    private Spinner categoriesSpinner;
    private DataManager dataManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_post_offer_step_2, container, false);

        dataManager = new DataManager();

        categoriesSpinner = (Spinner) view.findViewById(R.id.tab_post_offer_step_2_category_spinner);
        List<Category> categoryList = dataManager.getAllCategories();
        List<String> categories = new ArrayList<String>();
        categories.add(getResources().getString(R.string.select_category));
        for (int i = 0; i < categoryList.size(); i++) {
            categories.add(categoryList.get(i).getCategoryName());
        }
        // Creating adapter for spinner
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.row_item_spinneritem, categories) {
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner. First item will be used for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        // Drop down layout style - list view with radio button
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        categoriesSpinner.setAdapter(categoriesAdapter);

        step3NextButton = (Button) view.findViewById(R.id.tab_post_offer_step_2_next_button);
        step3NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.setCurrentPage(3);
                ((PostOfferActivity)getActivity()).changefragmentalongStepProcess(3);
            }
        });

        return view;
    }

    @Override
    public void changeViewType() {

    }
}