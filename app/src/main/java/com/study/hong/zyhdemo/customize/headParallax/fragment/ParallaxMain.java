package com.study.hong.zyhdemo.customize.headParallax.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.customize.headParallax.ui.ParallaxListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 洪 on 2019/5/14.
 * 头部视差main
 * 知识点：setOverScrollMode//永远不显示蓝色阴影
 */

public class ParallaxMain extends SupportFragment {
    @BindView(R.id.listview)
    ParallaxListView listview;
    Unbinder unbinder;
    private  String [] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };
    public static ParallaxMain newInstance() {
        Bundle args = new Bundle();
        ParallaxMain fragment = new ParallaxMain();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customize_parallax_mian, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View inflate = View.inflate(getContext(), R.layout.customize_parallax_head, null);
        //永远不显示蓝色阴影
        listview.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        ImageView imageView = inflate.findViewById(R.id.imageView);
        listview.setImageView(imageView);
        listview.addHeaderView(inflate);
        listview.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,indexArr));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
