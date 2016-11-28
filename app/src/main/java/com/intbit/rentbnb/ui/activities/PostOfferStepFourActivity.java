package com.intbit.rentbnb.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.intbit.rentbnb.R;
import com.intbit.rentbnb.base.ApplicationConstants;
import com.intbit.rentbnb.base.RentbnbBaseFragment;
import com.intbit.rentbnb.support.Locator;
import com.intbit.rentbnb.ui.activities.ProductPostedActivity;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Adiba on 14/11/2016.
 */

public class PostOfferStepFourActivity extends RentbnbBaseFragment implements Locator.Listener {

    private GoogleMap googleMap;
    Context mContext;
    private LocationManager locationManager;
    private Double currentLatitude;
    private Double currentLongitude;
    private int PERMISSION_REQUEST_FINE_LOCATION = ApplicationConstants.PERMISSION_REQUEST_FINE_LOCATION;
    private int PERMISSION_REQUEST_COARSE_LOCATION = ApplicationConstants.PERMISSION_REQUEST_COARSE_LOCATION;
    MapView mMapView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.tab_post_offer_step_4, container, false);

        initializeViews(v, savedInstanceState);

        gotoCurrentLocation();

        showCurrentPinCode();

        return v;
    }

    private void showCurrentPinCode() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getResources().getString(R.string.app_name));
        builder.setMessage(mContext.getResources().getString(R.string.your_current_location));
        builder.setPositiveButton(mContext.getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(mContext, ProductPostedActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ((Activity) mContext).finishAffinity();
                        mContext.startActivity(intent);
                    }
                });
        builder.setNegativeButton(mContext.getResources().getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initializeViews(View v, Bundle savedInstanceState) {
        mContext = getActivity().getApplicationContext();

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        mMapView = (MapView) v.findViewById(R.id.tab_post_offer_step4_mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gotoCurrentLocation() {
        if (checkCoarseLocation()) {
            if (checkFineLocation()) {
                Locator locator = new Locator(mContext);
                locator.getLocation(Locator.Method.NETWORK_THEN_GPS, this);
            }
        }
    }

    private boolean checkCoarseLocation() {
        boolean isPermissionGranted = false;
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        }
        return isPermissionGranted;
    }

    private boolean checkFineLocation() {
        boolean isPermissionGranted = false;
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_FINE_LOCATION);
        }
        return isPermissionGranted;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gotoCurrentLocation();
            }
        }

        if (requestCode == PERMISSION_REQUEST_COARSE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gotoCurrentLocation();
            }
        }
    }

    @Override
    public void onLocationFound(Location location) {

        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng currentLocation = new LatLng(currentLatitude, currentLongitude);
                googleMap.addMarker(new MarkerOptions().position(currentLocation).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(currentLatitude, currentLongitude))      // Sets the center of the map to location user
                        .zoom(17)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
    }

    @Override
    public void onLocationNotFound() {

    }

}