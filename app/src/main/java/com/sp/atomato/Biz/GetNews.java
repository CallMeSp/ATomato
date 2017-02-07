package com.sp.atomato.Biz;

import android.util.Log;

import com.sp.atomato.Api.NewsApi;
import com.sp.atomato.Model.MainBody;
import com.sp.atomato.Presenter.MainPresenter;

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
        Log.e(TAG, "getNews: " );
        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl("http://route.showapi.com/107-33/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        NewsApi newsApi=retrofit.create(NewsApi.class);
        newsApi.getbyRxjava("29671","f2bafae0b5054e1ba479c2aad08b9822")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainBody>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG, "onError: " );
                    }
                    @Override
                    public void onNext(MainBody newsJson) {
                        Log.e(TAG, "onNext: "+"?????" );
                        listener.getResult(newsJson);
                    }
                });
    }
}
