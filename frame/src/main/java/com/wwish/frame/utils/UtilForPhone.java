package com.wwish.frame.utils;

import android.os.Build;

/**
 * Created by wangwei-ds10 on 2017/8/31.
 */

public class UtilForPhone {
    public static boolean isSupportAnimation() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }
}
