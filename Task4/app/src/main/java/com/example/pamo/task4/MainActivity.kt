package com.example.pamo.task4

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_bmi_calculator.*
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ppm_recommendation.*
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quizzes.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var height = 0.0
    private var weight = 0.0
    private var age = 0

    private val decimalFormat = DecimalFormat("#.##")
    private var quizAboutFoodScore = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                home_layout!!.visibility = View.VISIBLE
                bmi_calculator_layout!!.visibility = View.GONE
                ppm_recommendation_layout!!.visibility = View.GONE
                chart_layout!!.visibility = View.GONE
                webview!!.visibility = View.GONE
                quizzes_layout!!.visibility = View.GONE
                quiz_layout!!.visibility = View.GONE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_calculator -> {
                home_layout!!.visibility = View.GONE
                bmi_calculator_layout!!.visibility = View.VISIBLE
                ppm_recommendation_layout!!.visibility = View.GONE
                chart_layout!!.visibility = View.GONE
                webview!!.visibility = View.GONE
                quizzes_layout!!.visibility = View.GONE
                quiz_layout!!.visibility = View.GONE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_recommendation -> {
                home_layout!!.visibility = View.GONE
                bmi_calculator_layout!!.visibility = View.GONE
                ppm_recommendation_layout!!.visibility = View.VISIBLE
                chart_layout!!.visibility = View.GONE
                webview!!.visibility = View.GONE
                quizzes_layout!!.visibility = View.GONE
                quiz_layout!!.visibility = View.GONE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_chart -> {
                home_layout!!.visibility = View.GONE
                bmi_calculator_layout!!.visibility = View.GONE
                ppm_recommendation_layout!!.visibility = View.GONE
                chart_layout!!.visibility = View.VISIBLE
                webview!!.visibility = View.VISIBLE
                quizzes_layout!!.visibility = View.GONE
                quiz_layout!!.visibility = View.GONE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_quiz -> {
                home_layout!!.visibility = View.GONE
                bmi_calculator_layout!!.visibility = View.GONE
                ppm_recommendation_layout!!.visibility = View.GONE
                chart_layout!!.visibility = View.GONE
                webview!!.visibility = View.GONE
                quizzes_layout!!.visibility = View.VISIBLE
                quiz_layout!!.visibility = View.GONE
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val heightEditTextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                height = java.lang.Double.parseDouble(s.toString()) / 100.0
            } catch (e: NumberFormatException) {
                bmiResultTextView!!.text = ""
                bmiInfoTextView!!.text = ""
                height = 0.0
            }

            calculateBMI()
            calculatePPM()
        }

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }

    private val weightEditTextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                weight = java.lang.Double.parseDouble(s.toString())
            } catch (e: NumberFormatException) {
                bmiResultTextView!!.text = ""
                bmiInfoTextView!!.text = ""
                weight = 0.0
            }

            calculateBMI()
            calculatePPM()
        }

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }

    private val ageEditTextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                age = Integer.parseInt(s.toString())
            } catch (e: NumberFormatException) {
                recommendationDietTextView!!.text = ""
                ppmResultTextView!!.text = ""
                dietImageView!!.visibility = View.INVISIBLE
                age = 0
            }

            calculatePPM()
        }

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bmiResultTextView!!.text = ""
        bmiInfoTextView!!.text = ""
        ppmResultTextView!!.text = ""
        recommendationDietTextView!!.text = ""
        foodQuizResultTextView!!.text = applicationContext.getString(R.string.food_quiz_start_score, 0.toString())
        dietImageView!!.visibility = View.INVISIBLE
        genderManRadioButton!!.toggle()
        heightEditText.addTextChangedListener(heightEditTextWatcher)
        weightEditText.addTextChangedListener(weightEditTextWatcher)
        ageEditText.addTextChangedListener(ageEditTextWatcher)
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        home_layout!!.visibility = View.VISIBLE
        bmi_calculator_layout!!.visibility = View.GONE
        ppm_recommendation_layout!!.visibility = View.GONE
        chart_layout!!.visibility = View.GONE
        quizzes_layout!!.visibility = View.GONE
        quiz_layout!!.visibility = View.GONE
        foodQuizButton!!.setOnClickListener {
            quizzes_layout!!.visibility = View.GONE
            quiz_layout!!.visibility = View.VISIBLE
        }
        submitAnswersButton!!.setOnClickListener {
            quizAboutFoodScore = 0

            val selectedRadioButton1Id = question1RadioGroup!!.checkedRadioButtonId
            if (selectedRadioButton1Id != -1) {
                val selectedRadioButton1 = findViewById<RadioButton>(selectedRadioButton1Id)
                val selectedRadioButton1Text = selectedRadioButton1.text.toString()
                if (selectedRadioButton1Text.equals(getString(R.string.question1_correct_answer), ignoreCase = true)) {
                    quizAboutFoodScore++
                }
            }

            val selectedRadioButton2Id = question2RadioGroup!!.checkedRadioButtonId
            if (selectedRadioButton2Id != -1) {
                val selectedRadioButton2 = findViewById<RadioButton>(selectedRadioButton2Id)
                val selectedRadioButton2Text = selectedRadioButton2.text.toString()
                if (selectedRadioButton2Text.equals(getString(R.string.question2_correct_answer), ignoreCase = true)) {
                    quizAboutFoodScore++
                }
            }

            val selectedRadioButton3Id = question3RadioGroup!!.checkedRadioButtonId
            if (selectedRadioButton3Id != -1) {
                val selectedRadioButton3 = findViewById<RadioButton>(selectedRadioButton3Id)
                val selectedRadioButton3Text = selectedRadioButton3.text.toString()
                if (selectedRadioButton3Text.equals(getString(R.string.question3_correct_answer), ignoreCase = true)) {
                    quizAboutFoodScore++
                }
            }

            val selectedRadioButton4Id = question4RadioGroup!!.checkedRadioButtonId
            if (selectedRadioButton4Id != -1) {
                val selectedRadioButton4 = findViewById<RadioButton>(selectedRadioButton4Id)
                val selectedRadioButton4Text = selectedRadioButton4.text.toString()
                if (selectedRadioButton4Text.equals(getString(R.string.question4_correct_answer), ignoreCase = true)) {
                    quizAboutFoodScore++
                }
            }

            val selectedRadioButton5Id = question5RadioGroup!!.checkedRadioButtonId
            if (selectedRadioButton5Id != -1) {
                val selectedRadioButton5 = findViewById<RadioButton>(selectedRadioButton5Id)
                val selectedRadioButton5Text = selectedRadioButton5.text.toString()
                if (selectedRadioButton5Text.equals(getString(R.string.question5_correct_answer), ignoreCase = true)) {
                    quizAboutFoodScore++
                }
            }

            quizzes_layout!!.visibility = View.VISIBLE
            quiz_layout!!.visibility = View.GONE
            foodQuizResultTextView!!.text = applicationContext.getString(R.string.food_quiz_start_score, quizAboutFoodScore.toString())
            question1RadioGroup!!.clearCheck()
            question2RadioGroup!!.clearCheck()
            question3RadioGroup!!.clearCheck()
            question4RadioGroup!!.clearCheck()
            question5RadioGroup!!.clearCheck()
        }
        genderRadioGroup.setOnCheckedChangeListener { _, _ -> calculatePPM() }
        val webSettings = webview!!.settings
        webSettings.javaScriptEnabled = true
        val htmlData = ("<html>"
                + "  <head>"
                + "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                + "    <script type=\"text/javascript\">"
                + "      google.charts.load('current', {'packages':['corechart']});"
                + "      google.charts.setOnLoadCallback(drawChart);"

                + "      function drawChart() {"

                + "        var data = google.visualization.arrayToDataTable(["
                + "          ['Month', 'Weight'],"
                + "          ['Jan', 80],"
                + "          ['Feb', 77],"
                + "          ['Mar', 75],"
                + "          ['Apr', 79],"
                + "          ['Aug', 82],"
                + "          ['Sep', 84],"
                + "          ['Oct', 80],"
                + "          ['Nov', 74],"
                + "          ['Dec',  71],"
                + "        ]);"

                + "        var options = {"
                + "          title: 'Your weight in each month',"
                + "          curveType: 'function',"
                + "          legend: { position: 'bottom' }"
                + "        };"

                + "        var chart = new google.visualization.LineChart(document.getElementById('chart'));"
                + "        chart.draw(data, options);"
                + "      }"
                + "    </script>"
                + "  </head>"
                + "  <body>"
                + "    <div id=\"chart\" style=\"width: 900px; height: 500px;\"></div>"
                + "  </body>"
                + "</html>")
        webview!!.loadData(htmlData, "text/html", "UTF-8")
        webview!!.visibility = View.GONE
    }

    private fun calculateBMI() {
        if (weight == 0.0 || height == 0.0) {
            bmiResultTextView!!.text = ""
            bmiInfoTextView!!.text = ""
        } else {
            val bmi = weight / (height * height)
            bmiResultTextView!!.text = decimalFormat.format(bmi)
            when {
                bmi < 16 -> {
                    bmiInfoTextView!!.setText(R.string.bmi_starvation)
                }
                bmi in 16.0..16.99 -> {
                    bmiInfoTextView!!.setText(R.string.bmi_emaciation)
                }
                bmi in 17.0..18.49 -> {
                    bmiInfoTextView!!.setText(R.string.bmi_underweight)
                }
                bmi in 18.5..24.99 -> {
                    bmiInfoTextView!!.setText(R.string.bmi_correct_value)
                }
                bmi in 25.0..29.99 -> {
                    bmiInfoTextView!!.setText(R.string.bmi_overweight)
                }
                bmi in 30.0..34.99 -> {
                    bmiInfoTextView!!.setText(R.string.bmi_first_degree_obesity)
                }
                bmi in 35.0..39.99 -> {
                    bmiInfoTextView!!.setText(R.string.bmi_second_degree_obesity)
                }
                bmi >= 40 -> {
                    bmiInfoTextView!!.setText(R.string.bmi_extreme_obesity)
                }
            }
        }
    }

    private fun calculatePPM() {
        if (weight == 0.0 || height == 0.0 || age == 0) {
            recommendationDietTextView!!.text = ""
            ppmResultTextView!!.text = ""
            dietImageView!!.visibility = View.INVISIBLE
        } else {
            val bmi = weight / (height * height)
            val ppm: Double = if (genderManRadioButton!!.isChecked) {
                10 * weight + 6.25 * height - 5 * age + 5
            } else {
                10 * weight + 6.25 * height - (5 * age).toDouble() - 161.0
            }
            recommendationDietTextView!!.setText(R.string.recommendation_diet)
            ppmResultTextView!!.text = applicationContext.getString(R.string.ppm_result, decimalFormat.format(ppm))
            when {
                bmi < 18.5 -> {
                    dietImageView!!.visibility = View.VISIBLE
                    dietImageView!!.setImageResource(R.drawable.bmi_underweight)
                }
                bmi in 18.5..24.99 -> {
                    dietImageView!!.visibility = View.VISIBLE
                    dietImageView!!.setImageResource(R.drawable.bmi_correct_value)
                }
                bmi in 25.00..29.99 -> {
                    dietImageView!!.visibility = View.VISIBLE
                    dietImageView!!.setImageResource(R.drawable.bmi_overweight)
                }
                bmi >= 30 -> {
                    dietImageView!!.visibility = View.VISIBLE
                    dietImageView!!.setImageResource(R.drawable.bmi_obesity)
                }
            }
        }
    }
}
