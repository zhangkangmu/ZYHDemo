package com.study.hong.zyhdemo.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.base.BaseFragment;
import com.study.hong.zyhdemo.features.advertisement.Advertisement;
import com.study.hong.zyhdemo.features.cotrolswitch.ControlSwitch;
import com.study.hong.zyhdemo.features.xialaxuanzekuang.DropDown;


/**
 * Created by 洪 on 2019/6/4.
 */

public class FeaturesMain extends BaseFragment implements View.OnClickListener{

    private TextView tv_advertisement;
    private TextView tv_switch,tv_xialaxaunze;

    public static FeaturesMain newInstance() {
        Bundle args = new Bundle();
        FeaturesMain fragment = new FeaturesMain();
        fragment.setArguments(args);
        Log.d("zyh","FeaturesMain newInstance");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.features_mian_layout, container, false);
        tv_advertisement = view.findViewById(R.id.tv_advertisement);
        tv_switch = view.findViewById(R.id.tv_switch);
        tv_xialaxaunze = view.findViewById(R.id.tv_xialaxaunze);
        initListen();
        Log.d("zyh","FeaturesMain onCreateView");
        return view;
    }

    private void initListen() {
        tv_advertisement.setOnClickListener(this);
        tv_switch.setOnClickListener(this);
        tv_xialaxaunze.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_advertisement) {
            start(Advertisement.newInstance());
        }else if (i == R.id.tv_switch) {
            start(ControlSwitch.newInstance());
        }else if (i == R.id.tv_xialaxaunze) {
            startActivity(new Intent(getActivity(), DropDown.class));
        }
    }
}
