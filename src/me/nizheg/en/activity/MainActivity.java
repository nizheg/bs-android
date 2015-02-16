package me.nizheg.en.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import me.nizheg.en.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_browser:
                openBrowserActivity();
                return true;
            case R.id.action_open_braille:
                openBrailleActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openBrowserActivity() {
        Intent intent = new Intent(this, BrowserActivity.class);
        startActivity(intent);
    }

    private void openBrailleActivity() {
        Intent intent = new Intent(this, BrailleActivity.class);
        startActivity(intent);
    }
}
