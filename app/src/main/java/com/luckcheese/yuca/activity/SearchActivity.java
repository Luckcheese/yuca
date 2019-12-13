package com.luckcheese.yuca.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

public class SearchActivity extends AppCompatActivity implements ProductsCallback, OnProductClickListener, SearchView.OnQueryTextListener {

    private SearchController searchController;
    private FlowController flowController;

    private ProductsAdapter adapter;
    private SearchView searchView;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_activity, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    private void configureView() {
        adapter = new ProductsAdapter(this, this);

        RecyclerView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        int gridColumns = getResources().getInteger(R.integer.search_list_columns);
        listView.setLayoutManager(new GridLayoutManager(this, gridColumns));
    }

    private void search() {
        searchController.getProducts(null, this);
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

    // ----- SearchView.OnQueryTextListener

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchController.getProducts(query,this);
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.equals("")) {
            searchController.getProducts(null,this);
            return true;
        }
        return false;
    }
}
