package com.butter.butterssc.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.butter.butterssc.R
import com.butter.butterssc.adapter.HomeViewPagerAdapter
import com.butter.butterssc.data.getHomeTitleData
import com.butter.butterssc.model.FinishEvent
import com.butter.butterssc.ui.view.BaseHomeView
import com.butter.butterssc.ui.view.LotteryView
import com.butter.butterssc.ui.view.NewsView
import com.butter.butterssc.ui.view.TrendView
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
        mViews = listOf(TrendView(mContext), NewsView(mContext), LotteryView(mContext))
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
    fun onReEventFinish(event:FinishEvent){
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
