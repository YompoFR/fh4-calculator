package com.example.fh4calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_gearing.*
import java.lang.NumberFormatException
import kotlin.math.log
import kotlin.math.max
import kotlin.math.roundToInt

class Gearing : AppCompatActivity() {

    // Static Values
    var tireWidth = 225.0 //in mm
    var aspectRatio = 50.0 //dot know what it is
    var wheelDiameter = 15.0 //in inches

    // Variable Values
    var maxRpm = 0
    var calulatedDiameter = 0.0
    var maxSpeed = 0.0 // in miles per hour

    var finalDriveRatio = 0.0
    //var gearRatio = 3.23

    var maxSpeedKmh: Double = 0.0

    var numberOfGears: Int = 5

    var speedOfGear: Double = 0.0
    private var listSpeedPerGear: MutableList<Int> = ArrayList(0)
    var listRatioPerGear: MutableList<Double> = ArrayList(0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gearing)

        btnCalculateGearing.setOnClickListener() {
            try {
                getValues()
                calculateValues()
                assignValues()
            } catch (e: NumberFormatException){

            }
        }
    }

    private fun getValues(){
        maxRpm = edtMaxRpm.text.toString().toInt()
        numberOfGears = edtNumberOfGears.text.toString().toInt()
        finalDriveRatio = edtFinalDriveRatio.text.toString().toDouble()
    }

    private fun calculateValues(){
        calulatedDiameter = formulaCalculatedDiameter()
        maxSpeed = edtMaxSpeedRatio.text.toString().toInt().toDouble()
        //maxSpeed = example()
        //Log.d("Max Speed", maxSpeed.toString())
        speedOfGear =  (maxSpeed/ numberOfGears).toDouble()
        getSpeedPerGear()
        getRatioPerGear()
    }

    private fun getSpeedPerGear(){
        for (gear in 1 .. numberOfGears){
            var currentSpeed = 0
            currentSpeed = (speedOfGear*gear).toInt()
            listSpeedPerGear.add(gear-1, currentSpeed)
        }
    }

    private fun getRatioPerGear(){
        for (gear in 1 .. numberOfGears){
            var currentSpeed = listSpeedPerGear[gear-1]
            var currentRatio = 0.0
            currentRatio = formulaCalculateGearRatio(currentSpeed)
            listRatioPerGear.add(gear-1, currentRatio)
        }
    }

    private fun assignValues(){
        /*
        tvGear1.text = String.format("%.2f", listRatioPerGear[0])
        tvGear2.text = String.format("%.2f", listRatioPerGear[1])
        tvGear3.text = String.format("%.2f", listRatioPerGear[2])
        tvGear4.text = String.format("%.2f", listRatioPerGear[3])
        tvGear5.text = String.format("%.2f", listRatioPerGear[4])
        tvGear6.text = String.format("%.2f", listRatioPerGear[5])
        */

        /*
        * 1º 30% respecto a la velocidad max
        * 2º 40%
        * 3º 60
        * 4º 75
        * 5  87.5
        * 6  100
        * */

        edtMaxSpeedRatio.setText(maxSpeed.toString())
        tvGear1.text = listSpeedPerGear[0].toString()
        tvGear2.text = listSpeedPerGear[1].toString()
        tvGear3.text = listSpeedPerGear[2].toString()
        tvGear4.text = listSpeedPerGear[3].toString()
        tvGear5.text = listSpeedPerGear[4].toString()
        tvGear6.text = listSpeedPerGear[5].toString()

    }

    private fun formulaCalculateGearRatio(currentSpeed: Int): Double {
        return (maxRpm * calulatedDiameter) / (currentSpeed * finalDriveRatio * 336)
    }

    private fun formulaCalculatedDiameter(): Double {
        return (tireWidth / 25.4) * (aspectRatio / 100) * 2  + wheelDiameter
    }

    /*
    * Original formula for calulating final speed per gear ratio
     (maxRpm * calulatedDiameter) / (finalDriveRatio * gearRatio * 336)
     */
}