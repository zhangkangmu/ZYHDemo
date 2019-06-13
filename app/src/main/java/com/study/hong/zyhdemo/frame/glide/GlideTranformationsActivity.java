package com.study.hong.zyhdemo.frame.glide;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.frame.glide.adapter.GlideTranformationsAdapter;


public class GlideTranformationsActivity extends Activity {

    private RecyclerView mRvGlideTransformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_tranformations);
        mRvGlideTransformations = findViewById(R.id.rv_glide_transformations);
        initData();
    }

    private void initData() {
        // 初始化Recyclerview
        GlideTranformationsAdapter glideTranformationsAdapter = new GlideTranformationsAdapter(this);
        mRvGlideTransformations.setAdapter(glideTranformationsAdapter);
        mRvGlideTransformations.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}
