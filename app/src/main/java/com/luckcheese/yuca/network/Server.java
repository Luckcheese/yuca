package com.luckcheese.yuca.network;

import android.content.Context;

import com.luckcheese.yuca.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {

    private final Requests requests;

    public Server(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.host))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        requests = retrofit.create(Requests.class);
    }

    public Requests getRequests() {
        return requests;
    }
}
