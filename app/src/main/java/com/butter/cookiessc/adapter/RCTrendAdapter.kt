package com.butter.cookiessc.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.cookiessc.R
import com.butter.cookiessc.model.TrendItem
import com.butter.cookiessc.ui.activity.TrendDetailActivity
import com.butter.cookiessc.utils.ComUtils
import kotlinx.android.synthetic.main.item_trend.view.*
import java.util.*

/**
 * Created by zgyi on 2018-01-03.
 */
class RCTrendAdapter(val context: Context, val items: List<TrendItem>) : RecyclerView.Adapter<RCTrendAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.itemView?.imageview?.setImageResource(items[position]?.imgId)
        holder?.itemView?.tv_name?.text = items[position]?.name
        holder?.itemView?.tv_date?.text = ComUtils.getYesterday(Date())
        holder?.itemView?.setOnClickListener {
            val intent = Intent(context, TrendDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("url", items[position].url)
            bundle.putString("name", items[position].name)
            intent.putExtra("data",bundle)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return (ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_trend, parent, false)))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}