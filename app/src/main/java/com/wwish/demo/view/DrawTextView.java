package com.wwish.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wwish on 2017/11/9.
 */

public class DrawTextView extends View {
    //画笔
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);

    private String test="test";

    public DrawTextView(Context context) {
        super(context);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setPaint(){
        //设置文字大小
        paint.setTextSize(50);
        //是否加删除线
        paint.setStrikeThruText(true);
        //是否加下划线
        paint.setUnderlineText(true);
        //设置字体
//        paint.setTypeface(Typeface.SANS_SERIF);
//        paint.setTypeface(Typeface.DEFAULT);
        paint.setTypeface(Typeface.MONOSPACE);
        //文字横向错切角度
        paint.setTextSkewX(1);
        //文字横向缩放
        paint.setTextScaleX(1);

       //设置文字对齐方式
        paint.setTextAlign(Paint.Align.CENTER);
        //获得行距
        paint.getFontSpacing();
        //获得测量值
        paint.measureText("ttt");
        Rect bounds=new Rect();
        //获得区域bounds
        paint.getTextBounds(test,0,test.length(),bounds);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setTextSize(40);
        setPaint();
        canvas.drawText(test,50,500,paint);

    }
}
