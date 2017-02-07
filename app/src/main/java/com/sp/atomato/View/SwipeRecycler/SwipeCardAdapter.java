package com.sp.atomato.View.SwipeRecycler;

import android.support.v7.widget.RecyclerView;


import com.sp.atomato.Model.Joke;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/6.
 */

public abstract class SwipeCardAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public ArrayList<Joke> mlist=new ArrayList<>();
    public SwipeCardAdapter(ArrayList<Joke> list){
        mlist.clear();
        mlist.addAll(list);
        //notifyDataSetChanged();
    }
    public void deleteTopItem(){
        int position=getItemCount()-1;
        mlist.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public int getItemCount(){
        return mlist.size();
    }
    public void updateCntent(){
        notifyDataSetChanged();
    }
}
