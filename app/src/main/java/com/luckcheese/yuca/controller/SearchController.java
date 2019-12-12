package com.luckcheese.yuca.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.luckcheese.yuca.controller.callback.ProductsCallback;
import com.luckcheese.yuca.model.Product;
import com.luckcheese.yuca.network.Server;
import com.luckcheese.yuca.network.ServerCallback;

import java.util.List;

public class SearchController {

    private final Server server;

    public SearchController(Context context) {
        server = new Server(context);
    }

    public void getProducts(@NonNull final ProductsCallback callback) {
        server.call(server.getRequests().getProducts(), new ServerCallback<List<Product>>() {

            @Override
            public void onSuccess(List<Product> result) {
                callback.onProductsReceived(result);
            }

            @Override
            public void onError(int statusCode, String message) {
                callback.onServerError(statusCode, message);
            }
        });
    }
}
