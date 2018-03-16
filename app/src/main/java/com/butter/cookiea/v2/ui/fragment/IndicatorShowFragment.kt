package com.butter.cookiea.v2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.butter.cookiea.R

/**
 * Created by zgyi on 2017-11-24.
 */
class IndicatorShowFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_indicator_show, container, false)
        return view!!
    }

}