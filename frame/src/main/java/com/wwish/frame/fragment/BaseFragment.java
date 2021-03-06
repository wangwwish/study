package com.wwish.frame.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.wwish.frame.activity.BaseFragmentActivity;
import com.wwish.frame.utils.GLog;

/**
 * Created by wangwei-ds10 on 2017/8/31.
 */

public abstract class BaseFragment extends Fragment{
    private static final String TAG = BaseFragment.class.getSimpleName();

    private View containerView;// 这个frament的根view

    private Activity currentAttachedActivity = null;

    private BaseFragment baseFragment = null;

    public void buildDrawCacheBitmap() {
        try {
            View view = getView();
            if (view == null) {
                return;
            }

            int width = view.getLayoutParams().width;
            if (width <= 0) {
                width = view.getMeasuredWidth();
            }

            int height = view.getLayoutParams().height;
            if (height <= 0) {
                height = view.getMeasuredHeight();
            }

            Bitmap mDrawCacheBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(mDrawCacheBitmap);
            view.draw(canvas);
            if (mDrawCacheBitmap != null && !mDrawCacheBitmap.isRecycled()) {
                view.setTag(mDrawCacheBitmap);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog("onCreate", false);
        Bundle arg = getArguments();
        if (arg == null) {
            arg = new Bundle();
        }
        initData(arg);
        ;
    }


    public boolean isCurrentParentFragment() {
        if (!checkFragmentAvailable()) {
            return false;
        }
        if (baseFragment != null) {
            return getHostActivity().getCurrentFragment() == baseFragment;
        }

        return false;
    }

    public void setParent(BaseFragment parent) {
        baseFragment = parent;
    }

    public BaseFragment getParent() {
        return baseFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Activity activity) {
        try {
            super.onAttach(activity);
            showLog("onAttach", false);
            currentAttachedActivity = activity;
        } catch (Exception e) {
            GLog.e(TAG, e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        currentAttachedActivity = null;
        showLog("onDetach", false);


    }

    private void showLog(String log, boolean needCallStack) {
        GLog.d(TAG, log + " " + getClass().getName() + (needCallStack ? "" : ""));
    }

    @Override
    public final void onPause() {
        super.onPause();
        pause();
        showLog("onPause", false);
    }

    private boolean isFirstResume = true;

    @Override
    public final void onResume() {
        try {

            View mTootView = getView();
            if (mTootView != null) {
                mTootView.setTag(this);
            }

            if (isFirstResume) {
                isFirstResume = false;

            }

            super.onResume();
            if (containerView != null) {
                containerView.requestLayout();
                containerView.invalidate();
                resume();
                showLog("onResume", false);
            }
        } catch (Exception e) {
            GLog.e(TAG, e);
        }

    }

    @Override
    public final void onStart() {
        super.onStart();
        start();
        showLog("onStart", false);
    }

    @Override
    public final void onStop() {
        super.onStop();
        stop();
        showLog("onStop", false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLog("onViewCreated", false);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (containerView == null) {
            containerView = createView(inflater, container, savedInstanceState);
        } else {
            ViewGroup v = (ViewGroup) containerView.getParent();
            v.removeView(containerView);
        }
        showLog("onCreateView", false);
        return containerView;
    }

    /**
     * 这个方法很重要，整体的fragment中要显示的view在这里初始化，除了耗时的数据加载以外，其他view的初始化在这里执行，
     * 返回最终要显示的根view
     */
    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void resume();

    /**
     * 这个相当于现在activity中onStop中要做的事情
     */
    protected abstract void stop();

    /**
     * 这个相当于现在activity中onPause中要做的事情
     */
    protected abstract void pause();

    /**
     * 这个相当于现在activity中onStart中要做的事情
     */
    protected abstract void start();

    @Override
    public final void onDestroyView() {
        clearView();
        super.onDestroyView();
    }

    /**
     * 在这个方法里面进行数据的加载和UI的更新
     * 也就是说所有的数据获取等耗时操作全在这里实现，如果是有tab的fragment下的子fragment这个方法可以不现实
     *
     * @param animation 现在这个参数始终传入是null。
     */
    public abstract void onEnterAnimationEnd(Animation animation);

    @Override
    public void onDestroy() {
        super.onDestroy();
        clear();
    }

    /**
     * 在ondestoryView里面调用，必须重写，在这里把内存清理干净也就是这个fragment中的一些view层面的东西要清理
     */

    public abstract void clearView();

    /**
     * 在ondestory里面调用，必须重写，在这里把内存清理干净也就是这个fragment已经结束，所以这个方法很重要
     */
    public abstract void clear();

    public boolean checkFragmentAvailable() {
        return currentAttachedActivity != null;
    }

    /**
     * 这里会传进来一个Bundle对象用来对数据初始化
     */
    protected abstract void initData(Bundle data);


    public BaseFragmentActivity getHostActivity() {
        try {
            if (null != currentAttachedActivity) {
                return (BaseFragmentActivity) currentAttachedActivity;
            }
        } catch (Exception e) {
            GLog.e(TAG, e.getMessage());
        }
        return null;
    }

    protected View getRootView() {
        return containerView;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean dissmissPopWindowContainer() {
        return false;
    }
}
