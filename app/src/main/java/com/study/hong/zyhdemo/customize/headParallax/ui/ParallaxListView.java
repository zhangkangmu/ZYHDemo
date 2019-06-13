package com.study.hong.zyhdemo.customize.headParallax.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by 洪 on 2019/5/14.
 * 头部视差view
 * * 应用场景: 微信朋友圈, QQ空间, 微博个人展示，向下拉出，松开回弹
 * 功能实现:
 * 1. 重写overScrollBy  2. 松手之后执行动画, 类型估值器
 *
 * 运用到的知识点：overScrollBy   onGlobalLayout
 * 弹性的插值器：OvershootInterpolator    ValueAnimator：数值发生器
 */

public class ParallaxListView extends ListView {

    private int maxHeight;
    private int orignalHeight;

    public ParallaxListView(Context context) {
        super(context);
    }

    public ParallaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ImageView mImageView;

    public void setImageView(final ImageView imageView) {
        this.mImageView = imageView;
        //视图view监控
        mImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //显示的高度
                orignalHeight = mImageView.getHeight();
                int drawableHeight = mImageView.getDrawable().getIntrinsicHeight();//获得图片的真实高度
                maxHeight = orignalHeight >drawableHeight? orignalHeight *2:drawableHeight+400;
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    /**
     *在listview滑動到头部或者底部的时候执行，可以获得继续滑行的距离和方向
     * @param deltaX   继续滑动x方向的距离
     * @param deltaY   继续滑动Y方向的距离  负：表示顶部到头   正：表示底部到头
     * @param scrollX   一直为0
     * @param scrollY    一直为0   每一次重绘时，都会调用scrollTo（）重新设置mScrollY的值
     * @param scrollRangeX
     * @param scrollRangeY
     * @param maxOverScrollX  x方向最大可以滚动的距离
     * @param maxOverScrollY  y方向最大可以滚动的距离
     * @param isTouchEvent  true: 是手指拖动滑动     false:表示靠惯性滑动;
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //在顶部并且在滑动的时候
        if (isTouchEvent && deltaY < 0) {
        //我们需要不断的增加ImageView的高度
            if (mImageView!=null){
                int newHeight = mImageView.getHeight()-deltaY/3;
                if (maxHeight<newHeight){
                    newHeight=maxHeight;
                }
                mImageView.getLayoutParams().height=newHeight;
                //使ImageView的布局参数生效
                mImageView.requestLayout();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction()==MotionEvent.ACTION_UP){
        //需要将ImageView的高度缓慢恢复到最初高度
        //ValueAnimator:本身也不会提供任何一种动画。它简单的来说，就是一个数值发生器，它可以产生你想要的各种数值
        ValueAnimator animator = ValueAnimator.ofInt(mImageView.getHeight(), orignalHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mImageView.getLayoutParams().height=value;
                mImageView.requestLayout();  //使ImageView的布局参数生效
            }
        });
            animator.setInterpolator(new OvershootInterpolator(3));   //弹性的插值器
            animator.setDuration(350);
            animator.start();
        }
        return super.onTouchEvent(ev);
    }
}
