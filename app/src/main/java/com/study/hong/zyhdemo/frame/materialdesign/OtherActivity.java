package com.study.hong.zyhdemo.frame.materialdesign;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.study.hong.zyhdemo.R;

import java.util.List;

/**
 * Created by hong on 2019/6/12.
 */

public class OtherActivity extends Activity {
    private List<Person> person;
    private ImageView mPersonImageView;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private AppBarLayout mAppbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_change);
        //注意：这个设置导航栏透明会导致有阴影，整体往后移
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        mPersonImageView = findViewById(R.id.person_image_view);
        mToolbar = findViewById(R.id.toolbar);
        mCollapsingToolbar = findViewById(R.id.collapsing_toolbar);
        mAppbar = findViewById(R.id.appbar);

        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mToolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.colorPrimary),Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange()));
                mPersonImageView.setAlpha(1-Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange());
                Log.d("zyh",""+Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange());
            }
        });

    }


    /** 根据百分比改变颜色透明度 */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }


}
