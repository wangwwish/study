package com.wwish.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.wwish.demo.R;
import com.wwish.demo.activity.HomePageActivity;
import com.wwish.frame.fragment.BaseFragment;

/**
 * Created by wwish on 2017/9/5.
 */

public class TestFirstFragment extends BaseFragment implements View.OnClickListener{

    private Button button;
    private Context context;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=getHostActivity();
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        button=(Button)view.findViewById(R.id.btn_test);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    protected void resume() {

    }

    @Override
    protected void stop() {

    }

    @Override
    protected void pause() {

    }

    @Override
    protected void start() {

    }

    @Override
    public void onEnterAnimationEnd(Animation animation) {

    }

    @Override
    public void clearView() {

    }

    @Override
    public void clear() {

    }

    @Override
    protected void initData(Bundle data) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==button.getId()){
            Bundle bundle=new Bundle();
            ((HomePageActivity)context).addSecondFragment(TestFirstFragment.class,bundle,null);
        }
    }
}
