package com.butter.butterssc.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.butter.butterssc.ui.view.BaseHomeView

/**
 * Created by zgyi on 2018-01-03.
 */
class HomeViewPagerAdapter(val context: Context, val views: List<BaseHomeView>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        container?.addView(views[position].getView())
        return views[position].getView()
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view === `object`

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(views[position].getView())
    }

    override fun getCount(): Int = views.size

}