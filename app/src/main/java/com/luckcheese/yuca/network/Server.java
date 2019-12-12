package com.luckcheese.yuca.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {

    private final Requests requests;

    public Server() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://5dc05c0f95f4b90014ddc651.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        requests = retrofit.create(Requests.class);
    }

    public Requests getRequests() {
        return requests;
    }
}
