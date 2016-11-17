package com.intbit.rentbnb.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sakthivel on 14/11/16.
 */

public class Offer implements Parcelable {

    private String productName;
    private String productPrice;
    private String productUrl;
    private String productDescription;
    private String postedDate;

    public Offer() {
        
    }

    protected Offer(Parcel in) {
        productName = in.readString();
        productPrice = in.readString();
        productUrl = in.readString();
        productDescription = in.readString();
        postedDate = in.readString();
    }

    public static final Creator<Offer> CREATOR = new Creator<Offer>() {
        @Override
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        @Override
        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productPrice);
        dest.writeString(productUrl);
        dest.writeString(productDescription);
        dest.writeString(postedDate);
    }
}