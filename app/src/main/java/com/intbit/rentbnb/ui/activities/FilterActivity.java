package com.intbit.rentbnb.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;

/**
 * Created by sakthi on 2/1/17.
 */

public class FilterActivity extends RentbnbBaseActivity {

    Button categoriesButton, locationButton, priceButton;
    Button sortLowtoHighButton, sortHightoLowButton, sortNewestButton, sortClosestButton;
    Button categoryHomeButton, categoryFashionButton, categoryBabyButton, categoryCarsButton, categoryElectronicsButton, categoryFreeButton, categorySportsButton, categoryGamesButton, categoryMultimediaButton, categoryOtherButton;
    LinearLayout categoriesLayout, categories2Layout, priceLayout;
    RelativeLayout distanceLayout;
    FloatingActionButton selectFAB;
    SeekBar distanceSeekBar;
    TextView distanceInfoTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        setupActionBar(getResources().getString(R.string.filter), ActionBarActivityLeftAction.ACTION_BACK, ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_RESET);

        intializeViews();

        MyTouchListener touchListener = new MyTouchListener();

        distanceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                distanceInfoTextView.setText(String.valueOf(new Integer(progress))+" miles");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        categoriesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleButtonSelection(categoriesButton);
                return true;
            }
        });

        locationButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleButtonSelection(locationButton);
                return true;
            }
        });

        priceButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleButtonSelection(priceButton);
                return true;
            }
        });

        selectFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sortLowtoHighButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleSortButtons(sortLowtoHighButton);
                return true;
            }
        });

        sortHightoLowButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleSortButtons(sortHightoLowButton);
                return true;
            }
        });

        sortNewestButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleSortButtons(sortNewestButton);
                return true;
            }
        });

        sortClosestButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleSortButtons(sortClosestButton);
                return true;
            }
        });

        categoryHomeButton.setOnTouchListener(touchListener);
        categoryFashionButton.setOnTouchListener(touchListener);
        categoryBabyButton.setOnTouchListener(touchListener);
        categoryCarsButton.setOnTouchListener(touchListener);
        categoryElectronicsButton.setOnTouchListener(touchListener);
        categoryFreeButton.setOnTouchListener(touchListener);
        categorySportsButton.setOnTouchListener(touchListener);
        categoryGamesButton.setOnTouchListener(touchListener);
        categoryMultimediaButton.setOnTouchListener(touchListener);
        categoryOtherButton.setOnTouchListener(touchListener);
    }

    private void toggleSortButtons(Button button) {
        clearAllSortButtonStates();

        if (button == sortLowtoHighButton) {
            sortLowtoHighButton.setPressed(true);
        } else if (button == sortHightoLowButton) {
            sortHightoLowButton.setPressed(true);
        } else if (button == sortNewestButton) {
            sortNewestButton.setPressed(true);
        } else if (button == sortClosestButton) {
            sortClosestButton.setPressed(true);
        }
    }

    private void toggleButtonSelection(Button button) {
        hideAllLayouts();
        clearAllButtonStates();

        if (button == categoriesButton) {
            categoriesButton.setPressed(true);
            showCategoriesLayout();
        } else if (button == locationButton) {
            locationButton.setPressed(true);
            showLocationLayout();
        } else if (button == priceButton) {
            priceButton.setPressed(true);
            showPriceLayout();
        }
    }

    private void clearAllButtonStates() {
        categoriesButton.setPressed(false);
        locationButton.setPressed(false);
        priceButton.setPressed(false);
    }

    private void clearAllSortButtonStates() {
        sortLowtoHighButton.setPressed(false);
        sortHightoLowButton.setPressed(false);
        sortNewestButton.setPressed(false);
        sortClosestButton.setPressed(false);
    }

    private void intializeViews() {
        categoriesButton = (Button) findViewById(R.id.activity_filter_categories_button);
        locationButton = (Button) findViewById(R.id.activity_filter_location_button);
        priceButton = (Button) findViewById(R.id.activity_filter_price_button);
        categoriesLayout = (LinearLayout) findViewById(R.id.activity_filter_categories_layout);
        categories2Layout = (LinearLayout) findViewById(R.id.activity_filter_categories2_layout);
        priceLayout = (LinearLayout) findViewById(R.id.activity_filter_price_layout);
        distanceLayout = (RelativeLayout) findViewById(R.id.activity_filter_layout_distance);
        selectFAB = (FloatingActionButton) findViewById(R.id.activity_filter_select_options_fab);
        sortLowtoHighButton = (Button) findViewById(R.id.activity_filter_sort_price_lowtohigh);
        sortHightoLowButton = (Button) findViewById(R.id.activity_filter_sort_price_hightolow);
        sortNewestButton = (Button) findViewById(R.id.activity_filter_sort_newest_ads);
        sortClosestButton = (Button) findViewById(R.id.activity_filter_sort_closest_ads);

        categoryHomeButton = (Button) findViewById(R.id.category_home_button);
        categoryFashionButton = (Button) findViewById(R.id.category_fashion_button);
        categoryBabyButton = (Button) findViewById(R.id.category_baby_button);
        categoryCarsButton = (Button) findViewById(R.id.category_cars_button);
        categoryElectronicsButton = (Button) findViewById(R.id.category_electronics_button);
        categoryFreeButton = (Button) findViewById(R.id.category_free_button);
        categorySportsButton = (Button) findViewById(R.id.category_sports_button);
        categoryGamesButton = (Button) findViewById(R.id.category_games_button);
        categoryMultimediaButton = (Button) findViewById(R.id.category_multimedia_button);
        categoryOtherButton = (Button) findViewById(R.id.category_other_button);

        distanceSeekBar = (SeekBar) findViewById(R.id.activity_filter_location_seekBar);
        distanceInfoTextView = (TextView) findViewById(R.id.activity_filter_distance_value_textView);

        hideAllLayouts();
    }

    private void hideAllLayouts() {
        categoriesLayout.setVisibility(View.GONE);
        categories2Layout.setVisibility(View.GONE);
        priceLayout.setVisibility(View.GONE);
        distanceLayout.setVisibility(View.GONE);
    }

    private void showLocationLayout() {
        locationButton.setActivated(true);
        distanceLayout.setVisibility(View.VISIBLE);
    }

    private void showPriceLayout() {
        priceLayout.setVisibility(View.VISIBLE);
    }

    private void showCategoriesLayout() {
        categoriesLayout.setVisibility(View.VISIBLE);
        categories2Layout.setVisibility(View.VISIBLE);
    }

    public void actionBarRight2TextClicked() {
        resetPage();
    }

    private void resetPage() {
        hideAllLayouts();
        clearAllButtonStates();
        clearAllSortButtonStates();
    }

    public class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.isPressed()) {
                v.setPressed(false);
            } else {
                v.setPressed(true);
            }
            return true;
        }
    }
}