@file:Suppress("DEPRECATION")

package com.example.jeniskucing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed(Runnable { //This method will be executed once the timer is over
            val startSplash = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(startSplash)
            finish()
        }, 3000)
    }


}