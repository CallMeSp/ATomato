package com.sp.atomato.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sp.atomato.R;


/**
 * Created by Administrator on 2016/12/29.
 */

public class Countdown extends View{
    private static final String TAG = "Countdown";
    private Path origin_path,elapsed_path;
    private Paint origin_paint,elapsed_paint;
    private float si=0;
    private int width,height,radius,top,left,right,bottom,centre;
    private float progress=0;
    public Countdown(Context context){
        super(context);
        init();
    }
    public Countdown(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init();
    }
    public Countdown(Context context,AttributeSet attributeSet,int defstyle){
        super(context,attributeSet,defstyle);
        init();
    }
    public void setProgress(float x){
        progress=x;
    }
    public void init(){
        origin_paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        origin_paint.setAntiAlias(true);
        origin_paint.setStyle(Paint.Style.STROKE);
        origin_paint.setStrokeWidth(20);
        origin_paint.setColor(getResources().getColor(R.color.white));

        elapsed_paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        elapsed_paint.setAntiAlias(true);
        elapsed_paint.setStyle(Paint.Style.STROKE);
        origin_paint.setStrokeWidth(20);
        elapsed_paint.setColor(getResources().getColor(R.color.tomato));

        elapsed_path=new Path();
    }
    @Override
    protected void onDraw(Canvas canvas){
        width=getWidth();
        height=getHeight();
        radius=width/2-20;
        centre=width/2;
        canvas.drawCircle(centre,centre,radius,origin_paint);
        top=getTop();left=getLeft();right=getRight();bottom=getBottom();
        RectF oval=new RectF(centre-radius,centre-radius,centre+radius,centre+radius);
        Log.e(TAG, "onDraw: "+progress);
        elapsed_paint.setStrokeWidth(22);
        canvas.drawArc(oval,-90,progress,false,elapsed_paint);
        postInvalidateDelayed(1000);
    }
    public void drawSita(float sita){
        int top=getTop(),left=getLeft(),right=getRight(),bottom=getBottom();
        elapsed_path.moveTo((getLeft()+getRight())/2,getTop());
    }
}
