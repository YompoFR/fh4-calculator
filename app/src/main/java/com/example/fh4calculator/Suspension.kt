package com.example.fh4calculator

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_suspension.*
import java.lang.NumberFormatException

class Suspension : AppCompatActivity() {

    var previousId = 0
    var selectedId = 0

    var selectedButton = -1

    var springMaxValue = 0.0
    var springMinValue = 0.0
    var frontWeight = 0.0

    var dampingMaxValue = 0.0
    var dampingMinValue = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suspension)

        var defaultColor = resources.getColor(R.color.radio_button_default)

        rdgOption.setOnCheckedChangeListener() { radioGroup: RadioGroup, i: Int ->
            var radioButton: RadioButton

            if (radioGroup.checkedRadioButtonId != -1) {

                selectedId = radioGroup.checkedRadioButtonId

                var index = radioGroup.indexOfChild(findViewById(radioGroup.checkedRadioButtonId))
                selectedButton = index

                if (previousId == 0) {
                    previousId = radioGroup.checkedRadioButtonId
                }

                if (previousId != selectedId) {
                    radioButton = findViewById<RadioButton>(previousId)
                    radioButton.setBackgroundColor(defaultColor)
                    radioButton.setTextColor(Color.WHITE)
                }

                radioButton = findViewById<RadioButton>(selectedId)
                radioButton.setBackgroundColor(Color.CYAN)
                radioButton.setTextColor(Color.BLACK)
                previousId = radioGroup.checkedRadioButtonId

                if(selectedButton == 1){ // Rally button
                    edtDampingMaxValue.visibility = View.VISIBLE
                    edtDampingMaxValue.visibility = View.VISIBLE
                } else {
                    edtDampingMaxValue.visibility = View.INVISIBLE
                    edtDampingMaxValue.visibility = View.INVISIBLE
                }
            }
        }

        btnCalculate.setOnClickListener() {
            if (selectedButton != -1) {
                try {
                    assignValues()
                    cleanFields()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, this.getString(R.string.toast_fill_fields), Toast.LENGTH_SHORT).show()
                    cleanFields()
                }
            } else {
                Toast.makeText(this, this.getString(R.string.toast_select_option), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun assignValues() {
        springMaxValue = edtSpringsMaxValue.text.toString().toDouble()
        springMinValue = edtSpringsMinValue.text.toString().toDouble()
        frontWeight = edtFrontWeight.text.toString().toDouble()
        if (edtDampingMaxValue.isVisible && edtDampingMinValue.isVisible){
            dampingMaxValue = edtDampingMaxValue.text.toString().toDouble()
            dampingMinValue = edtDampingMinValue.text.toString().toDouble()
        }

        sendValues()
    }

    private fun sendValues() {
        if (springMaxValue != 0.0 && springMinValue != 0.0 && frontWeight != 0.0) {
            var intent = Intent()
            intent = Intent(this, Results::class.java)
            intent.putExtra("button", selectedButton)
            intent.putExtra("springsMax", springMaxValue)
            intent.putExtra("springsMin", springMinValue)
            intent.putExtra("dampingMax", dampingMaxValue)
            intent.putExtra("dampingMin", dampingMinValue)
            intent.putExtra("frontWeight", frontWeight)

            startActivity(intent)
        }
    }

    private fun cleanFields() {
        edtSpringsMaxValue.text.clear()
        edtSpringsMinValue.text.clear()
        edtFrontWeight.text.clear()
    }
}