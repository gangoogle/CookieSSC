package com.butter.cookiea.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.butter.cookiea.R
import com.butter.cookiea.model.FinishEvent
import com.butter.cookiea.ui.activity.CollectionActivity
import com.butter.cookiea.ui.activity.FeedBackActivity
import com.butter.cookiea.ui.activity.LoginActivity
import com.butter.cookiea.utils.ComUtils
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