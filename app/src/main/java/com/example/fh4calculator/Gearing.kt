package com.example.fh4calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_gearing.*
import kotlin.math.max

class Gearing : AppCompatActivity() {

    var finalDriveRatio = 4.71
    var tireWidth = 225.0 //in mm
    var aspectRatio = 50.0 //dot know what it is
    var wheelDiameter = 15.0 //in inches
    var calulatedDiameter = 0.0
    var calculatedGearRatio = 0.0

    var maxRpm = 7000
    var gearRatio = 3.23
    var maxSpeed = 142.2 // in miles per hour
    var maxSpeedKmh = maxSpeed * 1.6

    var numberOfGears: Int = 5

    var speedOfGear: Double = maxSpeedKmh / numberOfGears
    var listSpeedPerGear: MutableList<Int> = ArrayList(0)
    var numnberOfGears = 5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gearing)

        calulatedDiameter = formulaCalculatedDiameter()
        calculatedGearRatio = formulaCalculateGearRatio()

        assingSpeedPerGear()

        tvCalculatedDiameterText.text = listSpeedPerGear[1].toString()

    }

    fun assingSpeedPerGear(){
        for (gear in 1 .. numberOfGears){
            var currentSpeed = 0
            currentSpeed = (speedOfGear*gear).toInt()
            listSpeedPerGear.add(gear-1, currentSpeed)
        }
    }

    fun formulaCalculatedDiameter(): Double {
        return (tireWidth / 25.4) * (aspectRatio / 100) * 2  + wheelDiameter
    }

    /*
    * Original formula for calulating final speed per gear ratio
     (maxRpm * calulatedDiameter) / (finalDriveRatio * gearRatio * 336)
     */

    fun formulaCalculateGearRatio(): Double {
        return (maxRpm * calulatedDiameter) / (maxSpeed * finalDriveRatio * 336)
    }
}