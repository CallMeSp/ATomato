package com.example.administrator.myapplication.Biz;

import com.example.administrator.myapplication.Model.NewsJson;

/**
 * Created by Administrator on 2016/12/28.
 */

public interface ResultListener {
    //用于回调getnews的结果,在Mainpresenter中使用
    void getResult(NewsJson result);
}
