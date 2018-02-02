package com.butter.cookiessc.ui.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.butter.cookiessc.R
import com.butter.cookiessc.adapter.RCTrendAdapter
import com.butter.cookiessc.data.getTrendItemData
import kotlinx.android.synthetic.main.view_trend.view.*

/**
 * Created by zgyi on 2018-01-03.
 */
class TrendView(context: Context,val abundle: Bundle?) : BaseHomeView(context,abundle) {

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