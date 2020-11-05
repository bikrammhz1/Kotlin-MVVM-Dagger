package com.rbmhz.actwtk.view.activity.graph

import android.graphics.Color
import com.rbmhz.actwtk.R
import com.rbmhz.actwtk.databinding.ActivityGraphBinding
import com.rbmhz.actwtk.view.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_graph.*

/**
 * Created by Bikram Maharjan on 6/18/2020.
 */

class Graph : BaseActivity<ActivityGraphBinding, GraphViewModel>(
    GraphViewModel::class.java, R.layout.activity_graph
) {

    override fun onInitData(viewModel: GraphViewModel) {
        super.onInitData(viewModel)
        barChart(viewModel)
        LineChart(viewModel)
    }

    fun barChart(viewModel: GraphViewModel) {
        pie_chart.animateY(5000)
        pie_chart.cameraDistance = 7f;
        pie_chart.setUsePercentValues(true)
        pie_chart.isDrawHoleEnabled = true
        pie_chart.holeRadius = 17f;
        pie_chart.transparentCircleRadius = 11f;
        pie_chart.rotationAngle = 0f;
        pie_chart.isRotationEnabled = false;
        pie_chart.setEntryLabelColor(Color.GRAY)
        pie_chart.setDrawEntryLabels(true)
        pie_chart.description = null
        pie_chart.centerText
        pie_chart.data = viewModel.pieData()
    }

    fun LineChart(viewModel: GraphViewModel) {
        line_chart.animateY(5000)
        line_chart.cameraDistance = 7f;
        line_chart.description = null
        line_chart.data = viewModel.lineData()
    }
}