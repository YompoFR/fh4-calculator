package com.example.fh4calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_gearing.*
import java.lang.NumberFormatException
import kotlin.math.max

class Gearing : AppCompatActivity() {

    var finalDriveRatio = 4.71
    var tireWidth = 225.0 //in mm
    var aspectRatio = 50.0 //dot know what it is
    var wheelDiameter = 15.0 //in inches
    var calulatedDiameter = 0.0
    var calculatedGearRatio = 0.0

    var maxRpm = 0
    var gearRatio = 3.23
    var maxSpeed = 0 // in miles per hour
    var maxSpeedKmh = maxSpeed * 1.6

    var numberOfGears: Int = 5

    var speedOfGear: Double = maxSpeedKmh / numberOfGears
    var listSpeedPerGear: MutableList<Int> = ArrayList(0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gearing)

        btnCalculateGearing.setOnClickListener(){
            try {
                maxRpm = edtMaxRpm.text.toString().toInt()
                numberOfGears = edtNumberOfGears.text.toString().toInt()
            } catch (e: NumberFormatException){

            }
        }

        calulatedDiameter = formulaCalculatedDiameter()
        calculatedGearRatio = formulaCalculateGearRatio()

        assingSpeedPerGear()


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