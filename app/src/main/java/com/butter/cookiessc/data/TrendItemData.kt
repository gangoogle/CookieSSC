package com.butter.cookiessc.data

import com.butter.cookiessc.R
import com.butter.cookiessc.model.TrendItem

/**
 * Created by zgyi on 2018-01-03.
 */
fun getTrendItemData(): List<TrendItem> {
    return listOf(
            TrendItem(R.mipmap.ic_7lecai, "七乐彩走势分析", "daletou.html", "qlc"),
            TrendItem(R.mipmap.ic_shuangseqiu, "双色球走势分析", "shuangseqiu.html", "ssq"),
            TrendItem(R.mipmap.ic_daletou, "大乐透走势分析", "daletou.html", "dlt"),
            TrendItem(R.mipmap.ic_3d, "福彩3d走势分析", "3dzoushi.html", "fc3d"),
            TrendItem(R.mipmap.ic_11xuan5, "11选5走势分析", "11xuan5.html", "bj11x5"),
            TrendItem(R.mipmap.ic_7xingcai, "7星彩走势分析", "7xingcai.html", "qxc"),
            TrendItem(R.mipmap.ic_kuai3, "快3走势分析", "kuai3.html", "bjk3"),
            TrendItem(R.mipmap.ic_pailie3, "排列3走势分析", "pailie3.html", "pl3"),
            TrendItem(R.mipmap.ic_pailie5, "排列5走势分析", "pailie5.html", "pl5")
    )
}