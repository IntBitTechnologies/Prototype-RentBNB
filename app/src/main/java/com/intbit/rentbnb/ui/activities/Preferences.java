package com.intbit.rentbnb.ui.activities;

import android.content.SharedPreferences;

import com.intbit.rentbnb.base.ApplicationConstants;
import com.intbit.rentbnb.base.RentbnbApplication;

/**
 * Created by Adiba on 14/11/2016.
 */

public class Preferences {

    public static void setCurrentPage(int page) {
        SharedPreferences settings = RentbnbApplication.getContext().getSharedPreferences(ApplicationConstants.StepperPage, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(ApplicationConstants.CurrentPage, page);
        editor.commit();
    }

    public static int getCurrentPage() {
        SharedPreferences settings = RentbnbApplication.getContext().getSharedPreferences(ApplicationConstants.StepperPage, 0);
        return settings.getInt(ApplicationConstants.CurrentPage, 0);
    }

    public static void clearCurrentpage() {
        SharedPreferences settings = RentbnbApplication.getContext().getSharedPreferences(ApplicationConstants.StepperPage, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }
}