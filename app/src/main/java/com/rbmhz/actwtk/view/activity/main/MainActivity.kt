package com.rbmhz.actwtk.view.activity.main

import com.rbmhz.actwtk.R
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.rbmhz.actwtk.databinding.ActivityMainBinding
import com.rbmhz.actwtk.view.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    MainViewModel::class.java, R.layout.activity_main
) {

    override fun onInitData(viewModel: MainViewModel) {
        super.onInitData(viewModel)
        viewModel.loadDataApiO()
        viewModel.loadDataApiO11()

        viewModel.galleryList.observe(this, Observer { state ->
            LogDebugger("This==>", Gson().toJson(state))
            LogDebugger("This==>", Gson().toJson(state.size))
            LogDebugger("This==>", Gson().toJson(state[0].images))
        })
        viewModel.ooodata.observe(this, Observer { state ->
            LogDebugger("This==>111", Gson().toJson(state));
            LogDebugger("This==>111", state.password.toString());
            LogDebugger("This==>111", Gson().toJson(state.username));
        })
        barchart.animateY(5000)
        barchart.data = viewModel.barData();
    }
}
