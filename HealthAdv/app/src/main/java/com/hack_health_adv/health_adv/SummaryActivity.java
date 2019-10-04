package com.hack_health_adv.health_adv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    Button mysecondbutton;
    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Spinner myspinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(SummaryActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        myspinner.setAdapter(myadapter);

        mysecondbutton= (Button)findViewById(R.id.button);

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://app.powerbi.com/view?r=eyJrIjoiNmMwNmIyZmMtZDg0Yi00N2E0LThmZWUtMzIwYzQ3YTc1NGQzIiwidCI6IjJlZGRjMzljLTI5OTYtNGMyYS1hYjk3LWY3NjdjMzllYTE1NSIsImMiOjF9");


        mysecondbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent int1=new Intent("android.intent.action.VIEW", Uri.parse("https://app.powerbi.com/view?r=eyJrIjoiMzllM2JlYmQtNDc0ZS00YzdjLWJmZGItNjZmYTY2Yjk2NDgwIiwidCI6IjJlZGRjMzljLTI5OTYtNGMyYS1hYjk3LWY3NjdjMzllYTE1NSIsImMiOjF9"));
                //startActivity(int1);
                //Toast.makeText(SummaryActivity.this,"Hello",Toast.LENGTH_SHORT).show();
                Intent dashboardActivity = new Intent(SummaryActivity.this,DashboardActivity.class);
                startActivity(dashboardActivity);

            }
        });

    }
}
