package com.study.hong.zyhdemo.customize.nianxingkongjian.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.study.hong.zyhdemo.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 洪 on 2019/5/15.
 */

public class GooMian extends SupportFragment{
    public static GooMian newInstance() {
        Bundle args = new Bundle();
        GooMian fragment = new GooMian();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customize_nianxingkongjian_mian, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
