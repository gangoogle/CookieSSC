package com.butter.cookiessc.ui.view

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.animation.RotateAnimation
import com.butter.cookiessc.R
import com.butter.cookiessc.adapter.RCNewsAdapter
import com.butter.cookiessc.constant.JDNEWS_APPKEY
import com.butter.cookiessc.constant.NEWS_URL
import com.butter.cookiessc.model.response.BaseNewsResponse
import com.butter.cookiessc.model.response.NewsResponse
import com.butter.cookiessc.net.Api
import com.butter.cookiessc.net.RetrofitNetHelper
import kotlinx.android.synthetic.main.view_news.view.*
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.MapView
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.amap.api.maps2d.model.MyLocationStyle
import android.widget.Toast
import com.amap.api.services.core.LatLonPoint
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MarkerOptions


/**
 * Created by zgyi on 2018-01-03.
 */
class NewsView(context: Context, val abundle: Bundle?) : BaseHomeView(context, abundle), PoiSearch.OnPoiSearchListener, AMapLocationListener {

    override fun onLocationChanged(amapLocation: AMapLocation?) {
        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
                //定位成功回调信息，设置相关消息
                Latitude = amapLocation.latitude//获取纬度
                Longitude = amapLocation.longitude//获取经度


                var query = PoiSearch.Query("彩票", "", "")
                // keyWord表示搜索字符串，第二个参数表示POI搜索类型，默认为：生活服务、餐饮服务、商务住宅
                // 共分为以下20种：汽车服务|汽车销售|
                // 汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
                // 住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
                // 金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
                // cityCode表示POI搜索区域，（这里可以传空字符串，空字符串代表全国在全国范围内进行搜索）
                query.setPageSize(10)// 设置每页最多返回多少条poiitem
                query.setPageNum(1)// 设置查第一页
                val poiSearch = PoiSearch(context, query)
                //如果不为空值
                if (Latitude !== 0.0 && Longitude !== 0.0) {
                    poiSearch.bound = PoiSearch.SearchBound(LatLonPoint(Latitude,
                            Longitude), 6000)// 设置周边搜索的中心点以及区域
                    poiSearch.setOnPoiSearchListener(this)// 设置数据返回的监听器
                    poiSearch.searchPOIAsyn()// 开始搜索
                } else {
                    Toast.makeText(context, "定位失败", Toast.LENGTH_SHORT).show()
                }

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.errorCode + ", errInfo:"
                        + amapLocation.errorInfo)
            }
        }


    }


    override fun onPoiSearched(p0: PoiResult?, p1: Int) {
        Log.d("yzg", "${p0?.pois?.size}")
        p0?.pois?.forEach {
            val latLng = LatLng(it.latLonPoint.latitude, it.latLonPoint.longitude);
            val marker = aMap?.addMarker(MarkerOptions().position(latLng).title(it?.title).snippet(it?.snippet))
        }

    }

    override fun onPoiItemSearched(p0: PoiItem?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var mView: View? = null
    lateinit var dialog: MyProgressDialog
    var Latitude: Double = 0.0
    var Longitude: Double = 0.0
    var mLocationOption: AMapLocationClientOption? = null
    var mLocationClient: AMapLocationClient? = null
    var aMap: AMap? = null
    override fun getView(): View {
        if (mView == null) {
            mView = View.inflate(context, R.layout.view_news, null)
        }
        dialog = MyProgressDialog(context)
        return mView ?: View(context)
    }


    override fun initData() {
//        dialog.initDialog("加载中...")
//        val callResponse = RetrofitNetHelper.getInstance(context)
//                .getAPIService(Api::class.java)
//                .requestNews(NEWS_URL, "财经", "40", "0", JDNEWS_APPKEY)
//        RetrofitNetHelper.getInstance(context)
//                .enqueueNewsCall(callResponse, object : RetrofitNetHelper.RetrofitNewsCallBack<NewsResponse> {
//                    override fun onSuccess(baseResp: BaseNewsResponse<NewsResponse>) {
//                        dialog.dissmisDialog()
//                        //去除pic为空的数据
//                        val arr = arrayListOf<NewsResponse.News>()
//                        baseResp.result.result.list
//                                .filter { !it.pic.equals("") }
//                                .forEach { arr.add(it) }
//                        mView?.rc_view?.layoutManager = LinearLayoutManager(context)
//                        mView?.rc_view?.adapter = RCNewsAdapter(context, arr)
//
//                    }
//
//                    override fun onFailure(error: String) {
//                        dialog.dissmisDialog()
//                        Snackbar.make(mView?.rc_view!!, error, Snackbar.LENGTH_SHORT).show()
//                        mView?.rc_view?.adapter = RCNewsAdapter(context, arrayListOf<NewsResponse.News>())
//                    }
//                })

        mView?.mapview?.onCreate(abundle)
        aMap = mView?.mapview?.map

        mLocationOption = AMapLocationClientOption()
// 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption?.setLocationMode(AMapLocationMode.Hight_Accuracy)
// 设置是否返回地址信息（默认返回地址信息）
        mLocationOption?.setNeedAddress(true)
// 设置是否只定位一次,默认为false
        mLocationOption?.setOnceLocation(false)
// 设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption?.setWifiActiveScan(true)
// 设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption?.setMockEnable(false)
// 设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption?.setInterval(2000)


        mLocationClient = AMapLocationClient(context);

        mLocationClient?.setLocationOption(mLocationOption);
        mLocationClient?.setLocationListener(this)
// 启动定位
        mLocationClient?.startLocation()


        val myLocationStyle: MyLocationStyle
        myLocationStyle = MyLocationStyle()//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        aMap?.setMyLocationStyle(myLocationStyle)//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap?.setMyLocationEnabled(true)// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是fa
        aMap?.moveCamera(CameraUpdateFactory.zoomTo(18f))


    }


}