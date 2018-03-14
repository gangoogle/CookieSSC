package com.butter.cookiessc.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.butter.cookiessc.R
import com.butter.cookiessc.R.id.tv_user_eamil
import com.butter.cookiessc.adapter.RCNewsAdapter
import com.butter.cookiessc.constant.JDNEWS_APPKEY
import com.butter.cookiessc.constant.NEWS_URL
import com.butter.cookiessc.model.FinishEvent
import com.butter.cookiessc.model.response.BaseNewsResponse
import com.butter.cookiessc.model.response.NewsResponse
import com.butter.cookiessc.net.Api
import com.butter.cookiessc.net.RetrofitNetHelper
import com.butter.cookiessc.ui.activity.CollectionActivity
import com.butter.cookiessc.ui.activity.FeedBackActivity
import com.butter.cookiessc.ui.activity.LoginActivity
import com.butter.cookiessc.utils.ComUtils
import kotlinx.android.synthetic.main.activity_setting2.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by 11509 on 2018/2/23.
 */
class SettingView(context: Context, val abundle: Bundle?) : BaseHomeView(context, abundle) {

    var mView: View? = null


    override fun getView(): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.activity_setting2, null)
        }
        mView?.tv_un_register?.setOnClickListener {
            ComUtils.saveLoginInfo(context, "", "")
            context.startActivity(Intent(context, LoginActivity::class.java))
            EventBus.getDefault().post(FinishEvent(true))
        }
        //eamil
        mView?.tv_user_eamil?.text = ComUtils.getLoginInfo(context).email
        //收藏
        mView?.tv_collection?.text = "${ComUtils.getCollections(context)?.size ?: 0}条"
        //返回按钮
//        iv_back.setOnClickListener { finish() }


        mView?.tv_notification_switch?.text = if (ComUtils.getNotificationSetting(context)) "开" else "关"
        mView?.tv_notification_switch?.setOnClickListener {
            ComUtils.setNotificationSetting(context, !ComUtils.getNotificationSetting(context))
            mView?.tv_notification_switch?.text = if (ComUtils.getNotificationSetting(context)) "开" else "关"
        }

        mView?.tv_score?.setText("${ComUtils.getScore(context)} C")

        mView?.ll_collection?.setOnClickListener {
            context.startActivity(Intent(context, CollectionActivity::class.java))
        }

        mView?.ll_suggest?.setOnClickListener {
            context.startActivity(Intent(context, FeedBackActivity::class.java))
        }
        return mView ?: View(context)
    }


    override fun initData() {

    }
}