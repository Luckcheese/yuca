package com.luckcheese.yuca.network;

import android.content.Context;

import androidx.annotation.NonNull;

import com.luckcheese.yuca.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {

    private final Requests requests;
    private final String serverError;

    public Server(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.host))
                .addConverterFactory(GsonConverterFactory.create())
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
                    } catch (IOException e) {
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
