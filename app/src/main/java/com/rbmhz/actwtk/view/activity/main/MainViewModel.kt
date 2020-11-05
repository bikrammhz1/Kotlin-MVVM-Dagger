package com.rbmhz.actwtk.view.activity.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rbmhz.actwtk.model.GalleryModel
import com.rbmhz.actwtk.model.State
import com.rbmhz.actwtk.model.User
import com.rbmhz.actwtk.repository.Repository
import com.rbmhz.actwtk.transformer.observableSchedulersTransformer
import com.rbmhz.actwtk.viewmodel.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val api: Repository
) : BaseViewModel() {

    val galleryList: MutableLiveData<List<GalleryModel>> by lazy {
        MutableLiveData<List<GalleryModel>>()
    }
    val ooodata: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

//    @SuppressLint("CheckResult")
//    fun loadData() {
//        interfaceRepository.getGallery()
//            .compose(observableSchedulersTransformer())
//            .subscribe({ res ->
//                when (res.state) {
//                    State.IN_PROGRESS -> toast("Loading...")
//                    State.FAILED -> toast(res.message)
//                    State.SUCCESS -> galleryList.postValue(res.data)
//                }
//
//            }, { throwable ->
//                throwable?.printStackTrace()
//            })
//
//    }
//
//    @SuppressLint("CheckResult")
//    fun loadDataApi() {
//        api.getGallery2()
//            .compose(observableSchedulersTransformer())
//            .subscribe({ res ->
//                when (res.state) {
//                    State.IN_PROGRESS -> toast("Loading...")
//                    State.FAILED -> toast(res.message)
//                    State.SUCCESS -> galleryList.postValue(res.data)
//                }
//
//            }, { throwable ->
//                throwable?.printStackTrace()
//            })
//
//    }

    fun barData(): BarData {
        val NoOfEmp = ArrayList<BarEntry>()
        NoOfEmp.add(BarEntry(945f, 0f))
        NoOfEmp.add(BarEntry(1040f, 1f))
        NoOfEmp.add(BarEntry(1133f, 2f))

        val year = ArrayList<BarEntry>()
        year.add(BarEntry(2f, 4f))
        year.add(BarEntry(3f, 0f))
        year.add(BarEntry(5f, 4f))


        val bardataset = BarDataSet(NoOfEmp, "No Of Employee")
        val bardataset1 = BarDataSet(year, "No Of Data")
        val data = BarData(bardataset1, bardataset)
        bardataset.setColors(*ColorTemplate.COLORFUL_COLORS)

        return data;
    }

    @SuppressLint("CheckResult")
    fun loadDataApiO() {
        api.getGallery3()
            .compose(observableSchedulersTransformer())
            .subscribe({ res ->
                LogDebugger("this--->001", Gson().toJson(res))
                when (res.state) {
                    State.IN_PROGRESS -> {
                        toast("Loading...")
                        showDialog()
                    }

                    State.FAILED -> {
                        toast(res.message)
                        hideDialog()
                    }

                    State.SUCCESS -> {
                        galleryList.postValue(res.data)
                        hideDialog()
                    }
                }

            }, { throwable ->
                hideDialog()
                throwable?.printStackTrace()
            })

    }

    @SuppressLint("CheckResult")
    fun loadDataApiO11() {
        api.getGallery4()
            .compose(observableSchedulersTransformer())
            .subscribe({ res ->
                when (res.state) {
                    State.IN_PROGRESS -> {
                        toast("Loading...")
                        showDialog()
                    }
                    State.FAILED -> {
                        toast(res.message)
                        hideDialog()
                    }
                    State.SUCCESS -> {
                        hideDialog()
                        var data: User = Gson().fromJson(
                            Gson().toJson(res.data),
                            object : TypeToken<User>() {}.type
                        )
                        ooodata.postValue(data)
                    }
                }

            }, { throwable ->
                hideDialog()
                throwable?.printStackTrace()
            })

    }
}