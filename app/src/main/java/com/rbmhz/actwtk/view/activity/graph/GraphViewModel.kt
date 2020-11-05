package com.rbmhz.actwtk.view.activity.graph

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.rbmhz.actwtk.model.GalleryModel
import com.rbmhz.actwtk.model.State
import com.rbmhz.actwtk.repository.Repository
import com.rbmhz.actwtk.transformer.observableSchedulersTransformer
import com.rbmhz.actwtk.viewmodel.BaseViewModel
import javax.inject.Inject


class GraphViewModel @Inject constructor(
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


    fun pieData(): PieData {

        val yData = floatArrayOf(5f, 10f, 15f, 30f, 40f,1f)
        val xData = arrayOf("Sony", "Huawei", "LG", "Apple", "Samsung","1f")

        val yVals1 = ArrayList<PieEntry>()
        val xVals = ArrayList<String>()
        for (i in yData.indices) yVals1.add(PieEntry(yData[i], xData[i]))
        for (element in xData) xVals.add(element)

        val dataSet = PieDataSet(yVals1, "")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        val colors = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.GRAY)

        return data
    }
    fun lineData(): LineData {

        val yData = floatArrayOf(125f, 210f, 315f, 230f, 240f,111f)
        val xData = arrayOf("Sony", "Huawei", "LG", "Apple", "Samsung","1f")

        val yVals1 = ArrayList<Entry>()
        val xVals = ArrayList<String>()
        for (i in yData.indices) yVals1.add(PieEntry(yData[i], xData[i]))
        for (element in xData) xVals.add(element)

        val dataSet = LineDataSet(yVals1, "")

//        val colors = ArrayList<Int>()
//        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
//        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
//        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
//        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
//        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

//        colors.add(ColorTemplate.getHoloBlue())
//        dataSet.colors = colors

        val data = LineData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.GRAY)

        return data
    }

}