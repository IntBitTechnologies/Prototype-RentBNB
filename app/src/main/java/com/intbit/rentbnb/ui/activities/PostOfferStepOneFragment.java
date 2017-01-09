package com.intbit.rentbnb.ui.activities;

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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseFragment;
import com.intbit.rentbnb.models.Category;
import com.intbit.rentbnb.support.Preferences;
import com.intbit.rentbnb.support.RentBnbOnFragmentSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adiba on 14/11/2016.
 */
public class PostOfferStepOneFragment extends RentbnbBaseFragment  implements RentBnbOnFragmentSelectedListener {
    private Button step2;
    private Spinner categoriesSpinner;
    private DataManager dataManager;
    private Activity mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_post_offer_step_1, container, false);

        step2 = (Button) view.findViewById(R.id.tab_post_offer_step_1_next_button);
        categoriesSpinner = (Spinner) view.findViewById(R.id.tab_post_offer_step_2_category_spinner);

        step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.setCurrentPage(2);
                ((PostOfferActivity) getActivity()).changefragmentalongStepProcess(2);
            }
        });

        dataManager = new DataManager();

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

        return view;
    }

    @Override
    public void onFragmentSelected(FragmentManager fragmentManager, ViewPager viewPager, int position, Fragment fragment) {
        if (fragment instanceof PostOfferStepOneFragment) {
            //getData(RentBnbEnums.Offers_View_Grid.toInt());
        }
    }

    @Override
    public void changeViewType() {

    }


   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_post_offer_step_1);

        setupActionBar(getResources().getString(R.string.activity_title_post_an_offer), ActionBarActivityLeftAction.ACTION_CLOSE,ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        step2 = (Button) findViewById(R.id.tab_post_offer_step_1_next_button);
        categoriesSpinner = (Spinner) findViewById(R.id.tab_post_offer_step_1_category_spinner);

        step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostOfferStepOneFragment.this, PostOfferStepTwoFragment.class);
                startActivity(intent);
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
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, R.layout.row_item_spinneritem, categories) {
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

    }*/
}