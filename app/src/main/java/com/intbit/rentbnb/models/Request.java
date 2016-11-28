package com.intbit.rentbnb.models;

/**
 * Created by sakthi on 24/11/16.
 */

public class Request {

    String requestedBy;
    String requestedDate;
    String requestedProduct;
    String requestedModel;

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getRequestedProduct() {
        return requestedProduct;
    }

    public void setRequestedProduct(String requestedProduct) {
        this.requestedProduct = requestedProduct;
    }

    public String getRequestedModel() {
        return requestedModel;
    }

    public void setRequestedModel(String requestedModel) {
        this.requestedModel = requestedModel;
    }
}