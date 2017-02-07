package com.intbit.rentbnb.ui.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.CustomSpinnerAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.base.RentbnbBaseActivity;
import com.intbit.rentbnb.models.Category;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class RequestProductActivity extends RentbnbBaseActivity {
    private Button sellButton, rentButton, bothButton, postARequestButton;
    private TextView fromTextView, toTextView;
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Spinner itemCategoriesSpinner;
    private DataManager dataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_product);

        setupActionBar(getResources().getString(R.string.request), RentbnbBaseActivity.ActionBarActivityLeftAction.ACTION_CLOSE, RentbnbBaseActivity.ActionBarActivityRightAction.ACTION_NONE, RentbnbBaseActivity.ActionBarActivityRight2Action.ACTION_NONE);

        initializeViews();
        dataManager = new DataManager();

        sellButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleButtonSelection(sellButton);
                return true;
            }
        });

        rentButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleButtonSelection(rentButton);
                return true;
            }
        });

        bothButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                toggleButtonSelection(bothButton);
                return true;
            }
        });

        fromTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(fromTextView);
            }
        });

        toTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(toTextView);
            }
        });

        postARequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RequestProductActivity.this,"Request Posted Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        itemCategoriesSpinner.setDropDownWidth(getScreenWidthInPixels());
        if(dataManager != null) {
            List<Category> categoryList = dataManager.getAllCategories();
            ArrayList<String> categories = new ArrayList<String>();
            ArrayList<String> categoriesIcons = new ArrayList<>();
            categories.add(getResources().getString(R.string.item_category));
            for (int i = 0; i < categoryList.size(); i++) {
                categories.add(categoryList.get(i).getCategoryName());
                categoriesIcons.add(categoryList.get(i).getImageUrl());
            }

            CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(RequestProductActivity.this, categories, categoriesIcons);
            itemCategoriesSpinner.setAdapter(customSpinnerAdapter);

        }
    }

    private void toggleButtonSelection(Button button) {
        clearAllButtonStates();

        if (button == sellButton) {
            sellButton.setPressed(true);
        } else if (button == rentButton) {
            rentButton.setPressed(true);
        } else if (button == bothButton) {
            bothButton.setPressed(true);
        }
    }

    private void clearAllButtonStates() {
        sellButton.setPressed(false);
        rentButton.setPressed(false);
        bothButton.setPressed(false);
    }

    private void initializeViews() {
        sellButton = (Button) findViewById(R.id.activity_request_product_sell_button);
        rentButton = (Button) findViewById(R.id.activity_request_product_rent_button);
        bothButton = (Button) findViewById(R.id.activity_request_product_both_button);
        fromTextView = (TextView) findViewById(R.id.from_selected_date_text_view);
        toTextView = (TextView) findViewById(R.id.to_selected_date_text_view);
        postARequestButton = (Button) findViewById(R.id.new_request_post_a_request_button);
        itemCategoriesSpinner = (Spinner) findViewById(R.id.request_a_product_item_category_spinner);
    }

    private void showDatePicker(final TextView textView) {
        final Calendar calendar = (Calendar) Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(RequestProductActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                textView.setText(dateFormat.format(calendar.getTime()));
                textView.setError(null);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private int getScreenWidthInPixels() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        RequestProductActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        return width;
    }
}