package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutTheGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_the_game);

        WebView mWebView = findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://memorygame711796649.wordpress.com/about-the-game/");


    }
}