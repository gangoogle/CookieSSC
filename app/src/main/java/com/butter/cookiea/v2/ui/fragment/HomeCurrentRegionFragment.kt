package com.butter.cookiea.v2.ui.fragment

/**
 * Created by zgyi on 2017-11-24.
 */
class HomeCurrentRegionFragment : BaseHomeFragment() {


    override fun initFragmentView(): MutableList<BaseFragment> {
        val indicatorShowFgm: BaseFragment = IndicatorShowFragment()
        return mutableListOf<BaseFragment>(indicatorShowFgm)
    }

}