package com.luckcheese.yuca.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.luckcheese.yuca.R;
import com.luckcheese.yuca.controller.FlowController;
import com.luckcheese.yuca.controller.SearchController;
import com.luckcheese.yuca.controller.callback.ProductsCallback;
import com.luckcheese.yuca.model.Product;
import com.luckcheese.yuca.view.adapter.ProductsAdapter;
import com.luckcheese.yuca.view.adapter.callback.OnProductClickListener;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements ProductsCallback, OnProductClickListener {

    private SearchController searchController;
    private FlowController flowController;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchController = new SearchController(this);
        flowController = new FlowController();
        configureView();

        search();
    }

    private void configureView() {
        adapter = new ProductsAdapter(this, this);

        RecyclerView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        int gridColumns = getResources().getInteger(R.integer.search_list_columns);
        listView.setLayoutManager(new GridLayoutManager(this, gridColumns));
    }

    private void search() {
        searchController.getProducts(this);
    }

    // ----- ProductsCallback

    @Override
    public void onProductsReceived(List<Product> products) {
        adapter.setProducts(products);
    }

    @Override
    public void onServerError(int statusCode, String message) {
        View rootView = findViewById(android.R.id.content);
        Snackbar.make(rootView, message, 5000).show();
    }

    // ----- OnProductClickListener

    @Override
    public void onProduct(Product product) {
        flowController.openProductDetails(this, product);
    }
}
