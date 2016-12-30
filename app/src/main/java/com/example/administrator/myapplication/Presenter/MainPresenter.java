package com.example.administrator.myapplication.Presenter;

import android.util.Log;

import com.example.administrator.myapplication.Biz.GetNews;
import com.example.administrator.myapplication.Biz.ResultListener;
import com.example.administrator.myapplication.MainActivity_I;
import com.example.administrator.myapplication.Model.News;
import com.example.administrator.myapplication.Model.NewsJson;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MainPresenter {
    private GetNews getNews;
    private MainActivity_I mainActivity_i;
    private static final String TAG = "MainPresenter";
    private ArrayList<News> arrayList=new ArrayList<News>();
    public MainPresenter(MainActivity_I mainActivity_i){
        this.mainActivity_i=mainActivity_i;
        getNews=new GetNews(this);
    }
    public void GetSomeNews(){
        getNews.getNews(new ResultListener() {
            @Override
            public void getResult(NewsJson result) {
                arrayList.clear();
                for (int i=0;i<result.showapi_res_body.pagebean.contentlist.size();i++){
                    News news=new News();
                    Log.e(TAG, "onNext: "+(i+1)+"."+result.showapi_res_body.pagebean.contentlist.get(i).title);
                    Log.e(TAG, "onNext: "+(i+1)+"."+result.showapi_res_body.pagebean.contentlist.get(i).pubDate);
                    if (result.showapi_res_body.pagebean.contentlist.get(i).imageurls.size()>0){
                        Log.e(TAG, "onNext: "+(i+1)+"."+result.showapi_res_body.pagebean.contentlist.get(i).imageurls.get(0).url);
                        news.setImgurl(result.showapi_res_body.pagebean.contentlist.get(i).imageurls.get(0).url);
                    }
                    news.setTitile(result.showapi_res_body.pagebean.contentlist.get(i).title);
                    news.setUpdate(result.showapi_res_body.pagebean.contentlist.get(i).pubDate);
                    news.setLinkurl(result.showapi_res_body.pagebean.contentlist.get(i).link);
                    arrayList.add(news);
                }
                mainActivity_i.updateRecyclerView(arrayList);
            }
        });
    }

}
