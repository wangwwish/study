package com.wwish.frame.utils;

import android.util.Log;

/**
 * Created by wangwei-ds10 on 2017/8/31.
 */

public class GLog {
    private final static String TAG = GLog.class.getSimpleName();
    public static void d(String tag,String value){
        Log.d(tag,value);
    }

    public static void e(String tag,String value){
        Log.e(tag, value);
    }

    public static void w(String tag,String value){
        Log.w(tag, value);
    }

    public static void i(String tag,String value){
        Log.i(tag, value);
    }
    public static void e(String tag,Throwable t){
        e(tag,"",t);
    }
    public static void e(String tag, String info, Throwable t) {
        try {
            if (tag != null && info != null && t != null) {
                e(tag, info + "\n" + Log.getStackTraceString(t));
            }
        } catch (Exception e) {
            GLog.e(TAG, e.getMessage());
        }

    }
}
