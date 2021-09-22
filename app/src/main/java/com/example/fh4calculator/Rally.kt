package com.example.fh4calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rally.*
import java.lang.NumberFormatException

class Rally : AppCompatActivity() {

    var selectedButton = 0

    var springsMaxValue = 0.0
    var springsMinValue = 0.0

    var dampingMaxValue = 0.0
    var dampingMinValue = 0.0

    var frontWeight = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rally)

        // Checking info and assigning values
        checkIntentInfo()

        btnCalculateRally.setOnClickListener() {
            try {
                assignValues()
                cleanFields()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this,
                    this.getString(R.string.toast_fill_fields),
                    Toast.LENGTH_SHORT
                ).show()
                cleanFields()
            }
        }

    }

    private fun checkIntentInfo() {
        if (intent.extras != null) {
            selectedButton = intent.getIntExtra("button", 0)
            springsMaxValue = intent.getDoubleExtra("springsMax", 0.0)
            springsMinValue = intent.getDoubleExtra("springsMin", 0.0)
            frontWeight = intent.getDoubleExtra("frontWeight", 0.0)
        }
    }

    private fun assignValues() {
        dampingMaxValue = edtDampingMaxValue.text.toString().toDouble()
        dampingMinValue = edtDampingMinValue.text.toString().toDouble()

        sendValues()
    }

    private fun sendValues() {
        if (dampingMaxValue != 0.0 && dampingMinValue != 0.0) {
            var intent = Intent(this, Results::class.java)
            intent.putExtra("button", selectedButton)
            intent.putExtra("springsMax", springsMaxValue)
            intent.putExtra("springsMin", springsMinValue)
            intent.putExtra("dampingMax", dampingMaxValue)
            intent.putExtra("dampingMin", dampingMinValue)
            intent.putExtra("frontWeight", frontWeight)
            startActivity(intent)
        }
    }

    private fun cleanFields() {
        edtDampingMaxValue.text.clear()
        edtDampingMinValue.text.clear()
    }
}