package com.intbit.rentbnb.ui.activities;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.RentbnbBaseFragment;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferStepThreeFragment extends RentbnbBaseFragment {
    private Button step3NextButton;
    private SeekBar conditionSeekBar;
    private TextView conditionTextView;
    EditText priceEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_post_offer_step_3, container, false);
        conditionSeekBar = (SeekBar) v.findViewById(R.id.tab_post_offer_step_3_condition_seekBar);
        conditionTextView = (TextView) v.findViewById(R.id.tab_post_offer_step_3_condition_tracker_textView);
        step3NextButton = (Button) v.findViewById(R.id.tab_post_offer_step_3_next_button);
        priceEditText = (EditText) v.findViewById(R.id.tab_post_offer_step_3_price_EditText);
        priceEditText.requestFocus();

        step3NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Trigger code for changing page
                Preferences.setCurrentPage(4);
                ((PostOfferActivity) getActivity()).changefragmentalongStepProcess(4);
            }
        });

        // Initialize the textview with '0'.
        conditionTextView.setText("Condition: " + conditionSeekBar.getProgress() + "/" + conditionSeekBar.getMax());
        conditionSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 5;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                conditionTextView.setText("Condition: " + progress + "/" + seekBar.getMax());
            }
        });

        return v;
    }
}
