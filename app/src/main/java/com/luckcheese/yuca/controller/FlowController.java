package com.luckcheese.yuca.controller;

import android.app.Activity;
import android.content.Intent;

import com.luckcheese.yuca.activity.WebViewActivity;
import com.luckcheese.yuca.model.Product;

public class FlowController {

    public void openProductDetails(Activity activity, Product product) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra(WebViewActivity.INTENT_PAGE_TITLE, product.getTitle());
        intent.putExtra(WebViewActivity.INTENT_PAGE_URL, product.getLink());
        activity.startActivity(intent);
    }
}
