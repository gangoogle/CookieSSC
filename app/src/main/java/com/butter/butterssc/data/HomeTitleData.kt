package com.butter.butterssc.data

import com.butter.butterssc.R
import com.butter.butterssc.model.HomeTitle

/**
 * Created by zgyi on 2018-01-05.
 */
fun getHomeTitleData(): List<HomeTitle> {
    return listOf(HomeTitle("趋势", R.color.colorRedMain),
            HomeTitle("新闻", R.color.cadetblue),
            HomeTitle("开奖", R.color.olivedrab))
}