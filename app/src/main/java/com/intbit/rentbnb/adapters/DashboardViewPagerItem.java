package com.intbit.rentbnb.adapters;
import android.support.v4.app.Fragment;
/**
 * Created by Haider on 18/2/16.
 */
public class DashboardViewPagerItem {
    private Fragment fragment;
    private String fragmentTitle;
    private int fragmentIcon;

    public DashboardViewPagerItem(Fragment fragment, String fragmentTitle, int fragmentIcon){
        this.fragment = fragment;
        this.fragmentTitle = fragmentTitle;
        this.fragmentIcon = fragmentIcon;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getFragmentTitle() {
        return fragmentTitle;
    }

    public void setFragmentTitle(String fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
    }

    public int getFragmentIcon() {
        return fragmentIcon;
    }

    public void setFragmentIcon(int fragmentIcon) {
        this.fragmentIcon = fragmentIcon;
    }

}
