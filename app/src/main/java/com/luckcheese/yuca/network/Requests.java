package com.luckcheese.yuca.network;

import com.luckcheese.yuca.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Requests {

    @GET("elo7/api/1/products")
    Call<List<Product>> getProducts(@Query("q") String searchQuery);
}
