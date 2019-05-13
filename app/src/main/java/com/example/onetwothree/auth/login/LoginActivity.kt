package com.example.onetwothree.auth.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.onetwothree.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportFragmentManager?.beginTransaction()?.add(R.id.container, LoginFrag())?.commit()
    }
}
