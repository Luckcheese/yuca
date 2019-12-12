package com.luckcheese.yuca.controller.callback;

import com.luckcheese.yuca.model.Product;

import java.util.List;

public interface ProductsCallback extends ErrorCallback {

    void onProductsReceived(List<Product> products);
}
