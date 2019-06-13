package com.study.hong.zyhdemo.customize.cehua.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.FloatEvaluator;
import com.nineoldandroids.animation.IntEvaluator;
import com.nineoldandroids.view.ViewHelper;
import com.study.hong.zyhdemo.customize.utils.ColorUtil;

/**
 * Created by 洪 on 2019/5/7.
 * TypeEvaluator是一个抽象计算类借口，里面有4个方法实现了他，分被是：
 * ArgbEvaluator：rgb颜色计算
 * FloatEvaluator：float计算器
 *IntEvaluator：int计算器
 * RectEvaluator:矩形计算器
 */

public class SlideDragLayout extends FrameLayout {

    private ViewDragHelper viewDragHelper;
    private View mainView;
    private View menuView;
    private int width;  //屏幕宽
    private float dragRange;  //拖拽范围
    private FloatEvaluator floatEvaluator;   //安卓float计算器，主要做一些范围值之间的计算
    private IntEvaluator intEvaluator;   //安卓float计算器，主要做一些范围值之间的计算
    private ArgbEvaluator argbEvaluator;   //安卓float计算器，主要做一些范围值之间的计算

    public SlideDragLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public SlideDragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideDragLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 该方法在onMeasure执行完之后执行，那么可以在该方法中初始化自己和子View的宽高
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        dragRange = 0.6f * width;
    }

    private void init() {
        viewDragHelper = ViewDragHelper.create(this, callback);
        floatEvaluator = new FloatEvaluator();
        intEvaluator =new IntEvaluator();
        argbEvaluator=new ArgbEvaluator();
    }

    //一般执行5个方法
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child==mainView || child==menuView;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return (int) dragRange;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == mainView) {
                //往右拉，出了边界，限制mainView的左边
                if (left < 0) {
                    left = 0;
                    //限制mainView的右边
                } else if (left > dragRange) {
                    left = (int) dragRange;
                }
            }
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if (changedView==menuView){
                menuView.layout(0,0,menuView.getMeasuredWidth(),menuView.getMeasuredHeight());
                int newLeft=mainView.getLeft()+dx;
                if (newLeft < 0) newLeft=0;
                if (newLeft>dragRange) newLeft=(int)dragRange;
                mainView.layout(newLeft,0,newLeft+mainView.getMeasuredWidth(),mainView.getMeasuredHeight());
            }
            //1.计算view移动的百分比
            float fraction = mainView.getLeft()/dragRange;
            //2.执行一系列的伴随动画
            executeAnim(fraction);
            //3.根据fraction判断开关，设置回调
            if (fraction == 0f && currentState!= DragState.Close) {
                //更改状态为关闭，并回调关闭的方法
                currentState = DragState.Close;
                if (mOnDragChangeListen != null)  mOnDragChangeListen.onClose();
                //这里有一个bug，是fraction一直不为1.因此此处设为
            } else if (fraction == 0.99999994f  && currentState!= DragState.Open) {
                //更改状态为打开，并回调打开的方法
                currentState = DragState.Open;
                if (mOnDragChangeListen != null) mOnDragChangeListen.onOpen();
            }
            if (mOnDragChangeListen != null) mOnDragChangeListen.onDraging(fraction);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (mainView.getLeft()<dragRange/2){
                //在左半边
                close();
            }else{
                //在右半边
                open();
            }
            //处理用户的稍微滑动
            if(xvel>200 && currentState!= DragState.Open){
                open();
            }else if (xvel<-200 && currentState!= DragState.Close) {
                close();
            }
        }
    };

    /**
     * 执行伴随动画
     * @param fraction  滑行的百分比
     *  floatEvaluator:安卓计算器
     */
    private void executeAnim(float fraction) {
        //fraction:0-1
        //缩小mainView
//		float scaleValue = 0.8f+0.2f*(1-fraction);//1-0.8f
        ViewHelper.setScaleX(mainView, floatEvaluator.evaluate(fraction,1f,0.8f));
        ViewHelper.setScaleY(mainView, floatEvaluator.evaluate(fraction,1f,0.8f));

        //改变menuView
        //平移
        ViewHelper.setTranslationX(menuView, intEvaluator.evaluate(fraction,-menuView.getMeasuredWidth()/2,0));
        //放大menuView
        ViewHelper.setScaleX(menuView,floatEvaluator.evaluate(fraction,0.5f,1f));
        ViewHelper.setScaleY(menuView,floatEvaluator.evaluate(fraction,0.5f,1f));
        //改变menuView的透明度
        ViewHelper.setAlpha(menuView,floatEvaluator.evaluate(fraction,0.3f,1f));
        //让一个黑色覆盖在上面，模式选择这个：PorterDuff.Mode.SRC_OVER)
        getBackground().setColorFilter((Integer) ColorUtil.evaluateColor(fraction,Color.BLACK,Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);
    }

    public void open() {
        viewDragHelper.smoothSlideViewTo(mainView,(int) dragRange,mainView.getTop());
        ViewCompat.postInvalidateOnAnimation(SlideDragLayout.this);
    }

    public void close() {
        viewDragHelper.smoothSlideViewTo(mainView,0,mainView.getTop());
        ViewCompat.postInvalidateOnAnimation(SlideDragLayout.this);
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(SlideDragLayout.this);
        }
    }

    //交给父类处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //简单的异常处理
        if(getChildCount()!=2){
            throw new IllegalArgumentException("只能两个布局");
        }
        menuView = getChildAt(0);
        mainView = getChildAt(1);
    }

    public DragState getCurrentState() {
        return currentState;
    }

    //定义状态常量
    enum DragState {
        Open, Close;
    }

    private DragState currentState = DragState.Close;//当前SlideMenu的状态默认是关闭的

    //回调
    public interface OnDragChangeListen {
        /**
         * 关闭的回调
         */
        void onClose();
        /**
         * 打开的回调
         */
        void onOpen();
        /**
         * 正在拖拽中的回调
         */
        void onDraging(float fraction);
    }

    private OnDragChangeListen mOnDragChangeListen;

    public void setOnDragChangeListen(OnDragChangeListen onDragChangeListen) {
        this.mOnDragChangeListen = onDragChangeListen;
    }
}