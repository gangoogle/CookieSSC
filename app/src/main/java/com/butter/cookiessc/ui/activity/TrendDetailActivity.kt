package com.butter.cookiessc.ui.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.View
import com.butter.cookiessc.R
import com.butter.cookiessc.adapter.PerfTargetAdapter
import com.butter.cookiessc.data.getTrendDetailData
import com.butter.cookiessc.data.getTrendItemData
import com.butter.cookiessc.ui.view.MyProgressDialog
import com.butter.cookiessc.utils.ComUtils
import kotlinx.android.synthetic.main.activity_trend_detail.*
import kotlinx.android.synthetic.main.fragment_indicator_show.*
import java.util.*

class TrendDetailActivity : AppCompatActivity() {

    lateinit var dialog: MyProgressDialog
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(R.layout.activity_trend_detail)
        dialog = MyProgressDialog(this)
        val bundle = intent.getBundleExtra("data")
        val title = bundle.getString("name")
        val url = bundle.getString("url")
        tv_title_t.text = title
        fab.setOnClickListener { finish() }
        val t = Random().nextInt(3)
        dialog.initDialog("")
        val msg = Message()
        msg.obj = url
        msg.what = 0
        handler.sendMessageDelayed(msg, 1000 * t.toLong())
        ComUtils.addScore(this, 4)
    }

    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    dialog.dissmisDialog()
                    val item = getTrendDetailData().get(msg.obj as String)
                    ap_budget.setPercent(item?.archPercent!!)
                    tv_occu_rate_value.setText("${item?.occuRate}")
                    tv_comp_forecast_percent_value.setText("${item?.forceasRate}")
                    tv_room_night_value.setText("${item?.budgetMoney}")
                    tv_date_detail.setText("昨日：${ComUtils.getYesterday(Date())}")
                    tv_occu_rate_p.visibility = View.VISIBLE
                    tv_id_1.setText("${item?.v1}")
                    tv_id_2.setText("${item?.v2}")
                    tv_id_3.setText("${item?.v3}")
                    tv_id_4.setText("${item?.v4}")
                    tv_id_5.setText("${item?.v5}")
                    tv_id_6.setText("${item?.v6}")
                    list_view.adapter = PerfTargetAdapter(mContext, item.linePercent)
                }
            }
        }
    }
}
