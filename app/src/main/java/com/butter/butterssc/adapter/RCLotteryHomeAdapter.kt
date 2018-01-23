package com.butter.butterssc.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.butter.butterssc.R
import com.butter.butterssc.model.response.CaiPiaoResponse
import com.butter.butterssc.ui.activity.LotteryDetailActivity
import kotlinx.android.synthetic.main.item_home_lottery.view.*
import kotlinx.android.synthetic.main.item_lottery_info.view.*

/**
 * Created by zgyi on 2018-01-08.
 */
class RCLotteryHomeAdapter(val context: Context, val lotterys: MutableList<CaiPiaoResponse>) : RecyclerView.Adapter<RCLotteryHomeAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        Glide.with(context).load(lotterys[position]?.imgId).into(holder?.itemView?.imageview)
        holder?.itemView?.tv_name?.text = lotterys[position]?.name
        holder?.itemView?.tv_lottery_number_h?.text =
                lotterys[position].openCode.replace(',', ' ', true)
        holder?.itemView?.tv_odd_number_h?.text = lotterys[position].opentimestamp
        holder?.itemView?.tv_time_h?.text = lotterys[position].opentime
        holder?.itemView?.setOnClickListener {
            val intent = Intent(context, LotteryDetailActivity::class.java)
            intent.putExtra("url", lotterys[position]?.url)
            intent.putExtra("name", lotterys[position]?.name)
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_home_lottery, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return lotterys.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}