package com.wwish.frame.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

/**
 * Created by wangwei-ds10 on 2017/8/31.
 */

public interface IActivityStackManager {
    void push(Class<? extends BaseActivity> cls, Bundle args, HashMap<String, Object> hashMap);

    boolean pop(int requestCode, int resultCode, Intent data);

    boolean empty();

    boolean full();

    void clear();

    int size();

    BaseActivity top();

    void destroy();
}
