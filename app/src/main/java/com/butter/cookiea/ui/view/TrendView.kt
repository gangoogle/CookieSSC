package com.butter.cookiea.ui.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.butter.cookiea.R
import com.butter.cookiea.adapter.RCTrendAdapter
import com.butter.cookiea.data.getTrendItemData
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
        mView?.rv_view?.layoutManager =  GridLayoutManager(context, 2)
        mView?.rv_view?.adapter = RCTrendAdapter(context, getTrendItemData())

    }
}