package com.butter.butterssc.ui.view

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.butter.butterssc.R
import com.butter.butterssc.adapter.RCLotteryHomeAdapter
import com.butter.butterssc.adapter.RCNewsAdapter
import com.butter.butterssc.constant.JDNEWS_APPKEY
import com.butter.butterssc.constant.NEWS_URL
import com.butter.butterssc.data.getHomeTitleData
import com.butter.butterssc.data.getTrendItemData
import com.butter.butterssc.model.HomeTitle
import com.butter.butterssc.model.TrendItem
import com.butter.butterssc.model.response.BaseNewsResponse
import com.butter.butterssc.model.response.BaseResp
import com.butter.butterssc.model.response.CaiPiaoResponse
import com.butter.butterssc.model.response.NewsResponse
import com.butter.butterssc.net.Api
import com.butter.butterssc.net.RetrofitNetHelper
import kotlinx.android.synthetic.main.view_lottery.view.*


/**
 * Created by zgyi on 2018-01-03.
 */
class LotteryView(context: Context) : BaseHomeView(context) {
    var mView: View? = null
    val urls = getTrendItemData()
    val SIZE = 1
    lateinit var mAdapter: RCLotteryHomeAdapter
    val lotters = mutableListOf<CaiPiaoResponse>()
    override fun getView(): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.view_lottery, null)
        }
        mView?.rc_view?.layoutManager = LinearLayoutManager(context)
        mAdapter = RCLotteryHomeAdapter(context, lotters)
        mView?.rc_view?.adapter=mAdapter
        return mView ?: View(context)
    }


    override fun initData() {
        //发出请求
        urls.forEach {
            requestData(it)
        }

    }

    private fun requestData(trendItem: TrendItem) {
        val callResponse = RetrofitNetHelper.getInstance(context)
                .getAPIService(Api::class.java)
                .requestUrl("${trendItem.url}-$SIZE.json")
        RetrofitNetHelper.getInstance(context)
                .enqueueCall(callResponse, object : RetrofitNetHelper.RetrofitCallBack<CaiPiaoResponse> {
                    override fun onSuccess(baseResp: BaseResp<CaiPiaoResponse>) {
                        if (!baseResp?.data?.isEmpty()!!) {
                            val item = baseResp?.data[0]
                            item.imgId = trendItem.imgId
                            item.name = trendItem.name
                            item.url=trendItem.url
                            lotters.add(item)
                            mAdapter?.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(error: String) {
//                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                })
    }
}