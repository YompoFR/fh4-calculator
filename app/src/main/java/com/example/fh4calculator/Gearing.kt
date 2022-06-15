package com.example.fh4calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_gearing.*

class Gearing : AppCompatActivity() {

    var finalDriveRatio = 4.71
    var tireWidth = 225.0 //in mm
    var aspectRatio = 50.0 //dont know what it is
    var wheelDiameter = 15.0 //in inches
    var calulatedDiameter = 0.0
    var calculatedGearRatio = 0.0

    var maxRpm = 8000
    var gearRatio = 3.23

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gearing)

        calulatedDiameter = formulaCalculatedDiameter()
        calculatedGearRatio = formulaCalculateGearRatio()

        tvCalculatedDiameterText.text = calculatedGearRatio.toString()

    }

    fun formulaCalculatedDiameter(): Double {
        return (tireWidth / 25.4) * (aspectRatio / 100) * 2  + wheelDiameter
    }

    fun formulaCalculateGearRatio(): Double {
        return (maxRpm * calulatedDiameter) / (finalDriveRatio * 3.23 * 336)
    }
}