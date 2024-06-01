package com.example.itube;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class PlayVideoActivity extends AppCompatActivity {

    private WebView youtubeWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        youtubeWebView = findViewById(R.id.youtubeWebView);

        WebSettings webSettings = youtubeWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        youtubeWebView.setWebViewClient(new WebViewClient());

        String videoUrl = getIntent().getStringExtra("VIDEO_URL");
        if (videoUrl != null) {
            youtubeWebView.loadUrl(videoUrl);
        }
    }
}
