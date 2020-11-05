package com.rbmhz.actwtk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val toastMessage: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }
    val showDialog: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }
    val logDebugger: MutableLiveData<HashMap<String, String>> by lazy {
        MutableLiveData<HashMap<String, String>>()
    }

    fun toast(message: String?) {
        toastMessage.postValue(message)
    }

    fun showDialog() {
        showDialog.postValue("Show")
    }

    fun hideDialog() {
        showDialog.postValue("Hide")
    }

    fun LogDebugger(tag: String, msg: String) {
        var data: HashMap<String, String> = HashMap()
        data[tag] = msg
        logDebugger.postValue(data)
    }
}