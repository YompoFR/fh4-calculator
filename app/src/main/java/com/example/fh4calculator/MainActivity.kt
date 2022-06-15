package com.example.fh4calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent()
        rdSuspension.setOnClickListener(){
            intent = Intent(this, Suspension::class.java)
            startActivity(intent)
        }

        rdGearing.setOnClickListener(){
            intent = Intent(this, Gearing::class.java)
            startActivity(intent)
        }
    }
}