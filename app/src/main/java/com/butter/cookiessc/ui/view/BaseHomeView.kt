package com.butter.cookiessc.ui.view

import android.content.Context
import android.os.Bundle
import android.view.View

/**
 * Created by zgyi on 2018-01-03.
 */
abstract class BaseHomeView(val context: Context, val bundle: Bundle?) {

    private var isLoad = false

    abstract fun getView(): View

    fun loadData() {
        if (!isLoad) {
            //防止view没有加载
            getView()
            initData()
            isLoad = true
        }
    }

    protected abstract fun initData()

}