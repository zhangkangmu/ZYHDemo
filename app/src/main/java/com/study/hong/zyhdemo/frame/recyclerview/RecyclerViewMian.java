package com.study.hong.zyhdemo.frame.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.frame.recyclerview.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by hong on 2019/6/5.
 */

public class RecyclerViewMian extends Activity implements View.OnClickListener {
    private Button mBtnAdd;
    private Button mBtnDelete;
    private Button mBtnList;
    private Button mBtnGrid;
    private Button mBtnFlow;
    private RecyclerView mRecyclerview;

    private ArrayList<String> datas;
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        linstenAdapter();
    }

    private void linstenAdapter() {
        adapter = new MyRecyclerViewAdapter(datas,RecyclerViewMian.this);
        //false表示不排序
//        mRecyclerview.setLayoutManager(new LinearLayoutManager(RecyclerViewMian.this, LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(adapter);

        //添加RecyclerView的分割线,DividerListItemDecoration是一个自定义的分割线
        mRecyclerview.addItemDecoration(new DividerListItemDecoration(this,DividerListItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
            }
        });

        //设置动画
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    //设置集合的数据
    private void initData() {
        datas = new ArrayList<>();
        //准备数据集合
        for (int i=0;i<100;i++){

            datas.add("Content_"+i);
        }
    }

    //找到id
    private void initView() {
        setContentView(R.layout.recyclerview_mian);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnList = findViewById(R.id.btn_list);
        mBtnGrid = findViewById(R.id.btn_grid);
        mBtnFlow = findViewById(R.id.btn_flow);
        mRecyclerview = findViewById(R.id.recyclerview);
        mBtnAdd.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnList.setOnClickListener(this);
        mBtnGrid.setOnClickListener(this);
        mBtnFlow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_add) {
            adapter.addItem(0,"New_Content");
            mRecyclerview.scrollToPosition(0);
        } else if (i == R.id.btn_delete) {
            adapter.removeItem( 0);
        } else if (i == R.id.btn_list) {
          //设置List类型效果
            mRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        } else if (i == R.id.btn_grid) {
            //设置Grid类型效果
            mRecyclerview.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
//                recyclerview.scrollToPosition(99);
        } else if (i == R.id.btn_flow) {
            //设置瀑布流类型效果
            mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        }
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
