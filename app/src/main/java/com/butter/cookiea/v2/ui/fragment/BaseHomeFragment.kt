package com.butter.cookiea.v2.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.cookiea.R

/**
 * Created by zgyi on 2017-11-24.
 */
open abstract class BaseHomeFragment : Fragment() {
    var mActivity: Activity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mActivity = context as Activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layoutId = 0
        if (this is HomeCurrentRegionFragment) {
            layoutId = R.layout.fragment_current_region
        } else if (this is HomeSubordinateFragment) {
            layoutId = R.layout.fragment_home_subordinate
        }
        val view = inflater?.inflate(layoutId, container, false)
        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var id: Int = 0
        if (this is HomeCurrentRegionFragment) {
            id = R.id.ll_fgm_container
        } else {
            id = R.id.ll_fgm_container_sub
        }
        val fragments = initFragmentView()

        val manager = activity.supportFragmentManager
        val transaction = manager.beginTransaction()
        for (fragment in fragments) {
            transaction.add(id, fragment)
        }
        transaction.commit()
    }

    abstract fun initFragmentView(): MutableList<BaseFragment>
}