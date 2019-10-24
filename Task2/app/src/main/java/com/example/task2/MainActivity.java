package com.example.task2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private double height = 0.0;
    private double weight = 0.0;
    private int age = 0;

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private TextView bmiResultTextView;
    private TextView bmiInfoTextView;
    private TextView recommendationDietTextView;
    private TextView ppmResultTextView;
    private ImageView dietImageView;
    private RadioButton genderManRadioButton;
    private ConstraintLayout homeLayout;
    private ConstraintLayout calculatorLayout;
    private ConstraintLayout recommendationLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    homeLayout.setVisibility(View.VISIBLE);
                    calculatorLayout.setVisibility(View.GONE);
                    recommendationLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_calculator:
                    homeLayout.setVisibility(View.GONE);
                    calculatorLayout.setVisibility(View.VISIBLE);
                    recommendationLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_recommendation:
                    homeLayout.setVisibility(View.GONE);
                    calculatorLayout.setVisibility(View.GONE);
                    recommendationLayout.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeLayout = findViewById(R.id.home_layout);
        calculatorLayout = findViewById(R.id.bmi_calculator_layout);
        recommendationLayout = findViewById(R.id.ppm_recommendation_layout);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);
        bmiInfoTextView = findViewById(R.id.bmiInfoTextView);
        ppmResultTextView = findViewById(R.id.ppmResultTextView);
        dietImageView = findViewById(R.id.dietImageView);
        genderManRadioButton = findViewById(R.id.genderManRadioButton);
        RadioGroup genderRadioGroup = findViewById(R.id.genderRadioGroup);
        recommendationDietTextView = findViewById(R.id.recommendationDietTextView);
        EditText heightEditText = findViewById(R.id.heightEditText);
        EditText weightEditText = findViewById(R.id.weightEditText);
        EditText ageEditText = findViewById(R.id.ageEditText);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        bmiResultTextView.setText("");
        bmiInfoTextView.setText("");
        ppmResultTextView.setText("");
        recommendationDietTextView.setText("");
        dietImageView.setVisibility(View.INVISIBLE);
        genderManRadioButton.toggle();
        heightEditText.addTextChangedListener(heightEditTextWatcher);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
        ageEditText.addTextChangedListener(ageEditTextWatcher);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        homeLayout.setVisibility(View.VISIBLE);
        calculatorLayout.setVisibility(View.GONE);
        recommendationLayout.setVisibility(View.GONE);
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculatePPM();
            }
        });
    }

    private void calculateBMI() {
        if (weight == 0 || height == 0) {
            bmiResultTextView.setText("");
            bmiInfoTextView.setText("");
        } else {
            double bmi = weight / (height*height);
            bmiResultTextView.setText(decimalFormat.format(bmi));
            if (bmi < 16) {
                bmiInfoTextView.setText(R.string.bmi_starvation);
            } else if (bmi >= 16 && bmi <= 16.99) {
                bmiInfoTextView.setText(R.string.bmi_emaciation);
            } else if (bmi >= 17 && bmi <= 18.49) {
                bmiInfoTextView.setText(R.string.bmi_underweight);
            } else if (bmi >= 18.5 && bmi <= 24.99) {
                bmiInfoTextView.setText(R.string.bmi_correct_value);
            } else if (bmi >= 25 && bmi <= 29.99) {
                bmiInfoTextView.setText(R.string.bmi_overweight);
            } else if (bmi >= 30 && bmi <= 34.99) {
                bmiInfoTextView.setText(R.string.bmi_first_degree_obesity);
            } else if (bmi >= 35 && bmi <= 39.99) {
                bmiInfoTextView.setText(R.string.bmi_second_degree_obesity);
            } else if (bmi >= 40) {
                bmiInfoTextView.setText(R.string.bmi_extreme_obesity);
            }
        }
    }

    private void calculatePPM() {
        if (weight == 0 || height == 0 || age == 0) {
            recommendationDietTextView.setText("");
            ppmResultTextView.setText("");
            dietImageView.setVisibility(View.INVISIBLE);
        } else {
            double bmi = weight / (height*height);
            double ppm;
            if (genderManRadioButton.isChecked()) {
                ppm = (10 * weight) + (6.25 * height) - (5 * age) + 5;
            } else {
                ppm = (10 * weight) + (6.25 * height) - (5 * age) - 161;
            }
            recommendationDietTextView.setText(R.string.recommendation_diet);
            ppmResultTextView.setText(getApplicationContext().getString(R.string.ppm_result, decimalFormat.format(ppm)));
            if (bmi < 18.5) {
                dietImageView.setVisibility(View.VISIBLE);
                dietImageView.setImageResource(R.drawable.bmi_underweight);
            } else if (bmi >= 18.5 && bmi <= 24.99) {
                dietImageView.setVisibility(View.VISIBLE);
                dietImageView.setImageResource(R.drawable.bmi_correct_value);
            } else if (bmi > 24.99 && bmi <= 29.99) {
                dietImageView.setVisibility(View.VISIBLE);
                dietImageView.setImageResource(R.drawable.bmi_overweight);
            } else if (bmi > 30) {
                dietImageView.setVisibility(View.VISIBLE);
                dietImageView.setImageResource(R.drawable.bmi_obesity);
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
            calculateBMI();
            calculatePPM();
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
            calculateBMI();
            calculatePPM();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    };

    private final TextWatcher ageEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                age = Integer.parseInt(s.toString());
            }
            catch (NumberFormatException e) {
                recommendationDietTextView.setText("");
                ppmResultTextView.setText("");
                dietImageView.setVisibility(View.INVISIBLE);
                age = 0;
            }
            calculatePPM();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
    };
}