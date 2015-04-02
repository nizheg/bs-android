package me.nizheg.en.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
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
        initializeWorkingButtons();
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
                insertSpace();
            }
        });
        resultView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clearResults();
                return true;
            }
        });
    }


    private void initializeSignalView() {
        View signalView = findViewById(R.id.signalView);
        signalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDot();
            }
        });
        signalView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                insertDash();
                return true;
            }
        });
    }

    private void initializeWorkingButtons() {
        Button dotButton = (Button) findViewById(R.id.buttonDot);
        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDot();
            }
        });
        Button dashButton = (Button) findViewById(R.id.buttonDash);
        dashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDash();
            }
        });
        Button backSpaceButton = (Button) findViewById(R.id.buttonBackspace);
        backSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearLastSymbol();
            }
        });
        backSpaceButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clearResults();
                return true;
            }
        });
    }

    private void insertDot() {
        insertTextInInput("*");
    }

    private void insertDash() {
        insertTextInInput("-");
    }

    private void insertSpace() {
        insertTextInInput(" ");
    }

    private void clearResults() {
        inputView.setText("");
        resultView.setText("");
    }

    private void clearLastSymbol() {
        Editable text = inputView.getText();
        if (text.length() > 0) {
            text.delete(text.length() - 1, text.length());
        }
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