package com.butter.cookiessc.data

import com.butter.cookiessc.R
import com.butter.cookiessc.model.HomeTitle

/**
 * Created by zgyi on 2018-01-05.
 */
fun getHomeTitleData(): List<HomeTitle> {
    return listOf(HomeTitle("分析", R.color.colorAccent),
            HomeTitle("地图", R.color.cadetblue),
            HomeTitle("新开", R.color.olivedrab),
            HomeTitle("设置", R.color.olivedrab))
}