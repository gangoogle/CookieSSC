package com.butter.cookiea.ui.view

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.butter.cookiea.R
import com.butter.cookiea.adapter.RCNewsAdapter
import com.butter.cookiea.constant.JDNEWS_APPKEY
import com.butter.cookiea.constant.NEWS_URL
import com.butter.cookiea.model.response.BaseNewsResponse
import com.butter.cookiea.model.response.NewsResponse
import com.butter.cookiea.net.Api
import com.butter.cookiea.net.RetrofitNetHelper
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