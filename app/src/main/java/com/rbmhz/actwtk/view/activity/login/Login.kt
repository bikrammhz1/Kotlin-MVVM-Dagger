package com.rbmhz.actwtk.view.activity.login

import android.content.Intent
import com.rbmhz.actwtk.R
import com.rbmhz.actwtk.databinding.ActivityLoginBinding
import com.rbmhz.actwtk.view.activity.BaseActivity
import com.rbmhz.actwtk.view.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Bikram Maharjan on 6/18/2020.
 */

class Login : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    LoginViewModel::class.java, R.layout.activity_login
) {

    override fun onInitData(viewModel: LoginViewModel) {
        super.onInitData(viewModel)
        btn_login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

}