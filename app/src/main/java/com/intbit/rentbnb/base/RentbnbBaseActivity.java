package com.intbit.rentbnb.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intbit.rentbnb.R;


/**
 * Created by Sakthivel on 12/11/15.
 */
public abstract class RentbnbBaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Boolean activityFinishFlag;
    public boolean shouldDiscardChanges = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //hiding keyboard always
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopProgressDialog();
        hideRefreshSpinner();
    }

    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void startProgressDialog() {
        startProgressDialog(true);
    }

    public void startProgressDialog(boolean ifCancelable) {
        if (!isFinishing()) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(ifCancelable);
            }
            progressDialog.show();
        }
    }

    public void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        ;
    }

    public abstract void getData();

    public void showRefreshSpinner() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void hideRefreshSpinner() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public void shouldEnablePullToRefresh(boolean enable) {
        swipeRefreshLayout.setEnabled(enable);
    }

    /*public void initializeViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.global_SwipeRefreshLayout);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(true);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //showRefreshSpinner();
                    getData();
                }
            });
        }
    }*/

    public void setupActionBarTitle(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View customView = getLayoutInflater().inflate(R.layout.rentbnb_framework_action_bar_title_view, null);
        getSupportActionBar().setCustomView(customView);
        Toolbar parent = (Toolbar) customView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        final TextView activityTitle = ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title_textview));
        activityTitle.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        activityTitle.setText(title);
    }

    public void setupActionBar(String title, final ActionBarActivityLeftAction leftAction, final ActionBarActivityRightAction rightAction, final ActionBarActivityRight2Action right2Action) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View customView = getLayoutInflater().inflate(R.layout.rentbnb_framework_action_bar_view, null);
        getSupportActionBar().setCustomView(customView);
        Toolbar parent = (Toolbar) customView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        final TextView activityTitle = ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title));
        activityTitle.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        activityTitle.setText(title);
        final ImageView leftIcon = ((ImageView) getSupportActionBar().getCustomView().findViewById(R.id.action_bar_leftIcon));
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBarLeftButtonClicked();
                if (activityFinishFlag && !shouldDiscardChanges) {
                    finish();
                } else if (shouldDiscardChanges) {
                    onBackPressed();
                }
            }
        });
        leftIcon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    TypedValue outValue = new TypedValue();
                    getApplicationContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true);
                    ((ImageView) v).setBackgroundResource(outValue.resourceId);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ((ImageView) v).setBackgroundColor(getResources().getColor(android.R.color.transparent));
                }
                return false;
            }
        });

        final ImageView rightIcon = ((ImageView) getSupportActionBar().getCustomView().findViewById(R.id.action_bar_rightIcon));
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightAction == ActionBarActivityRightAction.ACTION_DELETE) {
                    actionBarRightButtonClicked();
                }
            }
        });

        final ImageView rightIcon2 = ((ImageView) getSupportActionBar().getCustomView().findViewById(R.id.action_bar_rightIcon2));
        rightIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (right2Action == ActionBarActivityRight2Action.ACTION_DONE) {
                    actionBarRight2ButtonClicked();
                } else if (right2Action == ActionBarActivityRight2Action.ACTION_NEXT) {
                    actionBarRight2ButtonClicked();
                } else if (right2Action == ActionBarActivityRight2Action.ACTION_EDIT) {
                    actionBarRight2ButtonClicked();
                } else if (right2Action == ActionBarActivityRight2Action.ACTION_DONE) {
                    actionBarRight2ButtonClicked();
                }
            }
        });

        rightIcon.setVisibility(View.GONE);
        leftIcon.setVisibility(View.GONE);
        rightIcon2.setVisibility(View.INVISIBLE);
        RelativeLayout.LayoutParams relativeLayoutParam = (RelativeLayout.LayoutParams) activityTitle.getLayoutParams();
        switch (leftAction) {
            case ACTION_BACK:
                leftIcon.setImageResource(R.drawable.keyboardback_icon);
                leftIcon.setVisibility(View.VISIBLE);
                activityFinishFlag = true;
                break;
            case ACTION_CLOSE:
                leftIcon.setVisibility(View.VISIBLE);
                leftIcon.setImageResource(R.drawable.close_icon);
                activityFinishFlag = true;
                break;
            case ACTION_NONE:
                activityFinishFlag = false;
                break;
        }
        switch (rightAction) {
            case ACTION_NONE:
                break;
            case ACTION_DELETE:
                rightIcon.setVisibility(View.VISIBLE);
                rightIcon.setImageResource(R.drawable.delete_icon);
                break;
        }
        switch (right2Action) {
            case ACTION_NONE:
                break;
            case ACTION_EDIT:
                rightIcon2.setVisibility(View.VISIBLE);
                rightIcon2.setImageResource(R.drawable.edit_icon);
                break;
            case ACTION_DONE:
                rightIcon2.setVisibility(View.VISIBLE);
                rightIcon2.setImageResource(R.drawable.save_icon);
                break;
            case ACTION_NEXT:
                rightIcon2.setVisibility(View.VISIBLE);
                rightIcon2.setImageResource(R.drawable.keyboard_next_icon);
                break;
        }
    }

    public void showToolbar(boolean show) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        if (show) {
            toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        } else {
            toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        }
    }

    public void actionBarLeftButtonClicked() {
        //Toast.makeText(this, "LeftIcon Clicked", Toast.LENGTH_SHORT).show();
    }

    public void actionBarRightButtonClicked() {
        //Toast.makeText(this, "RightIcon Clicked", Toast.LENGTH_SHORT).show();
    }

    public void actionBarRight2ButtonClicked() {
        //Toast.makeText(this, "Clicked Right2Icon", Toast.LENGTH_SHORT).show();
    }

    public void actionBarRight2TextClicked() {
        //Toast.makeText(this, "Clicked Right2Text", Toast.LENGTH_SHORT).show();
    }

    public enum ActionBarActivityLeftAction {

        ACTION_NONE,
        ACTION_CLOSE,
        ACTION_BACK
    }

    public enum ActionBarActivityRightAction {

        ACTION_NONE,
        ACTION_DELETE
    }

    public enum ActionBarActivityRight2Action {
        ACTION_NONE,
        ACTION_DONE,
        ACTION_EDIT,
        ACTION_NEXT
    }

    @Override
    public void onBackPressed() {
        if (shouldDiscardChanges) {
            showDiscardAlert();
        }
    }

    void showDiscardAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(getResources().getString(R.string.discard_changes_title));
        builder.setMessage(getResources().getString(R.string.discard_changes_message));
        builder.setPositiveButton(getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        builder.setNegativeButton(getResources().getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

}
