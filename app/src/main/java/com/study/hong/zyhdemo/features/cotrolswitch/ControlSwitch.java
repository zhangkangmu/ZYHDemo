package com.study.hong.zyhdemo.features.cotrolswitch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.study.hong.zyhdemo.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by shuaihong on 2019/2/27.
 * 控制开关的主页面
 */

public class ControlSwitch extends SupportFragment {

    private SwitchView switch_view;

    public static ControlSwitch newInstance() {
        Bundle args = new Bundle();
        ControlSwitch fragment = new ControlSwitch();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.switch_view, container, false);
        initID(view);
        return view;
    }

    private void initID(View view) {
        switch_view = view.findViewById(R.id.switch_view);
        //自定义属性没有好因为switch_view.xml的命名空间一直报错，有空再改
        //设置背景
        switch_view.setSwitchBackgroundResource(R.drawable.cotrols_switch_switch_background);
        //设置滑动按钮样子
        switch_view.setSlideButtonResource(R.drawable.controls_switch_slide_button);
        //设置是否可以滑动
        switch_view.setmSwitchState(true);

        switch_view.setSwitchStateUpdateListener(new SwitchView.OnSwitchStateUpdataListener() {
            @Override
            public void OnStateUpdate(boolean state) {
                Toast.makeText(_mActivity, "state" + state, Toast.LENGTH_SHORT).show();
            }
        });
    }
}