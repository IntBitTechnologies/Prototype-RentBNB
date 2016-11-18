package com.intbit.rentbnb.ui.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class RentNowActivity extends RentbnbBaseActivity {

    private TextView startDateTextView, endDateTextView;
    private Button requestProductButton;
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private LinearLayout startDateLinearLayout, endDateLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_now);
        setupActionBar(getResources().getString(R.string.activity_title_rent_now), ActionBarActivityLeftAction.ACTION_CLOSE,ActionBarActivityRightAction.ACTION_NONE, ActionBarActivityRight2Action.ACTION_NONE);

        startDateTextView = (TextView) findViewById(R.id.activity_rent_now_startDate_TextView);
        endDateTextView = (TextView) findViewById(R.id.activity_rent_now_endDate_TextView);
        requestProductButton = (Button) findViewById(R.id.activity_rent_now_requestButton);
        startDateLinearLayout = (LinearLayout) findViewById(R.id.activity_rent_now_startDate_linearlayout);
        endDateLinearLayout = (LinearLayout) findViewById(R.id.activity_rent_now_endDate_linearlayout);

        startDateLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStartDatePicker(v);
            }
        });

        endDateLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndDatePicker(v);
            }
        });

        requestProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RentNowActivity.this, "Request Sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RentNowActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showStartDatePicker(final View view) {
        final Calendar calendar = (Calendar) Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(RentNowActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                startDateTextView.setText(dateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showEndDatePicker(final View view) {
        final Calendar calendar = (Calendar) Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(RentNowActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker v, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                endDateTextView.setText(dateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

}