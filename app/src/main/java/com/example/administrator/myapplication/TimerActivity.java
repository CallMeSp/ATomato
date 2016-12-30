package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;


import com.example.administrator.myapplication.View.Countdown;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/27.
 */

public class TimerActivity extends Activity {
    private Subscription subscription;
    private static final String TAG = "TimerActivity";
    private final int COUNTTIME=60;
    private int timeleft=0;
    private int min=30,sec=0;
    private Vibrator vibrator;
    Countdown countdown;
    @BindView(R.id.time_text)TextView textView;
    @Override
    protected void onCreate(Bundle savedInsaceState){
        super.onCreate(savedInsaceState);
        setContentView(R.layout.timeractivity_root);
        ButterKnife.bind(this);
        showTime();
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);

        countdown=(Countdown)findViewById(R.id.countdown);


    }
    public static void StartMe(Context context){
        Intent intent=new Intent(context,TimerActivity.class);
        context.startActivity(intent);
    }
    public void showTime(){
        subscription=Observable
                .interval(0,1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Long aLong) {
                        int t=aLong.intValue();
                        Log.e(TAG, "onNext: "+t);
                        updateTime(t);
                        float sita=aLong*360f/1800f;
                        Log.e(TAG, "onNext: sita"+sita);
                        countdown.setProgress(sita);
                        if (min>10){
                            if (sec>=10){
                                textView.setText(min+":"+sec);
                            }else {
                                textView.setText(min+":"+"0"+sec);
                            }
                        }else {
                            if (sec>=10){
                                textView.setText("0"+min+":"+sec);
                            }else {
                                textView.setText("0"+min+":"+"0"+sec);
                            }
                        }
                    }
                });
    }
    //要重写onDestroy取消注册subscription
    @Override
    public void onDestroy(){
        super.onDestroy();
        subscription.unsubscribe();
    }
    public void updateTime(int elapsed){
        sec--;
        if (sec<0){
            sec=59;
            min--;
            if (min<0){
                min=0;
            }
        }
        if (min==0&&sec==0){
            subscription.unsubscribe();
            vibrator.vibrate(500);
        }
    }
}
