package com.butter.cookiea.v2.ui.fragment
import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment


/**
 * Created by zgyi on 2017-11-24.
 */
open abstract class BaseFragment : Fragment() {
    var mActivity: Activity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mActivity = context as Activity
    }
}