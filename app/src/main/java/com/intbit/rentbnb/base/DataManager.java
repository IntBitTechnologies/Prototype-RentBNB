package com.intbit.rentbnb.base;

import com.intbit.rentbnb.models.Category;
import com.intbit.rentbnb.models.Product;
import com.intbit.rentbnb.models.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sakthivel on 14/11/16.
 */

public class DataManager {

    String[] categories = {"Antiques", "Appliances", "Arts", "Audio Equipment", "Auto Parts", "Baby", "Beauty", "Bicycles", "Boats", "Books", "Business", "Campers", "Cars", "CDs", "Cell Phones", "Clothing", "Collectibles", "Farming", "Furniture", "Games", "General", "Home", "Household", "Jewelry", "Motorcycles", "Musical Instruments", "Pet Supplies", "Photography", "Sports", "Tickets", "Tools", "TV", "Video Equipment", "Video Games"};

    String[] subCategories = {"Kitchen", "Home Automation", "Home"};

    String[] products = {"Toaster", "Slow cooker", "Mixer", "Blender", "Coffee maker", "Microwave", "Tandoor cookware"};

   /* String[] productsOfAutomation = {"Smart Smoke and Carbon Monoxide Detector", "Water / Flood Sensor", "Light Control Switches and Dimmers", "Energy Monitoring Switch", "Smart Doorbell"};

    String[] productsOfHome = {"Air Conditioner", "Gas fireplace", "Refrigerators", "Vacuum cleaner", "Electric water heater tank", "Small twin window fan"};*/

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < categories.length; i++) {
            Category category = new Category();
            category.setCategoryName(categories[i]);
            categoryList.add(category);
        }
        return categoryList;
    }

    public List<SubCategory> getAllSubCategories() {
        List<SubCategory> subCategoryList = new ArrayList<>();
        for (int i = 0; i < subCategories.length; i++) {
            SubCategory subCategory = new SubCategory();
            subCategory.setSubCategoryName(subCategories[i]);
            subCategoryList.add(subCategory);
        }
        return subCategoryList;
    }

    public List<Product> getAllProductList() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            Product product = new Product();
            product.setProductName(products[i]);
            product.setProductPrice(String.valueOf(i));
            productList.add(product);
        }
        return productList;
    }

}