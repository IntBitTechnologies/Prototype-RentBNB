package com.intbit.rentbnb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.DashboardViewPagerAdapter;
import com.intbit.rentbnb.adapters.DashboardViewPagerItem;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.support.SlidingTabLayout.SlidingTabLayout;
import com.intbit.rentbnb.ui.activities.fragments.BuyTab;
import com.intbit.rentbnb.ui.activities.fragments.RentTab;
import com.intbit.rentbnb.ui.activities.fragments.RentToOwnTab;
import com.intbit.rentbnb.ui.activities.fragments.RequestsTab;

import java.util.ArrayList;

public class DashboardActivity extends RentbnbBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager pager;
    private DashboardViewPagerAdapter dashboardViewPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initializeViews();

        pager = (ViewPager) findViewById(R.id.activity_dashboard_viewPager);
        pager.setAdapter(dashboardViewPagerAdapter);

        setAdapterItems();

        SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.activity_dashboard_slidingTabLayout);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(pager);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });

    }

    private void initializeViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setIcon(R.drawable.ic_logo);

        SearchView searchView = (SearchView) findViewById(R.id.activity_main_categories_searchView);
        searchView.setQueryHint(getResources().getString(R.string.search_hint));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(DashboardActivity.this, PostOfferActivity.class);
                Intent intent = new Intent(DashboardActivity.this, NewRequestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapterItems() {

        String titles[] = getResources().getStringArray(R.array.dashboard_tab_titles);
        int icons[] = new int[]{R.drawable.calendar_icon, R.drawable.calendar_icon, R.drawable.calendar_icon, R.drawable.calendar_icon};

        ArrayList<DashboardViewPagerItem> items = new ArrayList<DashboardViewPagerItem>();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        for (int i = 0; i < titles.length; i++) {

            switch (i) {
                case 0:
                    fragment = new RentTab();
                    break;
                case 1:
                    fragment = new BuyTab();
                    break;
                case 2:
                    fragment = new RentToOwnTab();
                    break;
                case 3:
                    fragment = new RequestsTab();
                    break;
            }
            items.add(new DashboardViewPagerItem(fragment, titles[i], icons[i]));
        }

        dashboardViewPagerAdapter = new DashboardViewPagerAdapter(fragmentManager, items);
        dashboardViewPagerAdapter.setAdapterForViewPager(pager);

        pager.setAdapter(dashboardViewPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Toast.makeText(DashboardActivity.this, "Filter", Toast.LENGTH_SHORT).show();
            Intent filterIntent = new Intent(DashboardActivity.this, FilterActivity.class);
            startActivity(filterIntent);
            return true;
        } else if (id == R.id.action_changeView) {
            Toast.makeText(DashboardActivity.this, "Change View", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            //Already in Dashboard
        } else if (id == R.id.nav_myListings) {
            Intent intent = new Intent(DashboardActivity.this, MyListingActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_myOrders) {
            Intent intent = new Intent(DashboardActivity.this, MyOrdersActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_openRequest) {
            Intent intent = new Intent(DashboardActivity.this, RequestProductActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void shareAppLink() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.app_name) + "\n" + getResources().getString(R.string.app_name));
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}