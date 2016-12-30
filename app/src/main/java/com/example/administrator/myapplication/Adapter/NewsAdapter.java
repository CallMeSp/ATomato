package com.example.administrator.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.Model.News;
import com.example.administrator.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/30.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder>{
    private Context context;
    private ArrayList<News> newsArrayList=new ArrayList<News>();
    private LayoutInflater inflater;
    public NewsAdapter(Context context, ArrayList<News> newsArrayList){
        this.context=context;
        this.newsArrayList=newsArrayList;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getItemCount(){
        return newsArrayList.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView title,date;
        ImageView img;
        public MyHolder(View view){
            super(view);
            title=(TextView)view.findViewById(R.id.news_title);
            date=(TextView)view.findViewById(R.id.news_date);
            img=(ImageView)view.findViewById(R.id.news_img);
        }
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup viewGroup,int type){
        View view=inflater.inflate(R.layout.mainactivity_item,viewGroup,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }
    @Override
    public void onBindViewHolder(MyHolder myHolder,int position){
        myHolder.title.setText(newsArrayList.get(position).getTitile());
        myHolder.date.setText(newsArrayList.get(position).getUpdate());
        if (newsArrayList.get(position).getImgurl()!=null){
            Glide.with(context)
                    .load(newsArrayList.get(position).getImgurl())
                    .into(myHolder.img);
        }else {
            Glide.with(context)
                    .load(R.drawable.ifnull)
                    .into(myHolder.img);
        }
    }
}
