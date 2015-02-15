package me.nizheg.en.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import me.nizheg.en.R;
import me.nizheg.en.service.SimpleBrailleService;

public class MainActivity extends Activity {

    private SimpleBrailleService simpleBrailleService = new SimpleBrailleService();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculateAndDisplayBraille();
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openBrowserActivity() {
        Intent intent = new Intent(this, BrowserActivity.class);
        startActivity(intent);
    }

    public void onToggleClicked(View view) {
        calculateAndDisplayBraille();
    }

    private void calculateAndDisplayBraille() {
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
}
