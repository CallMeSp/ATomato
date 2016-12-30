package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.Adapter.NewsAdapter;
import com.example.administrator.myapplication.Api.NewsApi;
import com.example.administrator.myapplication.Model.News;
import com.example.administrator.myapplication.Model.NewsJson;
import com.example.administrator.myapplication.Presenter.MainPresenter;
import com.example.administrator.myapplication.View.WaveView3;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements MainActivity_I{
    private static final String TAG = "MainActivity";
    private MainPresenter presenter;
    private NewsAdapter adapter;
    private ArrayList<News> arrayList=new ArrayList<News>();
    @BindView(R.id.main_header)ImageView imageView;
    @BindView(R.id.button_in_wave)FloatingActionButton floatingActionButton;
    @BindView(R.id.waveview)WaveView3 waveView3;
    @BindView(R.id.news_recycle)RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new MainPresenter(this);
        setContentView(R.layout.activity_main);
        initViews();
        presenter.GetSomeNews();
    }
    private void initViews(){
        ButterKnife.bind(this);
        Glide.with(this)
                .load(R.drawable.background)
                .into(imageView);
        final CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(-2,-2);
        lp.gravity = Gravity.BOTTOM| Gravity.CENTER;
        waveView3.setOnWaveAnimationListener(new WaveView3.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0,0,0,(int)y+2);
                floatingActionButton.setLayoutParams(lp);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerActivity.StartMe(MainActivity.this);
            }
        });
        adapter=new NewsAdapter(this,arrayList);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void updateRecyclerView(ArrayList<News> arrayList){
        this.arrayList.clear();
        this.arrayList.addAll(arrayList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

}








