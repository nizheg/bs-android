package me.nizheg.en.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import me.nizheg.en.R;
import me.nizheg.en.service.SimpleBrailleService;

public class BrailleActivity extends SubActivity {

    private SimpleBrailleService simpleBrailleService = new SimpleBrailleService();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.braille);
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculateAndDisplayBraille();
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