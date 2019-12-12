package com.luckcheese.yuca.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.luckcheese.yuca.R;
import com.luckcheese.yuca.controller.SearchController;
import com.luckcheese.yuca.controller.callback.ProductsCallback;
import com.luckcheese.yuca.model.Product;
import com.luckcheese.yuca.view.adapter.ProductsAdapter;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements ProductsCallback {

    private SearchController controller;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = new SearchController(this);
        configureView();

        search();
    }

    private void configureView() {
        adapter = new ProductsAdapter(this);

        RecyclerView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void search() {
        controller.getProducts(this);
    }

    @Override
    public void onProductsReceived(List<Product> products) {

    }

    @Override
    public void onServerError(int statusCode, String message) {
        View rootView = findViewById(android.R.id.content);
        Snackbar.make(rootView, message, 5000).show();
    }
}
