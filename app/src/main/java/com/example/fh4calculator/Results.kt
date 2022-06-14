package com.example.fh4calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_results.*
import kotlin.math.abs
import kotlin.math.roundToLong

class Results : AppCompatActivity() {

    var selectedButton = 0

    // Max and min values
    var springsMaxValue = 0.0
    var springsMinValue = 0.0

    var barsMaxValue = 65.0
    var barsMinValue = 1.0

    var dampingMaxValue = 20.0
    var dampingMinValue = 3.0

    // Final values
    var frontSpringsValue = 0.0
    var rearSpringsValue = 0.0

    var frontBarsValue = 0.0
    var rearBarsValue = 0.0

    var frontDampingValue = 0.0
    var rearDampingValue = 0.0

    var frontWeight = 0.0
    var rearWeight = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        // Checking info and assigning values
        checkIntentInfo()

        // Calculating values...
        calculateValues()

        // Filling fields with the results
        fillFields()
    }

    private fun checkIntentInfo() {
        if (intent.extras != null) {
            selectedButton = intent.getIntExtra("button", 0)
            springsMaxValue = intent.getDoubleExtra("springsMax", 0.0)
            springsMinValue = intent.getDoubleExtra("springsMin", 0.0)
            dampingMaxValue = intent.getDoubleExtra("dampingMax", 20.0)
            dampingMinValue = intent.getDoubleExtra("dampingMin", 3.0)
            frontWeight = intent.getDoubleExtra("frontWeight", 0.0)
        }
    }

    private fun fillFields() {
        fillSprings()
        fillBars()
        fillDamping()
    }

    private fun fillSprings() {
        tvFrontSpringsValue.text = frontSpringsValue.toString()
        tvRearSpringsValue.text = rearSpringsValue.toString()
    }

    private fun fillBars() {
        tvFrontBarsValue.text = frontBarsValue.toString()
        tvRearBarsValue.text = rearBarsValue.toString()
    }

    private fun fillDamping() {
        tvFrontDampingValue.text =  String.format("%.2f", frontDampingValue)
        tvRearDampingValue.text =  String.format("%.2f", rearDampingValue)
    }

    private fun calculateValues() {
        calculateRearWeight()
        calculateFrontWeight()
        calculateBars()
        calculateSprings()
        calculateDamping()
    }

    private fun calculateRearWeight() {
        rearWeight = (frontWeight - 100)
        rearWeight = abs(rearWeight) / 100
    }

    private fun calculateFrontWeight() {
        frontWeight /= 100
    }

    private fun calculateBars() {
        frontBarsValue = formula(barsMaxValue, barsMinValue, frontWeight)
        rearBarsValue = formula(barsMaxValue, barsMinValue, rearWeight)
    }

    private fun calculateSprings() {
        frontSpringsValue = formula(springsMaxValue, springsMinValue, frontWeight)
        rearSpringsValue = formula(springsMaxValue, springsMinValue, rearWeight)
    }

    private fun calculateDamping() {
        frontDampingValue = formula(dampingMaxValue, dampingMinValue, frontWeight)
        rearDampingValue = formula(dampingMaxValue, dampingMinValue, rearWeight)
    }

    private fun formula(maxValue: Double, minValue: Double, weight: Double): Double {
        return (maxValue - minValue) * weight + minValue
    }
}