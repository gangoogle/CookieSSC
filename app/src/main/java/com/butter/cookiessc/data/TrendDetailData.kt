package com.butter.cookiessc.data

/**
 * Created by zgyi on 2018-01-23.
 */

fun getTrendDetailData(): Map<String, TrendDetailItem> {
    return mapOf(
            "qlc" to TrendDetailItem(65.2f,30f,12f,
                    listOf(
                            LinePercentData("一等奖",92,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",98,false),
                            LinePercentData("五等奖",-60,false),
                            LinePercentData("六等奖",160,false)
                            )),
            "ssq" to TrendDetailItem(80f,90f,40f,
                    listOf(
                            LinePercentData("一等奖",-98,false),
                            LinePercentData("二等奖",150,false),
                            LinePercentData("三等奖",120,false),
                            LinePercentData("四等奖",112,false),
                            LinePercentData("五等奖",80,false),
                            LinePercentData("六等奖",80,false)
                    )),
            "dlt" to TrendDetailItem(90f,60f,32f,
                    listOf(
                            LinePercentData("一等奖",122,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",70,false),
                            LinePercentData("四等奖",58,false),
                            LinePercentData("五等奖",50,false),
                            LinePercentData("六等奖",160,false)
                    )),
            "fc3d" to TrendDetailItem(25f,20f,32f,
                    listOf(
                            LinePercentData("一等奖",12,false),
                            LinePercentData("二等奖",180,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",98,false),
                            LinePercentData("五等奖",-20,false),
                            LinePercentData("六等奖",20,false)
                    )),
            "bj11x5" to TrendDetailItem(25f,10f,22f,
                    listOf(
                            LinePercentData("一等奖",92,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",28,false),
                            LinePercentData("五等奖",60,false),
                            LinePercentData("六等奖",160,false)
                    )),
            "qxc" to TrendDetailItem(72.5f,30f,12f,
                    listOf(
                            LinePercentData("一等奖",92,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",98,false),
                            LinePercentData("五等奖",60,false),
                            LinePercentData("六等奖",160,false)
                    )),
            "bjk3" to TrendDetailItem(25f,10f,22f,
                    listOf(
                            LinePercentData("一等奖",92,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",28,false),
                            LinePercentData("五等奖",60,false),
                            LinePercentData("六等奖",160,false)
                    )),
            "pl3" to TrendDetailItem(25f,20f,32f,
                    listOf(
                            LinePercentData("一等奖",12,false),
                            LinePercentData("二等奖",180,false),
                            LinePercentData("三等奖",110,false),
                            LinePercentData("四等奖",98,false),
                            LinePercentData("五等奖",20,false),
                            LinePercentData("六等奖",20,false)
                    )),
            "pl5" to TrendDetailItem(90f,60f,32f,
                    listOf(
                            LinePercentData("一等奖",122,false),
                            LinePercentData("二等奖",120,false),
                            LinePercentData("三等奖",70,false),
                            LinePercentData("四等奖",58,false),
                            LinePercentData("五等奖",50,false),
                            LinePercentData("六等奖",160,false)
                    ))
            )
}