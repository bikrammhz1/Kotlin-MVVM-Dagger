package com.rbmhz.actwtk.view.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rbmhz.actwtk.BR
import com.rbmhz.actwtk.BuildConfig
import com.rbmhz.actwtk.viewmodel.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel>(
    private val viewModelClass: Class<VM>,
    @LayoutRes val layoutRes: Int
) : DaggerAppCompatActivity() {

    private lateinit var progress: ProgressDialog

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: VB
    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        onInitLayout(binding)
        onInitData(viewModel)
    }

    @CallSuper
    open fun onInitLayout(binding: VB) {
    }

    @CallSuper
    open fun onInitData(viewModel: VM) {
        observeBaseViewModel()
        observeShowDialog()
        observeShowLogDebugger()
    }

    private fun observeBaseViewModel() {
        viewModel.toastMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeShowDialog() {
        viewModel.showDialog.observe(this, Observer {
            if (it?.toLowerCase() == "Show"?.toLowerCase()) {
                showDialog()

            } else {
                hideDialog()
            }
        })
    }

    private fun observeShowLogDebugger() {
        viewModel.logDebugger.observe(this, Observer {
            LogDebugger(it.keys.toString(), it.values.toString())
        })
    }

    fun LogDebugger(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun showDialog() {
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()
    }

    fun hideDialog() {
        progress.dismiss();
    }
}