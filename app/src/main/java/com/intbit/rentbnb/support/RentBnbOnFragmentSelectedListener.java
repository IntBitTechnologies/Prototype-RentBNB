package com.intbit.rentbnb.support;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;

/**
 * Created by Sakthivel on 30/12/16.
 */
public interface RentBnbOnFragmentSelectedListener {
    public void onFragmentSelected(FragmentManager fragmentManager, ViewPager viewPager, int position, Fragment fragment);
}
