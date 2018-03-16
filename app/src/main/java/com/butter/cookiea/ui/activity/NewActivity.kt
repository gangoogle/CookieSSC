package com.butter.cookiea.ui.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.butter.cookiea.R
import com.butter.cookiea.v2.ui.fragment.BaseHomeFragment
import com.butter.cookiea.v2.ui.fragment.HomeCurrentRegionFragment
import com.butter.cookiea.v2.ui.fragment.HomeSubordinateFragment
import kotlinx.android.synthetic.main.activity_new.*
import java.util.ArrayList

class NewActivity : AppCompatActivity() {

    private lateinit var fragments: MutableList<BaseHomeFragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        init()
    }

    fun init() {
        vp_main.setBackgroundColor(Color.LTGRAY)
        fragments = ArrayList()
        fragments.add(HomeCurrentRegionFragment())
        fragments.add(HomeSubordinateFragment())
        vp_main.setAdapter(FragmentAdapter(supportFragmentManager, fragments))
    }

    internal inner class FragmentAdapter(fm: FragmentManager, private val fragments: List<BaseHomeFragment>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}
