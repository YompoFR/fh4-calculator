package com.example.fh4calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_gearing.*

class Gearing : AppCompatActivity() {

    var finalDriveRatio = 4.71
    var tireWidth = 225 //in mm
    var aspectRatio = 50 //dont know what it is
    var wheelDiameter = 15 //in inches
    var calulatedDiameter = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gearing)

        calulatedDiameter = formulaCalculatedDiameter()
        tvCalculatedDiameterText.text = calulatedDiameter.toString()
    }

    fun formulaCalculatedDiameter(): Double {
        return (tireWidth *25.4)*(aspectRatio /100)*2+ wheelDiameter
    }
}