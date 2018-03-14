package com.butter.cookiessc.ui.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.butter.cookiessc.R
import com.butter.cookiessc.adapter.RCNewsAdapter
import com.butter.cookiessc.constant.JDNEWS_APPKEY
import com.butter.cookiessc.constant.NEWS_URL
import com.butter.cookiessc.model.response.BaseNewsResponse
import com.butter.cookiessc.model.response.NewsResponse
import com.butter.cookiessc.net.Api
import com.butter.cookiessc.net.RetrofitNetHelper
import com.butter.cookiessc.ui.view.MyProgressDialog
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity() {

    lateinit var dialog: MyProgressDialog
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        context = this
        dialog = MyProgressDialog(this)
        iv_back.setOnClickListener { finish() }
        initData()
    }

    private fun initData() {
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
                        rc_view?.layoutManager = LinearLayoutManager(context)
                        rc_view?.adapter = RCNewsAdapter(context, arr)

                    }

                    override fun onFailure(error: String) {
                        dialog.dissmisDialog()
                        Snackbar.make(rc_view!!, error, Snackbar.LENGTH_SHORT).show()
                        rc_view?.adapter = RCNewsAdapter(context, arrayListOf<NewsResponse.News>())
                    }
                })
    }
}
