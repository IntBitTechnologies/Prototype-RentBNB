package com.intbit.rentbnb.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.intbit.rentbnb.base.ApplicationConstants;
import com.intbit.rentbnb.support.RentBnbOnFragmentSelectedListener;

import java.util.List;

/**
 * Created by Sakthivel on 30/12/16.
 */
public class DashboardViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<DashboardViewPagerItem> items;
    private FragmentManager fragmentManager;

    public DashboardViewPagerAdapter(FragmentManager fragmentManager, List<DashboardViewPagerItem> items) {
        super(fragmentManager);
        this.items = items;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle fragmentTagBundle = new Bundle();
        fragmentTagBundle.putString(ApplicationConstants.FRAGMENT_TAG_KEY, ApplicationConstants.FRAGMENT_DASHBOARD + position);
        Fragment fragment = items.get(position).getFragment();
        fragment.setArguments(fragmentTagBundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return items.get(position).getFragmentTitle();
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }

    public List<DashboardViewPagerItem> getItems() {
        return items;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void setAdapterForViewPager(final ViewPager viewPager) {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                List<Fragment> fragments = fragmentManager.getFragments();
                Fragment fragment = null;
                for (int i = 0; i < fragments.size(); i++) {
                    Fragment cachedFragment = fragmentManager.getFragments().get(i);
                    if (cachedFragment != null) {
                        Bundle bundle = cachedFragment.getArguments();
                        if (bundle != null) {
                            String tag = bundle.getString(ApplicationConstants.FRAGMENT_TAG_KEY);
                            if (tag != null) {
                                if (tag.equalsIgnoreCase(ApplicationConstants.FRAGMENT_DASHBOARD + position)) {
                                    fragment = cachedFragment;
                                    break;
                                }
                            }

                        }
                    }
                }
                if (fragment != null && fragment instanceof RentBnbOnFragmentSelectedListener) {
                    ((RentBnbOnFragmentSelectedListener) fragment).onFragmentSelected(fragmentManager, viewPager, position, fragment);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}