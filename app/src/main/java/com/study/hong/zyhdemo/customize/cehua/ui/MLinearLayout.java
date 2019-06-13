package com.study.hong.zyhdemo.customize.cehua.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by 洪 on 2019/5/8.
 */

public class MLinearLayout extends LinearLayout {
    public MLinearLayout(Context context) {
        super(context);
    }

    public MLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private SlideDragLayout slideMenu;
    public void setSlideMenu(SlideDragLayout slideMenu){
        this.slideMenu = slideMenu;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(slideMenu!=null && slideMenu.getCurrentState()== SlideDragLayout.DragState.Open){
            //如果slideMenu打开则应该拦截并消费掉事件
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(slideMenu!=null && slideMenu.getCurrentState()== SlideDragLayout.DragState.Open){
            if(event.getAction()==MotionEvent.ACTION_UP){
                //抬起则应该关闭slideMenu
                slideMenu.close();
            }

            //如果slideMenu打开则应该拦截并消费掉事件
            return true;
        }
        return super.onTouchEvent(event);
    }
}
