package com.example.exchangeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val leftWatcher = ExchangeWatcher(leftValueEditText)
        val rightWatcher = ExchangeWatcher(rightValueEditText)
        leftWatcher.oppositeWatcher = rightWatcher
        rightWatcher.oppositeWatcher = leftWatcher
        leftValueEditText.addTextChangedListener(leftWatcher)
        rightValueEditText.addTextChangedListener(rightWatcher)

        val coursesMap: Map<String, Double> = mapOf("USD" to 1.0, "EUR" to 1.1192, "RUB" to 0.015,
            "JPY" to 0.0092, "CNY" to 0.14, "INR" to 0.013)
        val currencyCodesArray = coursesMap.keys.toTypedArray()

        val currencyCodesAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencyCodesArray)
        currencyCodesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        leftValuesSpinner.adapter = currencyCodesAdapter
        rightValuesSpinner.adapter = currencyCodesAdapter

        leftValuesSpinner.onItemSelectedListener = ExchageSpinnerListner(leftWatcher, coursesMap)
        rightValuesSpinner.onItemSelectedListener = ExchageSpinnerListner(rightWatcher, coursesMap)
    }
}
