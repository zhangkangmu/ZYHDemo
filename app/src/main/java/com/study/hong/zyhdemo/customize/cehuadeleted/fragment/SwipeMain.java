package com.study.hong.zyhdemo.customize.cehuadeleted.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;


import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.customize.cehuadeleted.adapter.MyAdapter;
import com.study.hong.zyhdemo.customize.cehuadeleted.ui.SwipeLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 洪 on 2019/5/14.
 */

public class SwipeMain extends SupportFragment {
    @BindView(R.id.listview)
    ListView listview;
    Unbinder unbinder;
    private MyAdapter adapter;
    private ArrayList<String> list = new ArrayList<String>();
    public static SwipeMain newInstance() {
        Bundle args = new Bundle();
        SwipeMain fragment = new SwipeMain();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customize_cehuadeleted_mian, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListView();
    }

    private void initListView() {
        adapter = new MyAdapter();
        adapter.setContext(getContext());
        initDate();
        adapter.setList(list);
        listview.setAdapter(adapter);
        listListener();
    }

    private void listListener() {
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    //如果垂直滑动，则需要关闭已经打开的layout
                    SwipeLayoutManager.getInstance().closeCurrentLayout();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initDate() {
        for (int i = 0; i < 30; i++) {
            list.add(i + "");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
