package com.intbit.rentbnb.ui.activities.fragments;

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
import com.intbit.rentbnb.models.SubCategory;
import com.intbit.rentbnb.support.Preferences;
import com.intbit.rentbnb.ui.activities.PostOfferActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adiba on 14/11/2016.
 */
public class PostOfferStepOneFragment extends RentbnbBaseFragment {
    private Button step2;
    private Spinner categoriesSpinner, subCategoriesSpinner;
    private DataManager dataManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_post_offer_step_1, container, false);

        step2 = (Button) v.findViewById(R.id.tab_post_offer_step_1_next_button);
        categoriesSpinner = (Spinner) v.findViewById(R.id.tab_post_offer_step_1_category_spinner);
        subCategoriesSpinner = (Spinner) v.findViewById(R.id.tab_post_offer_step_1_subcategory_spinner);

        step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Do all Validations and Save Data
                Preferences.setCurrentPage(2);
                ((PostOfferActivity) getActivity()).changefragmentalongStepProcess(2);
            }
        });

        dataManager = new DataManager();

        List<Category> categoryList = dataManager.getAllCategories();
        List<SubCategory> subCategoryList = dataManager.getAllSubCategories();
        List<String> categories = new ArrayList<String>();
        List<String> subCategories = new ArrayList<String>();
        categories.add(getResources().getString(R.string.select_category));
        subCategories.add(getResources().getString(R.string.select_subcategory));
        for (int i = 0; i < categoryList.size(); i++) {
            categories.add(categoryList.get(i).getCategoryName());
        }
        for (int i = 0; i < subCategoryList.size(); i++) {
            subCategories.add(subCategoryList.get(i).getSubCategoryName());
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

        // Creating adapter for spinner
        ArrayAdapter<String> subcategoriesAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.row_item_spinneritem, subCategories) {
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
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        // Drop down layout style - list view with radio button
        subcategoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        subCategoriesSpinner.setAdapter(subcategoriesAdapter);

        return v;
    }
}