package me.nizheg.en;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    private SimpleBrailleService simpleBrailleService = new SimpleBrailleService();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onToggleClicked(View view) {
        // Is the toggle on?

        boolean isChecked1 = ((ToggleButton) findViewById(R.id.toggleButton1)).isChecked();
        boolean isChecked2 = ((ToggleButton) findViewById(R.id.toggleButton2)).isChecked();
        boolean isChecked3 = ((ToggleButton) findViewById(R.id.toggleButton3)).isChecked();
        boolean isChecked4 = ((ToggleButton) findViewById(R.id.toggleButton4)).isChecked();
        boolean isChecked5 = ((ToggleButton) findViewById(R.id.toggleButton5)).isChecked();
        boolean isChecked6 = ((ToggleButton) findViewById(R.id.toggleButton6)).isChecked();

        char[] characterList = simpleBrailleService.getAllPossibleVariants(
                new boolean[]{isChecked1, isChecked2, isChecked3, isChecked4, isChecked5, isChecked6});

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(characterList, 0, 4);
    }

    public void onGoClicked(View view) {
        WebView myWebView = (WebView) findViewById(R.id.webView);
        EditText urlView = (EditText) findViewById(R.id.editText);
        myWebView.loadUrl(urlView.getText().toString());
    }

}
