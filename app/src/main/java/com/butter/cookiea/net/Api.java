package com.butter.cookiea.net;


import com.butter.cookiea.model.response.BaseNewsResponse;
import com.butter.cookiea.model.response.BaseResp;
import com.butter.cookiea.model.response.CaiPiaoResponse;
import com.butter.cookiea.model.response.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by zgyi on 2017-12-28.
 */

public interface Api {
    @GET("{url}")
    Call<BaseResp<CaiPiaoResponse>> requestUrl(@Path("url") String url);

    @GET
    Call<BaseNewsResponse<NewsResponse>> requestNews(@Url String url, @Query("channel") String channel,
                                                     @Query("num") String num, @Query("start") String start,
                                                     @Query("appkey") String appkey);
}
