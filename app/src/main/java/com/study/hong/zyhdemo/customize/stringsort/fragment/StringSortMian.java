package com.study.hong.zyhdemo.customize.stringsort.fragment;

import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.hong.zyhdemo.customize.cehua.other.Constant;
import com.study.hong.zyhdemo.customize.stringsort.ui.FlowLayout;
import com.study.hong.zyhdemo.customize.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by hong on 2019/5/31.
 */

public class StringSortMian extends SupportFragment {
    private ArrayList<String> data=new ArrayList<String>();

    public static StringSortMian newInstance() {
        Bundle args = new Bundle();
        StringSortMian fragment = new StringSortMian();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> list = Arrays.asList(Constant.NAMES);
        data=new ArrayList(list);
        data.add("电视剧哈佛阿尔");
        data.add("大水坑立方米");
        data.add("电视剧哈佛阿尔");
        data.add("大");
        data.add("大数据破");
        data.add("驱蚊器翁多群无二道区翁所多");
        data.add("大萨达撒大所多撒大所多撒多撒");
        data.add(" ");
        data.add("打算");
        Log.d("zyh",data.size()+"");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // 支持上下滑动
            ScrollView scrollView = new ScrollView(_mActivity);
            FlowLayout flow = new FlowLayout(_mActivity);

            int padding = Utils.dip2px(10,_mActivity);
            flow.setPadding(padding, padding, padding, padding);// 设置内边距

            flow.setHorizontalSpacing(Utils.dip2px(6,_mActivity));// 水平间距
            flow.setVerticalSpacing(Utils.dip2px(8,_mActivity));// 竖直间距
        for (int i = 0; i < data.size(); i++) {
            final String keyword = data.get(i);
            TextView view = new TextView(_mActivity);
            view.setText(keyword);

            view.setTextColor(Color.BLACK);
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);// 18sp
            view.setPadding(padding, padding, padding, padding);
            view.setGravity(Gravity.CENTER);

            // 生成随机颜色
            Random random = new Random();
            int r = 30 + random.nextInt(200);
            int g = 30 + random.nextInt(200);
            int b = 30 + random.nextInt(200);

            int color = 0xffcecece;// 按下后偏白的背景色

//             GradientDrawable bgNormal = Utils.getGradientDrawable(
//             Color.rgb(r, g, b), Utils.dip2px(6,_mActivity));
//             GradientDrawable bgPress = Utils.getGradientDrawable(
//             color, Utils.dip2px(6,_mActivity));
//             StateListDrawable selector = Utils.getSelector(bgNormal,
//             bgPress);

            //前面注释的方法可以写成这样，传入正常的颜色以及按下的颜色，和半径
            StateListDrawable selector = Utils.getSelector(
                    Color.rgb(r, g, b), color, Utils.dip2px(6,_mActivity));
            view.setBackgroundDrawable(selector);

            flow.addView(view);

            // 只有设置点击事件, 状态选择器才起作用
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(_mActivity, keyword,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        scrollView.addView(flow);
        return scrollView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
