package me.nizheg.en.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import me.nizheg.en.R;

public class BrowserActivity extends SubActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        initializeWebView();
    }

    private void initializeWebView() {
        WebView myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        myWebView.setWebViewClient(new WebViewClient());
    }

    public void onGoClicked(View view) {
        WebView myWebView = (WebView) findViewById(R.id.webView);
        EditText urlView = (EditText) findViewById(R.id.editText);
        myWebView.loadUrl(urlView.getText().toString());
    }
}