package com.sp.atomato.Model;

/**
 * Created by Administrator on 2017/2/7.
 */

public class Joke {
    public String title,img_url;
    public void setTitle(String title){
        this.title=title;
    }
    public void setImg_url(String url){
        this.img_url=url;
    }
    public String getTitle(){
        return this.title;
    }
    public String getImg_url(){
        return this.img_url;
    }
}
