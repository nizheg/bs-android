package me.nizheg.en.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import me.nizheg.en.R;
import me.nizheg.en.service.SimpleMorseService;

public class MorseActivity extends SubActivity {

    private EditText inputView;
    private TextView resultView;
    private SimpleMorseService morseService = new SimpleMorseService();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.morse);

        inputView = (EditText) findViewById(R.id.morseInputView);
        initializeResultView();
        initializeSignalView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculate();
    }

    private void initializeResultView() {
        resultView = (TextView) findViewById(R.id.morseResultView);
        resultView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                insertTextInInput(" ");
            }
        });
        resultView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                inputView.setText("");
                resultView.setText("");
                return true;
            }
        });
    }

    private void initializeSignalView() {
        View signalView = findViewById(R.id.signalView);
        signalView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                insertTextInInput("-");
                return true;
            }
        });
        signalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTextInInput("*");
            }
        });
    }

    private void insertTextInInput(String text) {
        int start = Math.max(inputView.getSelectionStart(), 0);
        int end = Math.max(inputView.getSelectionEnd(), 0);
        inputView.getText().replace(Math.min(start, end), Math.max(start, end), text, 0, text.length());
    }

    private void calculate() {
        String input = inputView.getText().toString();
        String result = morseService.parseRussianString(input) +
                " / " +
                morseService.parseEnglishString(input);
        resultView.setText(result);
    }
}