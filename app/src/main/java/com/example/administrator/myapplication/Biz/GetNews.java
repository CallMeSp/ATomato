package com.example.administrator.myapplication.Biz;

import android.util.Log;

import com.example.administrator.myapplication.Api.NewsApi;
import com.example.administrator.myapplication.Model.NewsJson;
import com.example.administrator.myapplication.Presenter.MainPresenter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/27.
 */

public class GetNews implements GetNews_I{
    private static final String TAG = "GetNews";
    private MainPresenter presenter;

    public GetNews(MainPresenter presenter){
        this.presenter=presenter;
    }
    //获取新闻信息
    @Override
    public void getNews(final ResultListener listener){
        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl("http://route.showapi.com/109-35/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        NewsApi newsApi=retrofit.create(NewsApi.class);
        newsApi.getbyRxjava("29671","f2bafae0b5054e1ba479c2aad08b9822","NBA")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsJson>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }
                    @Override
                    public void onError(Throwable throwable) {

                    }
                    @Override
                    public void onNext(NewsJson newsJson) {
                        listener.getResult(newsJson);
                    }
                });
    }
}
