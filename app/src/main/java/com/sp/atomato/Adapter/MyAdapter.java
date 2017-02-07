package com.sp.atomato.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sp.atomato.Model.Joke;
import com.sp.atomato.R;
import com.sp.atomato.View.SwipeRecycler.SwipeCardAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/7.
 */

public class MyAdapter extends SwipeCardAdapter<MyAdapter.MyHolder> {
    private static final String TAG = "MyAdapter";
    private Context mcontext;
    public MyAdapter(Context context, ArrayList<Joke> list){
        super(list);
        mcontext=context;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent,int defStyle){
        View view= LayoutInflater.from(mcontext).inflate(R.layout.mainactivity_item,null);
        return new MyHolder(view);
    }
    @Override
    public void onBindViewHolder(MyHolder myHolder,int position){
        myHolder.textView.setText(mlist.get(position).getTitle());
        Glide.with(mcontext)
                .load(mlist.get(position).getImg_url())
                .into(myHolder.imageView);
        Log.e(TAG, "onBindViewHolder: "+position );
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public MyHolder(View view){
            super(view);
            textView=(TextView)view.findViewById(R.id.content_text);
            imageView=(ImageView)view.findViewById(R.id.content_img);
        }
    }
}
