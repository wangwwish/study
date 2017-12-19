package com.wwish.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wwish.demo.R;

/**
 * Created by wwish on 2017/11/9.
 */

public class PaintView extends View {

    Paint paint=new Paint();

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setPaint(String colorString){
        paint.setColor(Color.parseColor(colorString));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        setPaint("#009688");
//        canvas.drawRect(30,30,230,180,paint);
//        setPaint("#ffe188");
//        canvas.drawLine(330,30,490,180,paint);
//        setPaint("#e59688");
//        paint.setTextSize(60);
//        canvas.drawText("wwish",530,180,paint);
//
//        shader(canvas);
//
//        radialShader(canvas);
//
//        sweepShader(canvas);

        bitmapShader(canvas);
    }
     /*
      *
     */
    private void shader(Canvas canvas){
        Shader shader=new LinearGradient(100,100,500,500,Color.parseColor("#e91e63"),
                Color.parseColor("#2196f3"),Shader.TileMode.CLAMP);
        paint.setShader(shader);

        canvas.drawCircle(300,600,200,paint);
    }

    private void radialShader(Canvas canvas){
        Shader shader=new RadialGradient(600,600,300,Color.parseColor("#e91e63"),
                Color.parseColor("#2196f3"),Shader.TileMode.CLAMP);
        paint.setShader(shader);

        canvas.drawCircle(600,600,200,paint);
    }


    private void sweepShader(Canvas canvas){
        Shader shader=new SweepGradient(300,300,Color.parseColor("#e91e63"),
                Color.parseColor("#2196f3"));
        paint.setShader(shader);

        canvas.drawCircle(600,900,200,paint);
    }

    private void bitmapShader(Canvas canvas){
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Shader shader=new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        paint.setShader(shader);

        canvas.drawCircle(200,200,200,paint);
    }
}
