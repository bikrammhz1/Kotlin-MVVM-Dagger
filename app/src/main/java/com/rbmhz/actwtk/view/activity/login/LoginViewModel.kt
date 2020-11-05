package com.rbmhz.actwtk.view.activity.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rbmhz.actwtk.model.GalleryModel
import com.rbmhz.actwtk.model.State
import com.rbmhz.actwtk.model.User
import com.rbmhz.actwtk.repository.InterfaceRepository
import com.rbmhz.actwtk.repository.Repository
import com.rbmhz.actwtk.transformer.observableSchedulersTransformer
import com.rbmhz.actwtk.viewmodel.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val api: Repository
) : BaseViewModel() {

    val data: MutableLiveData<List<GalleryModel>> by lazy {
        MutableLiveData<List<GalleryModel>>()
    }

    @SuppressLint("CheckResult")
    fun loadDataApiO() {
        api.getGallery()
            .compose(observableSchedulersTransformer())
            .subscribe({ res ->
                when (res.state) {
                    State.IN_PROGRESS -> toast("Loading...")
                    State.FAILED -> toast(res.message)
                    State.SUCCESS -> data.postValue(res.data)
                }

            }, { throwable ->
                throwable?.printStackTrace()
            })
    }
}