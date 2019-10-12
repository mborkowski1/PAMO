package com.example.pamo.task1;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity {
    private double height = 0.0;
    private double weight = 0.0;

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private TextView bmiResultTextView;
    private TextView bmiInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmiResultTextView = findViewById(R.id.bmiResultTextView);
        bmiInfoTextView = findViewById(R.id.bmiInfoTextView);
        bmiResultTextView.setText("");
        bmiInfoTextView.setText("");

        EditText heightEditText = findViewById(R.id.heightEditText);
        EditText weightEditText = findViewById(R.id.weightEditText);
        heightEditText.addTextChangedListener(heightEditTextWatcher);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
    }

    private void calculate() {
        if (weight == 0 || height == 0) {
            bmiResultTextView.setText("");
            bmiInfoTextView.setText("");
        } else {
            double result = weight / (height*height);
            bmiResultTextView.setText(String.valueOf(decimalFormat.format(result)));
            if (result < 16) {
                bmiInfoTextView.setText(R.string.bmi_starvation);
            } else if (result >= 16 && result <= 16.99) {
                bmiInfoTextView.setText(R.string.bmi_emaciation);
            } else if (result >= 17 && result <= 18.49) {
                bmiInfoTextView.setText(R.string.bmi_underweight);
            } else if (result >= 18.5 && result <= 24.99) {
                bmiInfoTextView.setText(R.string.bmi_correct_value);
            } else if (result >= 25 && result <= 29.99) {
                bmiInfoTextView.setText(R.string.bmi_overweight);
            } else if (result >= 30 && result <= 34.99) {
                bmiInfoTextView.setText(R.string.bmi_first_degree_obesity);
            } else if (result >= 35 && result <= 39.99) {
                bmiInfoTextView.setText(R.string.bmi_second_degree_obesity);
            } else if (result >= 40) {
                bmiInfoTextView.setText(R.string.bmi_extreme_obesity);
            }
        }
    }

    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                height = Double.parseDouble(s.toString()) / 100.0;
            }
            catch (NumberFormatException e) {
                bmiResultTextView.setText("");
                bmiInfoTextView.setText("");
                height = 0.0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    };

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                weight = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e) {
                bmiResultTextView.setText("");
                bmiInfoTextView.setText("");
                weight = 0.0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    };
}
