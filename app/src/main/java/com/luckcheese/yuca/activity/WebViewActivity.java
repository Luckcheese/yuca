package com.luckcheese.yuca.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.luckcheese.yuca.R;

public class WebViewActivity extends AppCompatActivity {

    public static final String INTENT_PAGE_TITLE = "title";
    public static final String INTENT_PAGE_URL = "url";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent receivedData = getIntent();
        if (receivedData == null) {
            finish();
        }

        final String title = receivedData.getStringExtra("title");
        String url = getIntent().getStringExtra("url");

        webView = findViewById(R.id.webviewContent);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                setProgress(newProgress * 100);
                if (newProgress == 100) {
                    setTitle(title);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
