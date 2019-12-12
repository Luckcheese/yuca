package com.luckcheese.yuca.network;

public interface ServerCallback<T> {

    void onSuccess(T result);

    void onError(int statusCode, String message);
}
