package com.study.hong.zyhdemo.frame.glide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.study.hong.zyhdemo.R;


/**
 * Created by hong on 2019/6/5.
 */

public class GlideMain extends Activity implements View.OnClickListener{

    private Button mBtGlideBase;
    private Button mBtGlideRecyclerview;
    private Button mBtGlideTranfromations;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initID();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        //基本用法
        if (i == R.id.bt_glide_base) {
            startActivity(new Intent(this, GlideBaseActivity.class));
            //RecyclerView中加载图片
        } else if (i == R.id.bt_glide_recyclerview) {
            startActivity(new Intent(this, GlideRecyclerviewActivity.class));
            //图片变换
        } else if (i == R.id.bt_glide_tranfromations) {
            startActivity(new Intent(this, GlideTranformationsActivity.class));
        }
    }

    private void initID() {
        setContentView(R.layout.glide_main);
        mBtGlideBase = findViewById(R.id.bt_glide_base);
        mBtGlideRecyclerview = findViewById(R.id.bt_glide_recyclerview);
        mBtGlideTranfromations = findViewById(R.id.bt_glide_tranfromations);
        mBtGlideBase.setOnClickListener(this);
        mBtGlideRecyclerview.setOnClickListener(this);
        mBtGlideTranfromations.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
