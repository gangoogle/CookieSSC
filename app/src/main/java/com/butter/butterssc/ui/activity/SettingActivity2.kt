package com.butter.butterssc.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.butter.butterssc.R
import com.butter.butterssc.model.FinishEvent
import com.butter.butterssc.utils.ComUtils
import kotlinx.android.synthetic.main.activity_setting2.*
import org.greenrobot.eventbus.EventBus

class SettingActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting2)
        tv_un_register.setOnClickListener {
            ComUtils.saveLoginInfo(this, "", "")
            startActivity(Intent(this, LoginActivity::class.java))
            EventBus.getDefault().post(FinishEvent(true))
            finish()
        }
        //eamil
        tv_user_eamil.text = ComUtils.getLoginInfo(this).email
        //收藏
        tv_collection.text = "${ComUtils.getCollections(this)?.size ?: 0}条"
        //返回按钮
        iv_back.setOnClickListener { finish() }


        tv_notification_switch.text = if (ComUtils.getNotificationSetting(this)) "开" else "关"
        tv_notification_switch.setOnClickListener {
            ComUtils.setNotificationSetting(this, !ComUtils.getNotificationSetting(this))
            tv_notification_switch.text = if (ComUtils.getNotificationSetting(this)) "开" else "关"
        }

        tv_score.setText("${ComUtils.getScore(this)} C")

        ll_collection.setOnClickListener {
            startActivity(Intent(this, CollectionActivity::class.java))
        }

        ll_suggest.setOnClickListener{
            startActivity(Intent(this, FeedBackActivity::class.java))
        }
    }
}
