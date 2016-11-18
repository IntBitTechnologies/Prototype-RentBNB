package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.SubCategoriesListRecyclerViewAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.models.SubCategory;

import java.util.List;

public class SubCategoryActivity extends RentbnbBaseActivity {

    private RecyclerView subCategoriesRecyclerView;
    private DataManager dataManager;
    private SubCategoriesListRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        setupActionBar(getResources().getString(R.string.subcategories), ActionBarActivityLeftAction.ACTION_CLOSE, ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        subCategoriesRecyclerView = (RecyclerView) findViewById(R.id.activity_subcategories_recyclerView);

        GridLayoutManager mLayoutManager = new GridLayoutManager(SubCategoryActivity.this, 2);
        subCategoriesRecyclerView.setLayoutManager(mLayoutManager);


        dataManager = new DataManager();

        List<SubCategory> subCategoryList = dataManager.getAllSubCategories();

        mAdapter = new SubCategoriesListRecyclerViewAdapter(subCategoryList, this);
        subCategoriesRecyclerView.setAdapter(mAdapter);
    }
}