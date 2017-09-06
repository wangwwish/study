package com.wwish.frame.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangwei-ds10 on 2017/8/31.
 */

public class ActivityStackManager implements IActivityStackManager{

    public List<BaseActivity> activityList = new ArrayList<BaseActivity>();

    @Override
    public void push(Class<? extends BaseActivity> cls, Bundle args, HashMap<String, Object> hashMap) {

    }

    @Override
    public boolean pop(int requestCode, int resultCode, Intent data) {
        return false;
    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public BaseActivity top() {
        return null;
    }

    @Override
    public void destroy() {

    }

    public void addActivity(BaseActivity activity) {
        activityList.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        activityList.remove(activity);
    }

    /**
     * 完全退出
     *
     * @param context
     */
    public void exit(Context context) {
        while (activityList.size() > 0) {
            activityList.get(activityList.size() - 1).finish();
        }
    }

    /**
     * 完全退出
     */
    public void exit() {
        while (activityList.size() > 0) {
            activityList.get(activityList.size() - 1).finish();
        }

    }


    public Activity removeAllTask(String className){
        Activity homeAc = null;
        for (Activity ac : activityList) {
//            System.out.println("======removeAllTask======="+ac.getClass().getName()+"--"+className);
            if (!ac.getClass().getName().equals(className)) {
                ac.finish();
            }else{
                homeAc = ac;
            }
        }
        return homeAc;
    }

    /**
     * 处理只保留相应activity的个数
     *
     * @param c  activity名
     * @param count 最大activity个数
     */
    public void handleActivityRemain(Class<?> c, int count) {
        List<Activity> activityListRemain = new ArrayList<Activity>();
        for (Activity ac : activityList) {
            if (c.isInstance(ac)) {
                activityListRemain.add(ac);
            }
        }
        if (activityListRemain.size() > count) {
            activityListRemain.get(0).finish();
        }
    }

    /**
     * 根据class name获取activity
     *
     * @param name
     * @return
     */
    public Activity getActivityByClassName(String name) {
        for (Activity ac : activityList) {
            if (ac.getClass().getName().indexOf(name) >= 0) {
                return ac;
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public BaseActivity getActivityByClass(Class cs) {
        for (BaseActivity ac : activityList) {
            if (ac.getClass().equals(cs)) {
                return ac;
            }
        }
        return null;
    }

    /**
     * 弹出activity
     *
     * @param activity
     */
    public void popActivity(BaseActivity activity) {
        removeActivity(activity);
        activity.finish();
    }


    /**
     * 弹出activity到
     *
     * @param cs
     */
    @SuppressWarnings("rawtypes")
    public void popUntilActivity(Class... cs) {
        List<BaseActivity> list = new ArrayList<BaseActivity>();
        for (int i = activityList.size() - 1; i >= 0; i--) {
            BaseActivity ac = activityList.get(i);
            boolean isTop = false;
            for (int j = 0; j < cs.length; j++) {
                if (ac.getClass().equals(cs[j])) {
                    isTop = true;
                    break;
                }
            }
            if (!isTop) {
                list.add(ac);
            } else break;
        }
        for (Iterator<BaseActivity> iterator = list.iterator(); iterator.hasNext(); ) {
            BaseActivity activity = iterator.next();
            popActivity(activity);
        }
    }
}
