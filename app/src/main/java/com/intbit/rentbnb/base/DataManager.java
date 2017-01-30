package com.intbit.rentbnb.base;

import com.intbit.rentbnb.models.Category;
import com.intbit.rentbnb.models.Offer;
import com.intbit.rentbnb.models.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sakthivel on 14/11/16.
 */

public class DataManager {

    String[] categories = {"Home & Garden", "Fashion & Accessories", "Baby Care", "Cars & Motors", "Electronics", "Free Stuff", "Sports", "Games & Leisure", "Multimedia", "Other"};

    String[] categoryUrls = {"category_home", "category_fashion", "category_children", "category_cars", "category_electronics", "category_free_stuff", "category_sport", "category_games", "category_multimedia", "category_other" };

    String[] products = {"Apple MacBook Pro", "Dell Inspiron", "HP Notebook", "HP Spectre", "Lenovo C2000 Desktop", "Lenovo C2000 Desktop", "Lenovo C2000 Desktop", "Lenovo C2000 Desktop"};

    String[] productUrls = {"business", "sports", "education", "electronics", "party", "home", "construction", "tuxedo"};

    String[] description = {"1GB RAM", "2GB RAM", "3GB RAM", "4GB RAM", "5GB RAM", "6GB RAM", "7GB RAM", "8GB RAM"};

    String[] listedBy = {"Harry Potter", "Tom Cruise", "Brad Pitt", "Angelina Jolie", "Emma Watson", "Emma Watson", "Emma Watson", "Emma Watson"};

    String[] requestProducts = {"Harley Davidson for 1 Week", "Apple Laptop for 3 Months"};

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < categories.length; i++) {
            Category category = new Category();
            category.setCategoryName(categories[i]);
            category.setImageUrl(categoryUrls[i]);
            categoryList.add(category);
        }
        return categoryList;
    }

    public List<Offer> getAllOffersList() {
        List<Offer> offerList = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            Offer offer = new Offer();
            offer.setProductName(products[i]);
            offer.setProductPrice(String.valueOf(i + 5));
            //offer.setProductUrl(productUrls[i]);
            offer.setProductUrl(productUrls[i]);
            offer.setPostedDate("16-Nov-2016");
            offer.setProductDescription(description[i]);
            offer.setProductListedBy(listedBy[i]);
            offerList.add(offer);
        }
        return offerList;
    }

    public List<Request> getAllOpenRequests() {
        List<Request> requestList = new ArrayList<>();
        for (int i = 0; i < requestProducts.length; i++) {
            Request request = new Request();
            request.setRequestedBy("Dan Brown");
            request.setRequestedDate("24-Nov-2016");
            request.setRequestedModel("VPCEG-18");
            request.setRequestedProduct(requestProducts[i]);
            requestList.add(request);
        }
        return requestList;
    }

    public List<Offer> getAllRentOffers() {
        List<Offer> offerList = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            Offer offer = new Offer();
            offer.setProductName(products[i]);
            offer.setProductPrice(String.valueOf(i + 5));
            //offer.setProductUrl(productUrls[i]);
            offer.setProductUrl(productUrls[i]);
            offer.setPostedDate("16-Nov-2016");
            offer.setProductDescription(description[i]);
            offer.setProductListedBy(listedBy[i]);
            offerList.add(offer);
        }
        return offerList;
    }

    public List<Offer> getAllBuyOffers() {
        List<Offer> offerList = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            Offer offer = new Offer();
            offer.setProductName(products[i]);
            offer.setProductPrice(String.valueOf(i + 5));
            //offer.setProductUrl(productUrls[i]);
            offer.setProductUrl(productUrls[i]);
            offer.setPostedDate("16-Nov-2016");
            offer.setProductDescription(description[i]);
            offer.setProductListedBy(listedBy[i]);
            offerList.add(offer);
        }
        return offerList;
    }

    public List<Offer> getAllRequests() {
        List<Offer> offerList = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            Offer offer = new Offer();
            offer.setProductName(products[i]);
            offer.setProductPrice(String.valueOf(i + 5));
            //offer.setProductUrl(productUrls[i]);
            offer.setProductUrl(productUrls[i]);
            offer.setPostedDate("16-Nov-2016");
            offer.setProductDescription(description[i]);
            offer.setProductListedBy(listedBy[i]);
            offerList.add(offer);
        }
        return offerList;
    }

}