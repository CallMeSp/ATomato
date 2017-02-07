package com.sp.atomato.Presenter;

import android.util.Log;

import com.sp.atomato.Biz.GetNews;
import com.sp.atomato.Biz.ResultListener;
import com.sp.atomato.MainActivity_I;
import com.sp.atomato.Model.Joke;
import com.sp.atomato.Model.MainBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MainPresenter {
    private GetNews getNews;
    private MainActivity_I mainActivity_i;
    private static final String TAG = "MainPresenter";
    private ArrayList<Joke> arrayList=new ArrayList<Joke>();
    public MainPresenter(MainActivity_I mainActivity_i){
        this.mainActivity_i=mainActivity_i;
        getNews=new GetNews(this);
    }
    public void GetSomeNews(){
        getNews.getNews(new ResultListener() {
            @Override
            public void getResult(MainBody result) {
                arrayList.clear();
                for (int i=0;i<result.showapi_res_body.list.size();i++){
                    Joke news=new Joke();
                    news.setImg_url(result.showapi_res_body.list.get(i).sourceurl);
                    news.setTitle(result.showapi_res_body.list.get(i).title);
                    Log.e(TAG, "getResult: "+"show result" );
                    Log.e(TAG, "getResult: "+news.getTitle() +"   "+news.getImg_url());
                    arrayList.add(news);
                }
                mainActivity_i.updateRecyclerView(arrayList);
            }
        });
    }

}
