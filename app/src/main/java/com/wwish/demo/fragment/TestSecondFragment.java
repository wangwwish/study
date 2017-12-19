package com.wwish.demo.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;

import com.wwish.demo.R;
import com.wwish.frame.fragment.BaseFragment;

/**
 * Created by wwish on 2017/11/10.
 */

public class TestSecondFragment extends BaseFragment {
    /**
     * 这个方法很重要，整体的fragment中要显示的view在这里初始化，除了耗时的数据加载以外，其他view的初始化在这里执行，
     * 返回最终要显示的根view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_third, container, false);

        ViewStub testView=(ViewStub)mView.findViewById(R.id.testView);
        testView.setLayoutResource(R.layout.view_paint_test);
        testView.inflate();
        return mView;
    }

    @Override
    protected void resume() {

    }

    /**
     * 这个相当于现在activity中onStop中要做的事情
     */
    @Override
    protected void stop() {

    }

    /**
     * 这个相当于现在activity中onPause中要做的事情
     */
    @Override
    protected void pause() {

    }

    /**
     * 这个相当于现在activity中onStart中要做的事情
     */
    @Override
    protected void start() {

    }

    /**
     * 在这个方法里面进行数据的加载和UI的更新
     * 也就是说所有的数据获取等耗时操作全在这里实现，如果是有tab的fragment下的子fragment这个方法可以不现实
     *
     * @param animation 现在这个参数始终传入是null。
     */
    @Override
    public void onEnterAnimationEnd(Animation animation) {

    }

    /**
     * 在ondestoryView里面调用，必须重写，在这里把内存清理干净也就是这个fragment中的一些view层面的东西要清理
     */
    @Override
    public void clearView() {

    }

    /**
     * 在ondestory里面调用，必须重写，在这里把内存清理干净也就是这个fragment已经结束，所以这个方法很重要
     */
    @Override
    public void clear() {

    }

    /**
     * 这里会传进来一个Bundle对象用来对数据初始化
     *
     * @param data
     */
    @Override
    protected void initData(Bundle data) {

    }
}
