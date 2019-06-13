package com.study.hong.zyhdemo.customize.stellarmap.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;


import com.study.hong.zyhdemo.customize.stellarmap.ui.StellarMap;
import com.study.hong.zyhdemo.customize.utils.inter.StellarOnClick;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hong on 2019/5/31.
 */

public class StellarMapAdapter implements StellarMap.Adapter {
    private ArrayList<String> data;
    private Context mContext;
    private StellarOnClick stellarOnClick;

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }
    // 返回组的个数
    @Override
    public int getGroupCount() {
        return 2;
    }

    // 返回某组的item个数
    @Override
    public int getCount(int group) {
        int count = data.size() / getGroupCount();
        if (group == getGroupCount() - 1) {
            // 最后一页, 将除不尽,余下来的数量追加在最后一页, 保证数据完整不丢失
            count += data.size() % getGroupCount();
        }

        return count;
    }

    // 初始化布局
    @Override
    public View getView(int group, int position, View convertView) {
        // 因为position每组都会从0开始计数, 所以需要将前面几组数据的个数加起来,才能确定当前组获取数据的角标位置
        position += (group) * getCount(group - 1);
        final String keyword = data.get(position);
        TextView view = new TextView(mContext);
        view.setText(keyword);

        Random random = new Random();
        // 随机大小, 16-25
        int size = 16 + random.nextInt(10);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

        // 随机颜色
        // r g b, 0-255 -> 30-230, 颜色值不能太小或太大, 从而避免整体颜色过亮或者过暗
        int r = 30 + random.nextInt(200);
        int g = 30 + random.nextInt(200);
        int b = 30 + random.nextInt(200);

        view.setTextColor(Color.rgb(r, g, b));

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stellarOnClick.onclick(keyword);
            }
        });

        return view;
    }

    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
        if (isZoomIn) {
            // 往下滑加载上一页
            if (group > 0) {
                group--;
            } else {
                // 跳到最后一页
                group = getGroupCount() - 1;
            }
        } else {
            // 往上滑加载下一页
            if (group < getGroupCount() - 1) {
                group++;
            } else {
                // 跳到第一页
                group = 0;
            }
        }
        return group;
    }

    public void setStellarOnClick(StellarOnClick adStellarOnClick) {
        this.stellarOnClick = adStellarOnClick;
    }
}
