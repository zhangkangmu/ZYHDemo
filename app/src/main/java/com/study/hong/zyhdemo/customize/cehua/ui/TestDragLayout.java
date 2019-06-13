package com.study.hong.zyhdemo.customize.cehua.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.study.hong.zyhdemo.customize.utils.ColorUtil;


/**
 * Created by 洪 on 2019/5/6.
 * 这是写侧滑面板写的一个人简单demo
 */

public class TestDragLayout extends FrameLayout {

    private View redView;// 红孩子
    private View blueView;// 蓝精灵
    private ViewDragHelper viewDragHelper;


    public TestDragLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public TestDragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestDragLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        viewDragHelper = ViewDragHelper.create(this, callback);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 让ViewDragHelper帮我们判断是否应该拦截
        boolean result = viewDragHelper.shouldInterceptTouchEvent(ev);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback callback =new ViewDragHelper.Callback(){

        /**
         * 用于判断是否捕获当前触摸childview
         * @param child
         * @param pointerId
         * @return true：就捕获并解析 false：不处理
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.d("zyh","tryCaptureView");
            return child==redView || child==blueView;
        }

        /**
         * 手指抬起的时候滑行的距离，不要返回0
         * 获取view水平方向的拖拽范围,但是目前不能限制边界,返回的值目前用在手指抬起的时候view缓慢移动的动画世界的计算上面; 最好不要返回0
         */
        @Override
        public int getViewHorizontalDragRange(View child) {
            return getMeasuredWidth()-child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getMeasuredHeight()-child.getMeasuredHeight();
        }

        /**
         *控制child在水平方向的移动
         * @param child 左右改变的child的view
         * @param left  clidview在距离左侧的位置
         * @param dx   左侧滑行的距离
         * @return  想让child的left变成的值
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //限制边界
            if (left < 0) {
                return 0;
            } else if (left > getMeasuredWidth() - child.getMeasuredWidth()) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }
            return left;
        }

        /**
         * 控制child在垂直方向的移动 top:
         * 表示ViewDragHelper认为你想让当前child的top改变的值,top=chile.getTop()+dy dy:
         * 本次child垂直方向移动的距离 return: 表示你真正想让child的top变成的值
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if (top < 0) {
                top = 0;
            } else if (top > getMeasuredHeight() - child.getMeasuredHeight()) {
                top = getMeasuredHeight() - child.getMeasuredHeight();
            }
            return top;
        }
        /**
         * 当child的位置改变的时候执行,一般用来做其他子View的伴随移动 changedView：位置改变的child
         * left：child当前最新的left
         * top: child当前最新的top
         * dx: 本次水平移动的距离
         * dy: 本次垂直移动的距离
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            //需求：蓝色移动的时候红色跟着移动，红色移动蓝色不需要
            if (changedView == blueView) {
                redView.layout(redView.getLeft() + dx, redView.getTop() + dy, redView.getRight() + dx, redView.getBottom() + dy);
            }
            //计算滑行的百分比例
            float fractionLeft = changedView.getLeft() * 1f / (getMeasuredWidth() - changedView.getMeasuredWidth());
//            float fractionTop = changedView.getTop() * 1f / (getMeasuredHeight() - changedView.getMeasuredHeight());
            executeAnim(fractionLeft);
//            executeAnim(fractionTop);

        }

        /**
         * 手指抬起的执行该方法， releasedChild：当前抬起的view
         * xvel: x方向的移动的速度 正：向右移动， 负：向左移动
         * yvel: y方向移动的速度
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            float center=getMeasuredWidth()/2-releasedChild.getMeasuredWidth()/2;
            if (center>releasedChild.getLeft()){
                viewDragHelper.smoothSlideViewTo(redView,0,releasedChild.getTop());
                ViewCompat.postInvalidateOnAnimation(TestDragLayout.this);
            }else {
                viewDragHelper.smoothSlideViewTo(redView,getMeasuredWidth(),releasedChild.getTop());
                ViewCompat.postInvalidateOnAnimation(TestDragLayout.this);
            }
        }
    };

    @Override
    public void computeScroll() {
       if (viewDragHelper.continueSettling(true)){
           ViewCompat.postInvalidateOnAnimation(TestDragLayout.this);
       }
    }

    /**
     * 执行伴随动画
     * @param fraction 移动的百分比
     */
    private void executeAnim(float fraction) {
        //fraction: 0 - 1
        //缩放
//        redView.setScaleX(fraction);
//        redView.setScaleY(fraction);
       //透明
//        redView.setAlpha(fraction);
        //旋转
//        redView.setRotation(360*fraction);  //z轴
//        redView.setRotationX(360*fraction);  //z轴
//        redView.setRotationY(360*fraction);  //z轴
        //设置过度颜色的渐变,需要使用这个工具类
        redView.setBackgroundColor((Integer) ColorUtil.evaluateColor(fraction,Color.RED,Color.GREEN));
    }


//    /**
//     * @param widthMeasureSpec  父视图发过来给子视图的限制条件
//     * @param heightMeasureSpec   父视图发过来给子视图的限制条件
//     */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // //要测量我自己的子View
        // // int size = getResources().getDimension(R.dimen.width);//100dp
        // // int measureSpec =
        // MeasureSpec.makeMeasureSpec(redView.getLayoutParams().width,MeasureSpec.EXACTLY);
        // // redView.measure(measureSpec,measureSpec);
        // // blueView.measure(measureSpec, measureSpec);

        // //如果说没有特殊的对子View的测量需求，可以用如下方法
        // measureChild(redView, widthMeasureSpec, heightMeasureSpec);
        // measureChild(blueView, widthMeasureSpec, heightMeasureSpec);
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int left = 0;
        int top = 0;
        redView.layout(left, top, left + redView.getMeasuredWidth(), top
                + redView.getMeasuredHeight());
        blueView.layout(left,redView.getMeasuredHeight() ,
                left + blueView.getMeasuredWidth(), redView.getBottom()
                        + blueView.getMeasuredHeight());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        redView = getChildAt(0);
        blueView = getChildAt(1);
    }
}
