package com.hack_health_adv.health_adv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DashboardActivity extends AppCompatActivity {

    private WebView webView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("https://app.powerbi.com/view?r=eyJrIjoiMzllM2JlYmQtNDc0ZS00YzdjLWJmZGItNjZmYTY2Yjk2NDgwIiwidCI6IjJlZGRjMzljLTI5OTYtNGMyYS1hYjk3LWY3NjdjMzllYTE1NSIsImMiOjF9");
        webView.loadUrl("https://app.powerbi.com/view?r=eyJrIjoiZGEyYTI5NWYtYjhiMy00ZWE3LTkwODctNTZkYjU0OWIxZDQ1IiwidCI6IjJlZGRjMzljLTI5OTYtNGMyYS1hYjk3LWY3NjdjMzllYTE1NSIsImMiOjF9");
    }
}
