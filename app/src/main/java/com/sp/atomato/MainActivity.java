package com.sp.atomato;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.sp.atomato.Adapter.MyAdapter;
import com.sp.atomato.Model.Joke;
import com.sp.atomato.Presenter.MainPresenter;
import com.sp.atomato.View.SwipeRecycler.ItemRemovedListener;
import com.sp.atomato.View.SwipeRecycler.SwipeCardLayoutManager;
import com.sp.atomato.View.SwipeRecycler.SwipeCardRecyclerView;
import com.sp.atomato.View.WaveView3;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements MainActivity_I{
    private static final String TAG = "MainActivity";
    private MainPresenter presenter;
    private MyAdapter adapter;
    private ArrayList<Joke> arrayList=new ArrayList<Joke>();
    @BindView(R.id.main_header)ImageView imageView;
    @BindView(R.id.button_in_wave)FloatingActionButton floatingActionButton;
    @BindView(R.id.waveview)WaveView3 waveView3;
    @BindView(R.id.joke_recycle)SwipeCardRecyclerView recyclerView;
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
        adapter=new MyAdapter(this,arrayList);
        recyclerView.setLayoutManager(new SwipeCardLayoutManager());
        recyclerView.setAdapter(adapter);
        recyclerView.setItemRemovedListener(new ItemRemovedListener() {
            @Override
            public void onRightRemoved() {
            }
            @Override
            public void onLeftRemoved() {
            }
        });
    }
    @Override
    public void updateRecyclerView(ArrayList<Joke> arrayList){
        Log.e(TAG, "updateRecyclerView: "+arrayList.size() );
        //this.arrayList.clear();
        //this.arrayList.addAll(arrayList);\
        adapter.mlist.clear();
        adapter.mlist.addAll(arrayList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               adapter.notifyDataSetChanged();
                //adapter.updateCntent();
            }
        });
    }

}








