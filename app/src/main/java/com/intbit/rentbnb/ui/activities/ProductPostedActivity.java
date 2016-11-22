package com.intbit.rentbnb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseActivity;

public class ProductPostedActivity extends RentbnbBaseActivity {

    Button finishButton,postAnotherButton,shareButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_posted);

        finishButton = (Button) findViewById(R.id.activity_product_posted_finish_button);
        postAnotherButton = (Button) findViewById(R.id.activity_product_posted_post_another_button);
        shareButton = (Button) findViewById(R.id.activity_product_posted_share_button);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductPostedActivity.this, DashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        postAnotherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Preferences.setCurrentPage(1);
                //(PostOfferActivity).changefragmentalongStepProcess(1);

            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.share_subject));
                sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_description));
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

}