package com.study.hong.zyhdemo.customize;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.base.BaseFragment;
import com.study.hong.zyhdemo.customize.cehua.fragment.CehuaMainContent;
import com.study.hong.zyhdemo.customize.cehuadeleted.fragment.SwipeMain;
import com.study.hong.zyhdemo.customize.headParallax.fragment.ParallaxMain;
import com.study.hong.zyhdemo.customize.nianxingkongjian.fragment.GooMian;
import com.study.hong.zyhdemo.customize.quickindex.fragment.QuickIndexMain;
import com.study.hong.zyhdemo.customize.stellarmap.fragment.MapMian;
import com.study.hong.zyhdemo.customize.stringsort.fragment.StringSortMian;
import com.study.hong.zyhdemo.customize.youkutexiao.YouKu;
import com.study.hong.zyhdemo.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 洪 on 2019/5/1.
 */

public class CustomizeMain extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_qq_cehuamianban)
    TextView tvQqCehuamianban;
    @BindView(R.id.tv_quick_index)
    TextView tvQuickIndex;
    @BindView(R.id.tv_parallax)
    TextView tvParallax;
    @BindView(R.id.tv_swipelayout)
    TextView tvSwipelayout;
    @BindView(R.id.tv_gooview)
    TextView tvGooview;
    @BindView(R.id.stellar_map)
    TextView stellarMap;
    @BindView(R.id.string_sort)
    TextView stringSort;
    @BindView(R.id.youku_texiao)
    TextView youkuTexiao;

    public static CustomizeMain newInstance() {

        Bundle args = new Bundle();
        Log.d("zyh", "CustomizeMain newInstance");
        CustomizeMain fragment = new CustomizeMain();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customize_mian_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        Log.d("zyh", "CustomizeMain onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initOnClickListen();
    }

    private void initOnClickListen() {
//        tvQqCehuamianban.setOnClickListener(this);
//        tvquickindex.setOnClickListener(this);
//        tvParallax.setOnClickListener(this);
//        tvSwipelayout.setOnClickListener(this);
//        tvgooview.setOnClickListener(this);
//        stellarMap.setOnClickListener(this);
//        stringSort.setOnClickListener(this);
//        youku_texiao.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_qq_cehuamianban, R.id.tv_quick_index, R.id.tv_parallax, R.id.tv_swipelayout, R.id.tv_gooview, R.id.stellar_map, R.id.string_sort, R.id.youku_texiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_qq_cehuamianban:
                LogUtil.d("zyh","asdad");
                start(CehuaMainContent.newInstance());
                break;
            case R.id.tv_quick_index:
                start(QuickIndexMain.newInstance());
                break;
            case R.id.tv_parallax:
                start(ParallaxMain.newInstance());
                break;
            case R.id.tv_swipelayout:
                start(SwipeMain.newInstance());
                break;
            case R.id.tv_gooview:
                start(GooMian.newInstance());
                break;
            case R.id.stellar_map:
                start(MapMian.newInstance());
                break;
            case R.id.string_sort:
                start(StringSortMian.newInstance());
                break;
            case R.id.youku_texiao:
                Intent intent = new Intent(getActivity(), YouKu.class);
                startActivity(new Intent(intent));
                break;
        }
    }
//
//    @Override
//    public void onClick(View v) {
//        int i = v.getId();
//        if (i == R.id.tv_qq_cehuamianban) {
//            start(CehuaMainContent.newInstance());
//        } else if (i == R.id.tv_quick_index) {
//
//        } else if (i == R.id.tv_parallax) {
//            start(ParallaxMain.newInstance());
//        } else if (i == R.id.tv_swipelayout) {
//            start(SwipeMain.newInstance());
//        } else if (i == R.id.tv_gooview) {
//            start(GooMian.newInstance());
//        } else if (i == R.id.stellar_map) {
//            start(MapMian.newInstance());
//        }else if (i == R.id.string_sort) {
//            start(StringSortMian.newInstance());
//        }else if (i == R.id.youku_texiao) {
//            Intent intent = new Intent(getActivity(), YouKu.class);
//            startActivity(new Intent(intent));
//        }
//    }


}
