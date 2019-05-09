package com.example.onetwothree.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.onetwothree.home.HomeActivity
import com.example.onetwothree.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            run {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }, 2000)
    }
}
