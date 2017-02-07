package com.sp.atomato.Biz;


import com.sp.atomato.Model.MainBody;

/**
 * Created by Administrator on 2016/12/28.
 */

public interface ResultListener {
    //用于回调getnews的结果,在Mainpresenter中使用
    void getResult(MainBody result);
}
