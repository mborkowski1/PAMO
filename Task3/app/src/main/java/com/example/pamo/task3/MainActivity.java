package com.example.pamo.task3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
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
    private ConstraintLayout chartLayout;
    private ConstraintLayout quizzesLayout;
    private ConstraintLayout quizLayout;
    private WebView myWebView;
    private TextView foodQuizResultTextView;
    private Button foodQuizButton;
    private Button submitAnswersButton;
    private RadioGroup question1RadioGroup;
    private RadioGroup question2RadioGroup;
    private RadioGroup question3RadioGroup;
    private RadioGroup question4RadioGroup;
    private RadioGroup question5RadioGroup;
    private int quizAboutFoodScore = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    homeLayout.setVisibility(View.VISIBLE);
                    calculatorLayout.setVisibility(View.GONE);
                    recommendationLayout.setVisibility(View.GONE);
                    chartLayout.setVisibility(View.GONE);
                    myWebView.setVisibility(View.GONE);
                    quizzesLayout.setVisibility(View.GONE);
                    quizLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_calculator:
                    homeLayout.setVisibility(View.GONE);
                    calculatorLayout.setVisibility(View.VISIBLE);
                    recommendationLayout.setVisibility(View.GONE);
                    chartLayout.setVisibility(View.GONE);
                    myWebView.setVisibility(View.GONE);
                    quizzesLayout.setVisibility(View.GONE);
                    quizLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_recommendation:
                    homeLayout.setVisibility(View.GONE);
                    calculatorLayout.setVisibility(View.GONE);
                    recommendationLayout.setVisibility(View.VISIBLE);
                    chartLayout.setVisibility(View.GONE);
                    myWebView.setVisibility(View.GONE);
                    quizzesLayout.setVisibility(View.GONE);
                    quizLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_chart:
                    homeLayout.setVisibility(View.GONE);
                    calculatorLayout.setVisibility(View.GONE);
                    recommendationLayout.setVisibility(View.GONE);
                    chartLayout.setVisibility(View.VISIBLE);
                    myWebView.setVisibility(View.VISIBLE);
                    quizzesLayout.setVisibility(View.GONE);
                    quizLayout.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_quiz:
                    homeLayout.setVisibility(View.GONE);
                    calculatorLayout.setVisibility(View.GONE);
                    recommendationLayout.setVisibility(View.GONE);
                    chartLayout.setVisibility(View.GONE);
                    myWebView.setVisibility(View.GONE);
                    quizzesLayout.setVisibility(View.VISIBLE);
                    quizLayout.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeLayout = findViewById(R.id.home_layout);
        calculatorLayout = findViewById(R.id.bmi_calculator_layout);
        recommendationLayout = findViewById(R.id.ppm_recommendation_layout);
        chartLayout = findViewById(R.id.chart_layout);
        quizzesLayout = findViewById(R.id.quizzes_layout);
        quizLayout = findViewById(R.id.quiz_layout);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);
        bmiInfoTextView = findViewById(R.id.bmiInfoTextView);
        ppmResultTextView = findViewById(R.id.ppmResultTextView);
        dietImageView = findViewById(R.id.dietImageView);
        foodQuizResultTextView = findViewById(R.id.foodQuizResultTextView);
        genderManRadioButton = findViewById(R.id.genderManRadioButton);
        foodQuizButton = findViewById(R.id.foodQuizButton);
        submitAnswersButton = findViewById(R.id.submitAnswersButton);
        question1RadioGroup = findViewById(R.id.question1RadioGroup);
        question2RadioGroup = findViewById(R.id.question2RadioGroup);
        question3RadioGroup = findViewById(R.id.question3RadioGroup);
        question4RadioGroup = findViewById(R.id.question4RadioGroup);
        question5RadioGroup = findViewById(R.id.question5RadioGroup);
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
        foodQuizResultTextView.setText(getApplicationContext().getString(R.string.food_quiz_start_score, String.valueOf(0)));
        dietImageView.setVisibility(View.INVISIBLE);
        genderManRadioButton.toggle();
        heightEditText.addTextChangedListener(heightEditTextWatcher);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
        ageEditText.addTextChangedListener(ageEditTextWatcher);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        homeLayout.setVisibility(View.VISIBLE);
        calculatorLayout.setVisibility(View.GONE);
        recommendationLayout.setVisibility(View.GONE);
        chartLayout.setVisibility(View.GONE);
        quizzesLayout.setVisibility(View.GONE);
        quizLayout.setVisibility(View.GONE);
        foodQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizzesLayout.setVisibility(View.GONE);
                quizLayout.setVisibility(View.VISIBLE);
            }
        });
        submitAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizAboutFoodScore = 0;

                int selectedRadioButton1Id = question1RadioGroup.getCheckedRadioButtonId();
                if (selectedRadioButton1Id != -1) {
                    RadioButton selectedRadioButton1 = findViewById(selectedRadioButton1Id);
                    String selectedRadioButton1Text = selectedRadioButton1.getText().toString();
                    if (selectedRadioButton1Text.equalsIgnoreCase(getString(R.string.question1_correct_answer))) {
                        quizAboutFoodScore++;
                    }
                }

                int selectedRadioButton2Id = question2RadioGroup.getCheckedRadioButtonId();
                if (selectedRadioButton2Id != -1) {
                    RadioButton selectedRadioButton2 = findViewById(selectedRadioButton2Id);
                    String selectedRadioButton2Text = selectedRadioButton2.getText().toString();
                    if (selectedRadioButton2Text.equalsIgnoreCase(getString(R.string.question2_correct_answer))) {
                        quizAboutFoodScore++;
                    }
                }

                int selectedRadioButton3Id = question3RadioGroup.getCheckedRadioButtonId();
                if (selectedRadioButton3Id != -1) {
                    RadioButton selectedRadioButton3 = findViewById(selectedRadioButton3Id);
                    String selectedRadioButton3Text = selectedRadioButton3.getText().toString();
                    if (selectedRadioButton3Text.equalsIgnoreCase(getString(R.string.question3_correct_answer))) {
                        quizAboutFoodScore++;
                    }
                }

                int selectedRadioButton4Id = question4RadioGroup.getCheckedRadioButtonId();
                if (selectedRadioButton4Id != -1) {
                    RadioButton selectedRadioButton4 = findViewById(selectedRadioButton4Id);
                    String selectedRadioButton4Text = selectedRadioButton4.getText().toString();
                    if (selectedRadioButton4Text.equalsIgnoreCase(getString(R.string.question4_correct_answer))) {
                        quizAboutFoodScore++;
                    }
                }

                int selectedRadioButton5Id = question5RadioGroup.getCheckedRadioButtonId();
                if (selectedRadioButton5Id != -1) {
                    RadioButton selectedRadioButton5 = findViewById(selectedRadioButton5Id);
                    String selectedRadioButton5Text = selectedRadioButton5.getText().toString();
                    if (selectedRadioButton5Text.equalsIgnoreCase(getString(R.string.question5_correct_answer))) {
                        quizAboutFoodScore++;
                    }
                }

                quizzesLayout.setVisibility(View.VISIBLE);
                quizLayout.setVisibility(View.GONE);
                foodQuizResultTextView.setText(getApplicationContext().getString(R.string.food_quiz_start_score, String.valueOf(quizAboutFoodScore)));
                question1RadioGroup.clearCheck();
                question2RadioGroup.clearCheck();
                question3RadioGroup.clearCheck();
                question4RadioGroup.clearCheck();
                question5RadioGroup.clearCheck();
            }
        });
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculatePPM();
            }
        });
        myWebView = findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String htmlData = "<html>"
                +"  <head>"
                +"    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                +"    <script type=\"text/javascript\">"
                +"      google.charts.load('current', {'packages':['corechart']});"
                +"      google.charts.setOnLoadCallback(drawChart);"

                +"      function drawChart() {"

                +"        var data = google.visualization.arrayToDataTable(["
                +"          ['Month', 'Weight'],"
                +"          ['Jan', 80],"
                +"          ['Feb', 77],"
                +"          ['Mar', 75],"
                +"          ['Apr', 79],"
                +"          ['Aug', 82],"
                +"          ['Sep', 84],"
                +"          ['Oct', 80],"
                +"          ['Nov', 74],"
                +"          ['Dec',  71],"
                +"        ]);"

                +"        var options = {"
                +"          title: 'Your weight in each month',"
                +"          curveType: 'function',"
                +"          legend: { position: 'bottom' }"
                +"        };"

                +"        var chart = new google.visualization.LineChart(document.getElementById('chart'));"
                +"        chart.draw(data, options);"
                +"      }"
                +"    </script>"
                +"  </head>"
                +"  <body>"
                +"    <div id=\"chart\" style=\"width: 900px; height: 500px;\"></div>"
                +"  </body>"
                +"</html>";
        myWebView.loadData(htmlData, "text/html", "UTF-8");
        myWebView.setVisibility(View.GONE);
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
