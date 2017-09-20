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
import com.wwish.demo.utils.DeviceUtil;
import com.wwish.frame.fragment.BaseFragment;

/**
 * Created by wwish on 2017/9/5.
 */

public class TestFirstFragment extends BaseFragment implements View.OnClickListener{

    private Button screenButton,systemButton,hardwareButton,cpuButton,networkButton,deviceIdButton;
    private Context context;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=getHostActivity();
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        screenButton=(Button)view.findViewById(R.id.btn_screen);
        screenButton.setOnClickListener(this);
        systemButton=(Button)view.findViewById(R.id.btn_system);
        systemButton.setOnClickListener(this);
        hardwareButton=(Button)view.findViewById(R.id.btn_hardware);
        hardwareButton.setOnClickListener(this);
        cpuButton=(Button)view.findViewById(R.id.btn_cpu);
        cpuButton.setOnClickListener(this);
        deviceIdButton=(Button)view.findViewById(R.id.btn_device_id);
        deviceIdButton.setOnClickListener(this);
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
        int id=view.getId();
        if(id==screenButton.getId()){

        }else if(id==systemButton.getId()){

        }else if(id==hardwareButton.getId()){

        }else if(id==cpuButton.getId()){

        }else if(id==networkButton.getId()){

        }else if(id==deviceIdButton.getId()){
            DeviceUtil.getDeviceId(getActivity().getApplication());

        }
    }
}
