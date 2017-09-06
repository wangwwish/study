package com.wwish.frame.fragment;

import android.content.Intent;
import android.os.Bundle;

import com.wwish.frame.fragment.BaseFragment;

import java.util.HashMap;

/**
 * Created by wangwei-ds10 on 2017/8/31.
 */

public interface IFragmentStackManager {
    void push(Class<? extends BaseFragment> cls, Bundle args, HashMap<String, Object> hashMap);

    boolean pop(int requestCode, int resultCode, Intent data);

    boolean empty();

    boolean full();

    void clear();

    int size();

    BaseFragment top();

    void destroy();
}
