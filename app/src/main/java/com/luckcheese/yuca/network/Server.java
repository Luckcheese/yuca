package com.luckcheese.yuca.network;

import android.content.Context;

import androidx.annotation.NonNull;

import com.luckcheese.yuca.BuildConfig;
import com.luckcheese.yuca.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {

    private final Requests requests;
    private final String serverError;

    public Server(Context context) {

        // log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        // http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.host))
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        requests = retrofit.create(Requests.class);

        serverError = context.getString(R.string.default_server_error);
    }

    public Requests getRequests() {
        return requests;
    }

    public <T> void call(@NonNull Call<T> request, @NonNull final ServerCallback<T> callback) {
        request.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (IOException | NullPointerException e) {
                        errorMessage = serverError;
                    }

                    callback.onError(response.code(), errorMessage);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onError(0, serverError);
            }
        });
    }
}
