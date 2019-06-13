package com.study.hong.zyhdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.study.hong.zyhdemo.utils.LogUtil;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by hong on 2019/6/13.
 */

public class BaseActivity extends SupportActivity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG,getClass().getSimpleName() + "--onCreate");
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onDestroy");
    }
}
