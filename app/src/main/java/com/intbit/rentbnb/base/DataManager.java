package com.intbit.rentbnb.base;

import com.intbit.rentbnb.models.Category;
import com.intbit.rentbnb.models.Offer;
import com.intbit.rentbnb.models.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sakthivel on 14/11/16.
 */

public class DataManager {

    String[] categories = {"Business", "Sports", "Education", "Electronics", "Party", "Home", "Construction", "Uniform & Tuxedo"};

    String[] categoryUrls = {"business", "sports", "education", "electronics", "party", "home", "construction", "tuxedo"};

    String[] subCategories = {"Audio", "Auto", "HealthCare", "Laptops & Computers", "Phones & Tablets", "Video & Photography", "Other"};

    String[] subCategoriesUrls = {"audio", "auto", "healthcare", "laptop", "phonetablet", "photography", "other_electronics"};

    String[] products = {"Apple MacBook Pro", "Dell Inspiron", "HP Notebook", "HP Spectre", "Lenovo C2000 Desktop"};

    String[] productUrls = {"apple", "dell", "hp_notebook", "hp_spectre", "lenovoc2000"};

    String[] description = {"1GB RAM", "2GB RAM", "3GB RAM", "4GB RAM", "5GB RAM"};

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

    public List<SubCategory> getAllSubCategories() {
        List<SubCategory> subCategoryList = new ArrayList<>();
        for (int i = 0; i < subCategories.length; i++) {
            SubCategory subCategory = new SubCategory();
            subCategory.setSubCategoryName(subCategories[i]);
            subCategory.setImageUrl(subCategoriesUrls[i]);
            subCategoryList.add(subCategory);
        }
        return subCategoryList;
    }

    public List<Offer> getAllOffersList() {
        List<Offer> offerList = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            Offer offer = new Offer();
            offer.setProductName(products[i]);
            offer.setProductPrice(String.valueOf(i));
            offer.setProductUrl(productUrls[i]);
            offer.setPostedDate("16-Nov-2016");
            offer.setProductDescription(description[i]);
            offerList.add(offer);
        }
        return offerList;
    }

}