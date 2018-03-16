package com.jointwisdom.rms.single.group.ui.widget

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.PopupWindow
import android.animation.ValueAnimator
import com.butter.cookiea.R


/**
 * Created by zgyi on 2017-11-21.
 */
class MyFilterLayout(var context: Context, val parentView: View) {
    var mPopupwindow: PopupWindow? = null
    //max/10*scale;
    val widthScale = context.resources.getInteger(R.integer.IntFilterLayoutWidthSacle)
    var screenWidth = 0
    var screenHeight = 0
    var layoutView: View? = null
    var mSaveSelectedListener: SaveSelectedListener? = null

    init {
        layoutView = View.inflate(context, R.layout.view_filter_layout, null)
        initPop()
        setListData()
        showPop()
    }

    /**
     * 初始化popupwindow
     */
    private fun initPop() {
        screenWidth = getScreenWH(context)[0]
        screenHeight = getScreenWH(context)[1]
        mPopupwindow = PopupWindow(layoutView, getPopWidth(screenWidth), screenHeight, true)
        mPopupwindow?.setClippingEnabled(false)
        val dw = ColorDrawable(Color.TRANSPARENT)
        mPopupwindow?.setBackgroundDrawable(dw)
        mPopupwindow?.setOnDismissListener { backgroundAlpha(context, 1.0f) }
        mPopupwindow?.animationStyle = R.style.FilterLayoutPopAnimStyle
    }

    /**
     * 设置listview数据
     */
    private fun setListData() {

    }


    private fun showPop() {
        backgroundAlpha(context, 0.5f)
        mPopupwindow?.showAtLocation(parentView, Gravity.NO_GRAVITY,
                getShowXLocation(screenWidth, getPopWidth(screenWidth)), 0)
    }

    /**
     * 关闭popupwindow
     */
    private fun dissmissPop() {
        if (mPopupwindow?.isShowing!!) {
            mPopupwindow?.dismiss()
        }
    }


    private fun getScreenWH(context: Context): Array<Int> {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width = wm.defaultDisplay.width
        val heigh = wm.defaultDisplay.height
        return arrayOf(width, heigh)
    }

    /**
     * 计算Popupwindow的宽度
     */
    private fun getPopWidth(maxWidth: Int): Int {
        return maxWidth / 10 * widthScale
    }

    /**
     * 获取popupwindow x轴显示位置
     */
    private fun getShowXLocation(maxWidth: Int, popWidth: Int): Int {
        return maxWidth - popWidth
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    private fun backgroundAlpha(context: Context, bgAlpha: Float) {
        val lp = (context as Activity).window.attributes
        //透明度的动画
        val animator = ValueAnimator.ofFloat(if (bgAlpha == 1f) 0.5f else 1f, bgAlpha)
        animator.addUpdateListener {
            lp.alpha = it.getAnimatedValue() as Float
            (context as Activity).window.attributes = lp
        }
        animator.setDuration((context.resources.getInteger(R.integer.IntFilterAnimDuration)).toLong())
        animator.start()
    }

    interface SaveSelectedListener {
        fun saveData(position: Int)
    }

    fun addSaveSelectedListener(saveSelectedListener: SaveSelectedListener) {
        this.mSaveSelectedListener = saveSelectedListener
    }
}