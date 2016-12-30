package com.example.administrator.myapplication.Model;

/**
 * Created by Administrator on 2016/12/30.
 */

public class News {
    public String title,update,imgurl,linkurl;
    public String getTitile(){
        return this.title;
    }
    public String getUpdate(){
        return this.update;
    }
    public String getImgurl(){
        return this.imgurl;
    }
    public String getLinkurl(){
        return this.linkurl;
    }
    public void setTitile(String title){
        this.title=title;
    }
    public void setUpdate(String update){
        this.update=update;
    }
    public void setImgurl(String imgurl){
        this.imgurl=imgurl;
    }
    public void setLinkurl(String linkurl){
        this.linkurl=linkurl;
    }
}
