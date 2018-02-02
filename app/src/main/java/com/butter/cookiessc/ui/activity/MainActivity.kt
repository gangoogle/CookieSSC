package com.butter.cookiessc.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.butter.cookiessc.R
import com.butter.cookiessc.adapter.HomeViewPagerAdapter
import com.butter.cookiessc.data.getHomeTitleData
import com.butter.cookiessc.model.FinishEvent
import com.butter.cookiessc.ui.view.BaseHomeView
import com.butter.cookiessc.ui.view.LotteryView
import com.butter.cookiessc.ui.view.NewsView
import com.butter.cookiessc.ui.view.TrendView
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    lateinit var mContext: Context
    lateinit var mViews: List<BaseHomeView>
    lateinit var mHomeViewAdapter: HomeViewPagerAdapter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                view_pager.setCurrentItem(0, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                view_pager.setCurrentItem(1, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                view_pager.setCurrentItem(2, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mContext = this
        EventBus.getDefault().register(this)
        mViews = listOf(TrendView(mContext, savedInstanceState), NewsView(mContext, savedInstanceState),
                LotteryView(mContext, savedInstanceState))
        mHomeViewAdapter = HomeViewPagerAdapter(mContext, mViews)
        view_pager.offscreenPageLimit = 4
        view_pager.adapter = mHomeViewAdapter
        mViews[0].loadData()
        view_pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> navigation.selectedItemId = R.id.navigation_home
                    1 -> navigation.selectedItemId = R.id.navigation_dashboard
                    2 -> navigation.selectedItemId = R.id.navigation_notifications
                }
                mViews[position].loadData()
                tv_title.text = getHomeTitleData()[position].title
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ll_status.setBackgroundColor(mContext.resources.getColor(getHomeTitleData()[position].color))
                    (mContext as MainActivity).getWindow().setStatusBarColor(mContext.resources.getColor(getHomeTitleData()[position].color))
                }
            }
        })
        iv_info.setOnClickListener {
            startActivity(Intent(mContext, SettingActivity2::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onReEventFinish(event: FinishEvent) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
