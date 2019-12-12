package com.luckcheese.yuca.controller.callback;

public interface ErrorCallback {

    void onServerError(int statusCode, String message);
}
