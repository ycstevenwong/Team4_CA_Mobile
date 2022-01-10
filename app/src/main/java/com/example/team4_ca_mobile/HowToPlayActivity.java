package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class HowToPlayActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences currUser;
    Button back;
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        currUser = getSharedPreferences("currUser",MODE_PRIVATE);

        mWebView = findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://memorygame711796649.wordpress.com/about-the-game/");

        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        String username = currUser.getString("username",null);

        if(id == R.id.back){

            Intent intent = new Intent(this,MainMenuActivity.class);
            intent.putExtra("username",username);

            startActivity(intent);
        }
    }
}