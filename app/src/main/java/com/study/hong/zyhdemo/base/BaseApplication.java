package com.study.hong.zyhdemo.base;

import android.app.Application;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * Created by hong on 2019/6/13.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFragmentation();//init Fragmentation
    }

    private void initFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true)
                .install();
    }
}
