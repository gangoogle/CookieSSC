package com.butter.cookiessc

import android.app.Application
import cn.jpush.android.api.JPushInterface
import com.butter.cookiessc.ui.activity.SplashActivity

/**
 * Created by zgyi on 2018-01-09.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}