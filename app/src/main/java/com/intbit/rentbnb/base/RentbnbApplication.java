package com.intbit.rentbnb.base;

import android.app.Application;

/**
 * Created by Adiba on 16/11/2016.
 */

public class RentbnbApplication extends Application {
    protected static RentbnbApplication _context = null;

    public static RentbnbApplication getContext() {
        return _context;
    }

    public void onCreate() {
        super.onCreate();

        synchronized (RentbnbApplication.class) {
            _context = (RentbnbApplication) this.getApplicationContext();
        }
    }
}
