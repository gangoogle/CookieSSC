package com.butter.cookiessc.ui.view

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.animation.RotateAnimation
import com.butter.cookiessc.R
import com.butter.cookiessc.adapter.RCNewsAdapter
import com.butter.cookiessc.constant.JDNEWS_APPKEY
import com.butter.cookiessc.constant.NEWS_URL
import com.butter.cookiessc.model.response.BaseNewsResponse
import com.butter.cookiessc.model.response.NewsResponse
import com.butter.cookiessc.net.Api
import com.butter.cookiessc.net.RetrofitNetHelper
import kotlinx.android.synthetic.main.view_news.view.*
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.MapView
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.amap.api.maps2d.model.MyLocationStyle
import android.widget.Toast
import com.amap.api.services.core.LatLonPoint
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MarkerOptions
import kotlinx.android.synthetic.main.view_news.view.*


/**
 * Created by zgyi on 2018-01-03.
 */
class NewsView(context: Context, val abundle: Bundle?) : BaseHomeView(context, abundle){

    var mView: View? = null
    lateinit var dialog: MyProgressDialog


    override fun getView(): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.view_news, null)
        }
        dialog = MyProgressDialog(context)
        return mView ?: View(context)
    }


    override fun initData() {
        dialog.initDialog("加载中...")
        val callResponse = RetrofitNetHelper.getInstance(context)
                .getAPIService(Api::class.java)
                .requestNews(NEWS_URL, "财经", "40", "0", JDNEWS_APPKEY)
        RetrofitNetHelper.getInstance(context)
                .enqueueNewsCall(callResponse, object : RetrofitNetHelper.RetrofitNewsCallBack<NewsResponse> {
                    override fun onSuccess(baseResp: BaseNewsResponse<NewsResponse>) {
                        dialog.dissmisDialog()
                        //去除pic为空的数据
                        val arr = arrayListOf<NewsResponse.News>()
                        baseResp.result.result.list
                                .filter { !it.pic.equals("") }
                                .forEach { arr.add(it) }
                        mView?.rc_view?.layoutManager = LinearLayoutManager(context)
                        mView?.rc_view?.adapter = RCNewsAdapter(context, arr)

                    }

                    override fun onFailure(error: String) {
                        dialog.dissmisDialog()
                        Snackbar.make(mView?.rc_view!!, error, Snackbar.LENGTH_SHORT).show()
                        mView?.rc_view?.adapter = RCNewsAdapter(context, arrayListOf<NewsResponse.News>())
                    }
                })
    }


}