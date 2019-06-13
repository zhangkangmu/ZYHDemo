package com.study.hong.zyhdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.hong.zyhdemo.utils.LogUtil;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by hong on 2019/6/13.
 */

public class BaseFragment extends SupportFragment {
    private static final String TAG = "BaseFragment";
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG,getClass().getSimpleName() + "--onCreate");
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG,getClass().getSimpleName() + "--onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.d(TAG,getClass().getSimpleName() + "--onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d(TAG,getClass().getSimpleName() + "--onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d(TAG,getClass().getSimpleName() + "--onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d(TAG,getClass().getSimpleName() + "--onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d(TAG,getClass().getSimpleName() + "--onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d(TAG,getClass().getSimpleName() + "--onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d(TAG,getClass().getSimpleName() + "--onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG,getClass().getSimpleName() + "--onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d(TAG,getClass().getSimpleName() + "--onDetach");
    }
    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }
}
