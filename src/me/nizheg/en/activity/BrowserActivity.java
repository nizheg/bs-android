package me.nizheg.en.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import me.nizheg.en.R;

public class BrowserActivity extends SubActivity {

    public static final String MY_HOST = "en-nizheg.rhcloud.com";
    WebView webView;
    EditText urlView;
    Button refreshStopButton;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        initializeWebView();
        initializeUrlView();
        refreshStopButton = (Button) findViewById(R.id.refreshStopButton);
        refreshStopButton.setTag(Boolean.FALSE);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openUrl();
    }

    private void initializeWebView() {
        this.webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
    }

    private void initializeUrlView() {
        this.urlView = (EditText) findViewById(R.id.editText);
        urlView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    openUrl();
                    return true;
                }
                return false;
            }
        });
        urlView.setText(MY_HOST);
        openUrl();
    }

    public void onStartStop(View view) {
        if (Boolean.TRUE.equals(refreshStopButton.getTag())) {
            webView.stopLoading();
        } else {
            webView.reload();
        }
    }

    public void onBack(View view) {
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }

    public void onForward(View view) {
        if (webView.canGoForward()) {
            webView.goForward();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void openUrl() {
        String url = urlView.getText().toString().trim();
        if (url.length() == 0) {
            return;
        }
        if (!url.startsWith("http://")) {
            url = "http://" + url;
            urlView.setText(url);
        }
        webView.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpAuthRequest(WebView view,
                                              HttpAuthHandler handler, String host, String realm) {
            if (host.equals(MY_HOST + ":80") || host.equals(MY_HOST)) {
                handler.proceed("nizheg", "12345");
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            boolean result = super.shouldOverrideUrlLoading(view, url);
            urlView.setText(url);
            return result;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        public void onProgressChanged(WebView view, int progress) {

            if (progress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                refreshStopButton
                        .setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_delete));
                refreshStopButton.setTag(Boolean.TRUE);
            }
            progressBar.setProgress(progress);
            if (progress == 100) {
                progressBar.setVisibility(ProgressBar.GONE);
                refreshStopButton
                        .setBackgroundDrawable(getResources().getDrawable(android.R.drawable.ic_menu_rotate));
                refreshStopButton.setTag(Boolean.FALSE);
            }
        }
    }
}