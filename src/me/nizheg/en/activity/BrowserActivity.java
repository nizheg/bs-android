package me.nizheg.en.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import me.nizheg.en.R;

public class BrowserActivity extends SubActivity {

    WebView webView;
    EditText urlView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        initializeWebView();
        initializeUrlView();
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
        urlView.setText("http://en-nizheg.rhcloud.com/");
        openUrl();
    }

    public void onGoClicked(View view) {
        openUrl();
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

    private static class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpAuthRequest(WebView view,
                                              HttpAuthHandler handler, String host, String realm) {
            handler.proceed("nizheg", "12345");
        }
    }
}