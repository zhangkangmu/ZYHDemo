package com.study.hong.zyhdemo.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.base.BaseFragment;
import com.study.hong.zyhdemo.frame.EventBus.EventBusMain;
import com.study.hong.zyhdemo.frame.glide.GlideMain;
import com.study.hong.zyhdemo.frame.okhttp.fragment.OKHttpFragment;
import com.study.hong.zyhdemo.frame.pulltorefresh.LauncherActivity;
import com.study.hong.zyhdemo.frame.recyclerview.RecyclerViewMian;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * Created by 洪 on 2019/4/30.
 * 主页面
 */

public class Frame extends BaseFragment implements View.OnClickListener{

    private TextView okhttp;
    private TextView xutils3;
    private TextView retrofit2;
    private TextView fresco;
    private TextView glide;
    private TextView greendao;
    private TextView rxJava;
    private TextView evevt_bus;
    private TextView recyclerview;
    private TextView pullrefresh;

    public static Frame newInstance() {
        Bundle args = new Bundle();
        Frame fragment = new Frame();
        fragment.setArguments(args);
        Log.d("zyh","Frame newInstance");
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_main_fragment, container, false);
        okhttp = view.findViewById(R.id.okhttp);
        xutils3 = view.findViewById(R.id.xutils3);
        retrofit2 = view.findViewById(R.id.retrofit2);
        fresco = view.findViewById(R.id.Fresco);
        glide = view.findViewById(R.id.glide);
        greendao = view.findViewById(R.id.greendao);
        rxJava = view.findViewById(R.id.rxJava);
        evevt_bus = view.findViewById(R.id.evevt_bus);
        recyclerview = view.findViewById(R.id.recyclerview);
        pullrefresh = view.findViewById(R.id.pullrefresh);
        Log.d("zyh","Frame onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListen();
    }

    private void initListen() {
        okhttp.setOnClickListener(this);
        xutils3.setOnClickListener(this);
        retrofit2.setOnClickListener(this);
        fresco.setOnClickListener(this);
        glide.setOnClickListener(this);
        greendao.setOnClickListener(this);
        rxJava.setOnClickListener(this);
        evevt_bus.setOnClickListener(this);
        recyclerview.setOnClickListener(this);
        pullrefresh.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.okhttp) {
            start(OKHttpFragment.newInstance());
        } else if (i == R.id.xutils3) {
            Toast.makeText(_mActivity,"xutils3",Toast.LENGTH_SHORT).show();
        }else if (i==R.id.retrofit2){
            Toast.makeText(_mActivity,"retrofit2",Toast.LENGTH_SHORT).show();
        }else if (i==R.id.Fresco){
            Toast.makeText(_mActivity,"Fresco",Toast.LENGTH_SHORT).show();
        }else if (i==R.id.greendao){
            Toast.makeText(_mActivity,"greendao",Toast.LENGTH_SHORT).show();
        }else if (i==R.id.rxJava){
            Toast.makeText(_mActivity,"rxJava",Toast.LENGTH_SHORT).show();
        }else if (i==R.id.evevt_bus){
            startActivity(new Intent(getActivity(), EventBusMain.class));
        } else if (i == R.id.recyclerview) {
            startActivity(new Intent(getActivity(), RecyclerViewMian.class));
        } else if (i == R.id.glide) {
            startActivity(new Intent(getActivity(), GlideMain.class));
        }else if (i == R.id.pullrefresh) {
            startActivity(new Intent(getActivity(), LauncherActivity.class));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
