package com.butter.butterssc.ui.view

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.TextView
import com.butter.butterssc.R
import com.butter.butterssc.adapter.RCTrendAdapter
import com.butter.butterssc.data.getTrendItemData
import kotlinx.android.synthetic.main.view_trend.view.*

/**
 * Created by zgyi on 2018-01-03.
 */
class TrendView(context: Context) : BaseHomeView(context) {

    var mView: View? = null

    override fun getView(): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.view_trend, null)
        }
        return mView ?: View(context)
    }

    override fun initData() {
        Log.d("yzg","trend view initData")
        mView?.rv_view?.layoutManager = LinearLayoutManager(context)
        mView?.rv_view?.adapter = RCTrendAdapter(context, getTrendItemData())
    }
}