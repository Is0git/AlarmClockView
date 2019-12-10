package com.example.clockcustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var clock: ClockView
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.startButton)
        clock = findViewById(R.id.clock)
        clock.apply {
            totalTimeMs = 1500
            currentTimeMs = 25000
        }
        button.setOnClickListener(this)
        Thread(Runnable {
            Handler()
        })
    }

    override fun onClick(v: View?) {
        clock.startClock()
    }



}
