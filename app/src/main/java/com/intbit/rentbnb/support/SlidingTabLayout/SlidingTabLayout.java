package com.intbit.rentbnb.support.SlidingTabLayout;

/**
 * Created by sakthi on 8/12/15.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intbit.rentbnb.R;

/*import com.intbit.docapp.R;
import com.intbit.docapp.base.ApplicationConstants;
import com.intbit.docapp.ui.adapters.viewpageradapters.PatientViewPagerAdapter;
import com.intbit.docapp.ui.adapters.viewpageradapters.ProvidersViewPagerAdapter;
import com.intbit.docapp.ui.adapters.viewpageradapters.DashboardViewPagerAdapter;*/

/**
 * To be used with ViewPager to provide a tab indicator component which give constant feedback as to
 * the user's scroll progress.
 * <p/>
 * To use the component, simply add it to your view hierarchy. Then in your
 * {@link android.app.Activity} or {@link android.support.v4.app.Fragment} call
 * {@link #setViewPager(ViewPager)} providing it the ViewPager this layout is being used for.
 * <p/>
 * The colors can be customized in two ways. The first and simplest is to provide an array of colors
 * via {@link #setSelectedIndicatorColors(int...)}. The
 * alternative is via the {@link TabColorizer} interface which provides you complete control over
 * which color is used for any individual position.
 * <p/>
 * The views used as tabs can be customized by calling {@link #setCustomTabView(int, int, int)},
 * providing the layout ID of your custom layout.
 */
public class SlidingTabLayout extends HorizontalScrollView {
    /**
     * Allows complete control over the colors drawn in the tab layout. Set with
     * {@link #setCustomTabColorizer(TabColorizer)}.
     */
    public interface TabColorizer {

        /**
         * @return return the color of the indicator used when {@code position} is selected.
         */
        int getIndicatorColor(int position);

    }

    private static final int TITLE_OFFSET_DIPS = 14;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 14;

    private int mTitleOffset;

    private int mTabViewLayoutId;
    private int mTabViewTextViewId;
    private int mTabViewImageViewId;
    private boolean mDistributeEvenly;

    private ViewPager mViewPager;
    private SparseArray<String> mContentDescriptions = new SparseArray<String>();
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    private final SlidingTabStrip mTabStrip;

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Disable the Scroll Bar
        //setHorizontalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(true);
        // Make sure that the Tab Strips fills this View
        setFillViewport(true);

        mTitleOffset = (int) (TITLE_OFFSET_DIPS * getResources().getDisplayMetrics().density);

        mTabStrip = new SlidingTabStrip(context);
        //addView(mTabStrip, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mTabStrip, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * Set the custom {@link TabColorizer} to be used.
     * <p/>
     * If you only require simple custmisation then you can use
     * {@link #setSelectedIndicatorColors(int...)} to achieve
     * similar effects.
     */
    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        mTabStrip.setCustomTabColorizer(tabColorizer);
    }

    public void setDistributeEvenly(boolean distributeEvenly) {
        mDistributeEvenly = distributeEvenly;
    }

    /**
     * Sets the colors to be used for indicating the selected tab. These colors are treated as a
     * circular array. Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setSelectedIndicatorColors(int... colors) {
        mTabStrip.setSelectedIndicatorColors(colors);
    }

    /**
     * Set the {@link ViewPager.OnPageChangeListener}. When using {@link SlidingTabLayout} you are
     * required to set any {@link ViewPager.OnPageChangeListener} through this method. This is so
     * that the layout can update it's scroll position correctly.
     *
     * @see ViewPager#setOnPageChangeListener(ViewPager.OnPageChangeListener)
     */
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPagerPageChangeListener = listener;
    }

    /**
     * Set the custom layout to be inflated for the tab views.
     *
     * @param layoutResId Layout id to be inflated
     * @param textViewId  id of the {@link TextView} in the inflated view
     */
    public void setCustomTabView(int layoutResId, int textViewId, int imageViewId) {
        mTabViewLayoutId = layoutResId;
        mTabViewTextViewId = textViewId;
        mTabViewImageViewId = imageViewId;
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     */
    public void setViewPager(ViewPager viewPager) {
        mTabStrip.removeAllViews();

        mViewPager = viewPager;
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
        }
    }

    /**
     * Create a default view to be used for tabs. This is called if a custom tab view is not set via
     * {@link #setCustomTabView(int, int, int)}.
     */
    protected TextView createDefaultTabView(Context context) {
        TextView textView = new TextView(context);
        textView.setId(R.id.action_bar_title_textview);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TAB_VIEW_TEXT_SIZE_SP);
        textView.setTypeface(Typeface.DEFAULT);
        textView.setTextColor(getResources().getColor(R.color.primary_light));
        LinearLayout.LayoutParams tabTextView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tabTextView.gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        textView.setLayoutParams(tabTextView);
        textView.setPadding(0, 20, 0, 30);
        textView.setAllCaps(false);

        return textView;
    }

    /*private ImageView createDefaultImageIcon(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setId(R.id.addproduct_rowitem_image);
        imageView.setImageResource(R.drawable.add_icon);
        imageView.setColorFilter(getResources().getColor(R.color.primary_light));
        LinearLayout.LayoutParams tabLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tabLL.gravity = (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        imageView.setLayoutParams(tabLL);
        imageView.setPadding(0, 30, 0, 0);
        return imageView;
    }*/

    private LinearLayout createDefaultTabLL(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        //linearLayout.addView(createDefaultImageIcon(context));
        linearLayout.addView(createDefaultTabView(context));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        getContext().getTheme().applyStyle(R.style.colorControlHighlight_red, true);
        linearLayout.setBackgroundResource(outValue.resourceId);
        return linearLayout;
    }

    private void populateTabStrip() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final OnClickListener tabClickListener = new TabClickListener();

        for (int i = 0; i < adapter.getCount(); i++) {
            View tabView = null;
            LinearLayout tabViewLL = null;
            TextView tabTitleView = null;
            //ImageView tabIconView = null;

            if (mTabViewLayoutId != 0) {
                // If there is a custom tab view layout id set, try and inflate it
                tabView = LayoutInflater.from(getContext()).inflate(mTabViewLayoutId, mTabStrip,
                        false);
                tabTitleView = (TextView) tabView.findViewById(mTabViewTextViewId);
                //tabIconView = (ImageView) tabView.findViewById(mTabViewImageViewId);
            }

            if (tabViewLL == null) {
                tabViewLL = createDefaultTabLL(getContext());
            }

            if (tabTitleView == null) //&& TextView.class.isInstance(tabView)) {
            {
                tabTitleView = (TextView) tabViewLL.findViewById(R.id.action_bar_title_textview);
            }

            /*if (tabIconView == null) //&& ImageView.class.isInstance(tabView)) {
            {
                tabIconView = (ImageView) tabViewLL.findViewById(R.id.addproduct_rowitem_image);
            }*/

            if (mDistributeEvenly) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tabViewLL.getLayoutParams();
                lp.width = 0;
                lp.weight = 1;
            }

            tabTitleView.setText(adapter.getPageTitle(i));
            int resourceId = 0;
            /*if (viewType.equals(ApplicationConstants.USER_TYPE_PRACTICE)) {
                resourceId = ((DashboardViewPagerAdapter) adapter).getItems().get(i).getFragmentIcon();
            } else if (viewType.equals(ApplicationConstants.USER_TYPE_PROVIDER)) {
                resourceId = ((ProvidersViewPagerAdapter) adapter).getItems().get(i).getFragmentIcon();
            } else if (viewType.equals(ApplicationConstants.USER_TYPE_PATIENT)) {
                resourceId = ((PatientViewPagerAdapter) adapter).getItems().get(i).getFragmentIcon();
            }*/
            //tabIconView.setImageResource(resourceId);
            //tabIconView.setImageResource(R.mipmap.ic_launcher);
            tabViewLL.setOnClickListener(tabClickListener);
            String desc = mContentDescriptions.get(i, null);
            if (desc != null) {
                tabView.setContentDescription(desc);
            }

            /*if (tabIconView == null) {
                tabIconView = createDefaultImageIcon(getContext());
            }*/

            mTabStrip.addView(tabViewLL);
            if (i == mViewPager.getCurrentItem()) {
                tabViewLL.setSelected(true);
                tabTitleView.setTextColor(getResources().getColor(R.color.white));
                //tabIconView.setColorFilter(getResources().getColor(R.color.white));
            } else {
                tabTitleView.setTextColor(getResources().getColor(R.color.primary_light));
                //tabIconView.setColorFilter(getResources().getColor(R.color.primary_light));
            }
        }
    }

    public void setContentDescription(int i, String desc) {
        mContentDescriptions.put(i, desc);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mViewPager != null) {
            scrollToTab(mViewPager.getCurrentItem(), 0);
        }
    }

    private void scrollToTab(int tabIndex, int positionOffset) {
        final int tabStripChildCount = mTabStrip.getChildCount();
        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex >= tabStripChildCount) {
            return;
        }

        View selectedChild = mTabStrip.getChildAt(tabIndex);
        if (selectedChild != null) {
            int targetScrollX = selectedChild.getLeft() + positionOffset;

            if (tabIndex > 0 || positionOffset > 0) {
                // If we're not at the first child and are mid-scroll, make sure we obey the offset
                targetScrollX -= mTitleOffset;
            }

            scrollTo(targetScrollX, 0);
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = mTabStrip.getChildCount();
            if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount)) {
                return;
            }

            mTabStrip.onViewPagerPageChanged(position, positionOffset);

            View selectedTitle = mTabStrip.getChildAt(position);
            int extraOffset = (selectedTitle != null)
                    ? (int) (positionOffset * selectedTitle.getWidth())
                    : 0;
            scrollToTab(position, extraOffset);

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            TextView tabTextView;
            //ImageView tabIconImageView;
            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                mTabStrip.onViewPagerPageChanged(position, 0f);
                scrollToTab(position, 0);
            }
            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                mTabStrip.getChildAt(i).setSelected(position == i);
                tabTextView = (TextView) mTabStrip.getChildAt(i).findViewById(R.id.action_bar_title_textview);
                //tabIconImageView = (ImageView) mTabStrip.getChildAt(i).findViewById(R.id.addproduct_rowitem_image);
                if (i == position) {//active tab
                    if(tabTextView!=null) {
                        tabTextView.setTextColor(getResources().getColor(R.color.white));
                        //tabIconImageView.setColorFilter(getResources().getColor(R.color.white));
                    }
                } else {//inactive tab
                    if(tabTextView!=null) {
                        tabTextView.setTextColor(getResources().getColor(R.color.primary_light));
                        //tabIconImageView.setColorFilter(getResources().getColor(R.color.primary_light));
                    }
                }
            }
            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageSelected(position);
            }
        }

    }

    private class TabClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                if (v == mTabStrip.getChildAt(i)) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    }

}