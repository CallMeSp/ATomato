package com.example.administrator.myapplication.Api;


import com.example.administrator.myapplication.Model.NewsJson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface NewsApi {
    @GET("http://route.showapi.com/109-35/")
    Observable<NewsJson> getbyRxjava(@Query("showapi_appid") String k, @Query("showapi_sign") String s, @Query("title") String x);
}
