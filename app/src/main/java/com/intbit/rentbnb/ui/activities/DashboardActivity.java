package com.intbit.rentbnb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.CategoriesListRecyclerViewAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.models.Category;

import java.util.List;

public class DashboardActivity extends RentbnbBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView categoriesRecyclerView;
    private CategoriesListRecyclerViewAdapter mAdapter;
    private DataManager dataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initializeViews();

        dataManager = new DataManager();

        GridLayoutManager mLayoutManager = new GridLayoutManager(DashboardActivity.this, 2);
        categoriesRecyclerView.setLayoutManager(mLayoutManager);

        List<Category> allCategoriesList = dataManager.getAllCategories();

        mAdapter = new CategoriesListRecyclerViewAdapter(allCategoriesList, this);
        categoriesRecyclerView.setAdapter(mAdapter);
    }

    private void initializeViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, PostOfferActivity.class);
                startActivity(intent);
            }
        });

        categoriesRecyclerView = (RecyclerView) findViewById(R.id.activity_main_categories_recyclerView);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
            Intent intent = new Intent(DashboardActivity.this, OpenRequestsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_requestProduct) {
            //Intent intent = new Intent(DashboardActivity.this, RequestProductActivity.class);
            //startActivity(intent);
        } else if (id == R.id.nav_share) {
            shareAppLink();
        } else if (id == R.id.nav_logOut) {

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
