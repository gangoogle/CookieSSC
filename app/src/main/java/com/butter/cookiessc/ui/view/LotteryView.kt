package com.butter.cookiessc.ui.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.butter.cookiessc.R
import com.butter.cookiessc.adapter.RCLotteryHomeAdapter
import com.butter.cookiessc.data.getTrendItemData
import com.butter.cookiessc.model.TrendItem
import com.butter.cookiessc.model.response.BaseResp
import com.butter.cookiessc.model.response.CaiPiaoResponse
import com.butter.cookiessc.net.Api
import com.butter.cookiessc.net.RetrofitNetHelper
import kotlinx.android.synthetic.main.view_lottery.view.*


/**
 * Created by zgyi on 2018-01-03.
 */
class LotteryView(context: Context,val abundle: Bundle?) : BaseHomeView(context,abundle) {
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