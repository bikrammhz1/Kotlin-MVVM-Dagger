package com.rbmhz.actwtk.view.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.rbmhz.actwtk.R
import com.rbmhz.actwtk.view.activity.login.Login
import com.rbmhz.actwtk.view.activity.main.MainActivity

/**
 * Created by Bikram Maharjan on 6/17/2020.
 */

class Splash : AppCompatActivity() {

    private val out: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        }, out)
    }
}