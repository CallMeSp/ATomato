package com.example.administrator.myapplication.Presenter;

import android.util.Log;

import com.example.administrator.myapplication.Biz.GetNews;
import com.example.administrator.myapplication.Biz.ResultListener;
import com.example.administrator.myapplication.MainActivity_I;
import com.example.administrator.myapplication.Model.NewsJson;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MainPresenter {
    private GetNews getNews;
    private MainActivity_I mainActivity_i;
    private static final String TAG = "MainPresenter";
    public MainPresenter(MainActivity_I mainActivity_i){
        this.mainActivity_i=mainActivity_i;
        getNews=new GetNews(this);
    }
    public void GetSomeNews(){
        getNews.getNews(new ResultListener() {
            @Override
            public void getResult(NewsJson result) {
                for (int i=0;i<result.showapi_res_body.pagebean.contentlist.size();i++){
                    Log.e(TAG, "onNext: "+(i+1)+"."+result.showapi_res_body.pagebean.contentlist.get(i).title);
                }
            }
        });
    }

}
