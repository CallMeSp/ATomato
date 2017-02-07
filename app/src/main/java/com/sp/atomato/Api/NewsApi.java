package com.sp.atomato.Api;



import com.sp.atomato.Model.MainBody;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface NewsApi {
    @GET("http://route.showapi.com/107-33//")
    Observable<MainBody> getbyRxjava(@Query("showapi_appid") String k, @Query("showapi_sign") String s);
}
