package com.intbit.rentbnb.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.ui.activities.FilterActivity;

/**
 * Created by Sakthivel on 30/12/2016.
 */

public abstract class RentbnbBaseFragment extends Fragment {

    public abstract void changeViewType();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_changeView) {
            if (RentBnbEnvironment.Offers_View_Mode == RentBnbEnums.Offers_View_Grid.toInt()) {
                item.setIcon(R.drawable.ic_grid_view);
            } else if (RentBnbEnvironment.Offers_View_Mode == RentBnbEnums.Offers_View_List.toInt()) {
                item.setIcon(R.drawable.ic_list_view);
            }
            changeViewType();
            return true;
        } else if (id == R.id.action_filter) {
            Intent filterIntent =  new Intent(getActivity().getApplicationContext(), FilterActivity.class);
            startActivity(filterIntent);
        }

        return super.onOptionsItemSelected(item);
    }

}