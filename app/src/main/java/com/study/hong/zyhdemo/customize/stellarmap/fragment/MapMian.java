package com.study.hong.zyhdemo.customize.stellarmap.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.study.hong.zyhdemo.customize.stellarmap.adapter.StellarMapAdapter;
import com.study.hong.zyhdemo.customize.stellarmap.ui.ShakeListener;
import com.study.hong.zyhdemo.customize.stellarmap.ui.StellarMap;
import com.study.hong.zyhdemo.customize.utils.Utils;
import com.study.hong.zyhdemo.customize.utils.inter.StellarOnClick;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by hong on 2019/5/31.
 */

public class MapMian extends SupportFragment implements StellarOnClick {
    private ArrayList<String> data=new  ArrayList<String>();

    public static MapMian newInstance() {
        Bundle args = new Bundle();
        MapMian fragment = new MapMian();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i=0;i<50;i++){
            data.add("这是第"+i+"个应用");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final StellarMap stellar = new StellarMap(_mActivity);
        StellarMapAdapter adapter=new StellarMapAdapter();
        adapter.setContext(_mActivity);
        adapter.setData(data);
        adapter.setStellarOnClick(this);
        stellar.setAdapter(adapter);
        // 随机方式, 将控件划分为9行6列的的格子, 然后在格子中随机展示
        stellar.setRegularity(6, 9);

        // 设置内边距10dp
        int padding = Utils.dip2px(10,_mActivity);
        stellar.setInnerPadding(padding, padding, padding, padding);

        // 设置默认页面, 第一组数据
        stellar.setGroup(0, true);

        ShakeListener shake = new ShakeListener(_mActivity);
        shake.setOnShakeListener(new ShakeListener.OnShakeListener() {

            //设置摇晃事件
            @Override
            public void onShake() {
                stellar.zoomIn();// 跳到下一页数据
            }
        });

        return stellar;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onclick(String name) {
        Toast.makeText(_mActivity,name,Toast.LENGTH_SHORT).show();
    }
}
