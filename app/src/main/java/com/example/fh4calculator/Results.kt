package com.example.fh4calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_results.*
import kotlin.math.abs

class Results : AppCompatActivity() {

    var selectedButton = 0

    // Max and min values
    var springsMaxValue = 0.0
    var springsMinValue = 0.0

    var barsMaxValue = 65.0
    var barsMinValue = 1.0

    var reboundMaxValue = 20.0
    var reboundMinValue = 3.0

    // Final values
    var frontBarsValue = 0.0
    var rearBarsValue = 0.0

    var frontSpringsValue = 0.0
    var rearSpringsValue = 0.0

    var frontReboundValue = 0.0
    var rearReboundValue = 0.0

    var frontBumpValue = 0.0
    var rearBumpValue = 0.0

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
            reboundMaxValue = intent.getDoubleExtra("reboundMax", 20.0)
            reboundMinValue = intent.getDoubleExtra("reboundMin", 3.0)
            frontWeight = intent.getDoubleExtra("frontWeight", 0.0)
        }
    }

    private fun fillFields() {
        fillBars()
        fillSprings()
        fillDamping()
        fillBump()
    }

    private fun fillBars() {
        tvFrontBarsValue.text = String.format("%.2f", frontBarsValue)
        tvRearBarsValue.text = String.format("%.2f", rearBarsValue)
    }

    private fun fillSprings() {
        tvFrontSpringsValue.text = String.format("%.1f", frontSpringsValue)
        tvRearSpringsValue.text = String.format("%.1f", rearSpringsValue)
    }

    private fun fillDamping() {
        tvFrontDampingValue.text =  String.format("%.1f", frontReboundValue)
        tvRearDampingValue.text =  String.format("%.1f", rearReboundValue)
    }

    private fun fillBump() {
        tvFrontBumpValue.text = String.format("%.1f", frontBumpValue)
        tvRearBumpValue.text = String.format("%.1f", rearBumpValue)
    }

    private fun calculateValues() {
        calculateRearWeight()
        calculateFrontWeight()
        calculateBars()
        calculateSprings()
        calculateDamping()
        calculateBump()
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
        frontReboundValue = formula(reboundMaxValue, reboundMinValue, frontWeight)
        rearReboundValue = formula(reboundMaxValue, reboundMinValue, rearWeight)
    }

    private fun calculateBump(){
        frontBumpValue = frontReboundValue * 60 / 100
        rearBumpValue = rearReboundValue * 60 / 100
    }

    private fun formula(maxValue: Double, minValue: Double, weight: Double): Double {
        return (maxValue - minValue) * weight + minValue
    }
}