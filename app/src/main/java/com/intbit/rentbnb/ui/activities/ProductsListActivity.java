package com.intbit.rentbnb.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.ProductsListRecyclerViewAdapter;
import com.intbit.rentbnb.base.DataManager;
import com.intbit.rentbnb.models.Product;
import com.intbit.rentbnb.support.RecyclerItemClickListener;

import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    private RecyclerView productsRecyclerView;
    private ProductsListRecyclerViewAdapter productsListRecyclerViewAdapter;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        productsRecyclerView = (RecyclerView) findViewById(R.id.activity_products_list_recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        productsRecyclerView.setLayoutManager(mLayoutManager);

        dataManager = new DataManager();

        List<Product> productList = dataManager.getAllProductList();

        productsListRecyclerViewAdapter = new ProductsListRecyclerViewAdapter(productList, this);
        productsRecyclerView.setAdapter(productsListRecyclerViewAdapter);
        productsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(ProductsListActivity.this, "dfsf", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductsListActivity.this, ProductDetailActivity.class);
                        startActivity(intent);
                    }
                })
        );
    }

}