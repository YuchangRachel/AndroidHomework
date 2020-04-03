package com.example.phoebee.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

/*
Implement click listeners for the RecyclerView's items so that,
when clicked, a browser is opened to the url for that news news_item.
 */
public class WebActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_web.xml
        setContentView(R.layout.activity_web);

        webView = (WebView)findViewById(R.id.webview);

        Intent intent = getIntent();
        if(intent.hasExtra(Intent.EXTRA_TEXT)){
            String urlStrig = intent.getStringExtra(Intent.EXTRA_TEXT);
            webView.loadUrl(urlStrig);
        }

    }

}
