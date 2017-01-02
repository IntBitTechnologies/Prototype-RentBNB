package com.intbit.rentbnb.ui.activities.fragments;

/**
 * Created by sakthi on 8/12/15.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.CategoriesListRecyclerViewAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseFragment;
import com.intbit.rentbnb.models.Category;
import com.intbit.rentbnb.support.RentBnbOnFragmentSelectedListener;

import java.util.List;

public class RentTab extends RentbnbBaseFragment implements RentBnbOnFragmentSelectedListener {   //SearchView.OnQueryTextListener, SearchView.OnCloseListener, DocAppOnFragmentSelectedListener {
    private RecyclerView mRecyclerView;
    private Activity mContext;
    private ImageView noDataImageView;
    private DataManager dataManager;
    private CategoriesListRecyclerViewAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_rent, container, false);

        initializeViews(view);

        return view;
    }

    private void initializeViews(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rentTab_productsList_recyclerView);
        noDataImageView = (ImageView) view.findViewById(R.id.global_noDataFoundImageView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));


        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dataManager = new DataManager();

        setHasOptionsMenu(true);
    }

    @Override
    public void onFragmentSelected(FragmentManager fragmentManager, ViewPager viewPager, int position, Fragment fragment) {
        if (fragment instanceof RentTab) {
            getData();
        }
    }

    public void getData() {
        List<Category> allCategoriesList = dataManager.getAllCategories();

        mAdapter = new CategoriesListRecyclerViewAdapter(allCategoriesList, mContext);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setQueryHint(getResources().getString(R.string.search_hint_patient));

        ((EditText) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(getResources().getColor(R.color.white));
        ((EditText) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.primary_light));

        ImageView searchCloseIcon = (ImageView) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchCloseIcon.setColorFilter(getResources().getColor(R.color.white));
        setupSearchView();
    }
*/
    /*private void setupSearchView() {
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);
    }
*/
  /*  @Override
    public boolean onClose() {
        //Toast.makeText(mContext, "Close", Toast.LENGTH_SHORT).show();
        populatePatientList(offerList);
        return false;
    }
*/
    /*@Override
    public boolean onQueryTextSubmit(String query) {
        //Toast.makeText(mContext, "Text Submit", Toast.LENGTH_SHORT).show();
        searchList(query);
        return false;
    }
*/
    /*private void searchList(String query) {
        if (!StringUtil.isEmpty(query)) {
            query = query.toLowerCase();
            newPatientList = new ArrayList<PatientDetails>();
            for (int i = 0; i < offerList.size(); i++) {
                PatientDetails patient = offerList.get(i);
                if (patient.getFullName().toLowerCase().contains(query)) {
                    newPatientList.add(patient);
                }
            }
            populatePatientList(newPatientList);
        } else {
            populatePatientList(offerList);
        }
    }

    @Override
    public boolean onQueryTextChange(String query) {
        //Toast.makeText(mContext, "Text Change", Toast.LENGTH_SHORT).show();
        searchList(query);
        return false;
    }*/
}