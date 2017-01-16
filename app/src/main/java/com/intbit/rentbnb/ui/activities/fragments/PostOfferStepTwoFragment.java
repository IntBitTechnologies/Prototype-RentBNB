package com.intbit.rentbnb.ui.activities.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.CustomSpinnerAdapter;
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
        categoriesSpinner.setDropDownWidth(getScreenWidthInPixels());
        List<Category> categoryList = dataManager.getAllCategories();
        ArrayList<String> categories = new ArrayList<String>();
        ArrayList<String> categoriesIcons = new ArrayList<>();
        categories.add(getResources().getString(R.string.select_category));
        for (int i = 0; i < categoryList.size(); i++) {
            categories.add(categoryList.get(i).getCategoryName());
            categoriesIcons.add(categoryList.get(i).getImageUrl());
        }

        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(mContext, categories, categoriesIcons);
        categoriesSpinner.setAdapter(customSpinnerAdapter);

        step3NextButton = (Button) view.findViewById(R.id.tab_post_offer_step_2_next_button);
        step3NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.setCurrentPage(3);
                ((PostOfferActivity) getActivity()).changefragmentalongStepProcess(3);
            }
        });

        return view;
    }

    private int getScreenWidthInPixels() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        return width;
    }

    @Override
    public void changeViewType() {
    }

}