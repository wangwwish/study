package com.wwish.demo.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.wwish.aspectj.annotation.DebugTrace;
import com.wwish.demo.R;
import com.wwish.frame.activity.BaseActivity;

/**
 * Created by wangwei-ds10 on 2017/8/31.
 */

public class LaunchActivity extends BaseActivity {
    private final static String TAG =LaunchActivity.class.getSimpleName();
    public static int TIME_ANIMATION=1000;
    ImageView logo = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);
        initView();
        useAnimator();
    }
    @DebugTrace
    private void initView() {
        logo =(ImageView)this.findViewById(R.id.logo);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void useAnimator() {
        logo.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        ObjectAnimator scalex = ObjectAnimator.ofFloat(logo, "scaleX", 0, 1);
        ObjectAnimator scaley = ObjectAnimator.ofFloat(logo, "scaleY", 0, 1);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(logo, "rotation", 0.0f, 360f);
        rotation.addListener(new ObjectAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                logo.setLayerType(View.LAYER_TYPE_NONE,null);
                startHandler.sendEmptyMessageDelayed(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        AnimatorSet mSetPlayer = new AnimatorSet();
        mSetPlayer.setDuration(TIME_ANIMATION);
        mSetPlayer.play(scalex).with(scaley).with(rotation);
        mSetPlayer.start();
    }

    private Handler startHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //test
            Intent it = new Intent(LaunchActivity.this, HomePageActivity.class);
            startActivity(it);
            finish();
        }
    };
}
