package com.study.hong.zyhdemo.customize.cehuadeleted.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by 洪 on 2019/5/14.
 */

public class SwipeLayout extends FrameLayout {

    private View contentView;
    private View deleteView;
    private int contentWidth;
    private int contentHeight;
    private int deleteWidth;
    private int deleteHeight;
    private ViewDragHelper viewDragHelper;

    public SwipeLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public SwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        viewDragHelper = ViewDragHelper.create(this, callback);
    }

    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child==contentView || child==deleteView;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return deleteHeight;    //这里不知道返回什么合适，不为0一般
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child==contentView){
                if (left>0) left=0;
                if (left<-deleteWidth) left=-deleteWidth;
            }else if (child==deleteView){
                if (left>contentWidth) left=contentWidth;
                if (left<contentWidth-deleteWidth) left=contentWidth-deleteWidth;
            }
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if (changedView==contentView){
                deleteView.layout(deleteView.getLeft()+dx,deleteView.getTop()+dy,
                        deleteView.getRight()+dx,deleteView.getBottom()+dy);
            }else if (changedView==deleteView){
                contentView.layout(contentView.getLeft()+dx,contentView.getTop()+dy,
                        contentView.getRight()+dx,contentView.getBottom()+dy);
            }
            //判断开和关闭的逻辑,主item在左侧
            if (contentView.getLeft()==0 && currentState!= SwipeState.Close){
                //说明要把state更改为关闭，也就是在左侧的时候应该让这个状态我关闭
                currentState = SwipeState.Close;
                //回调接口关闭的方法
                if (listener!=null){
                    listener.onClose(getTag());
                }
                //说明当前的SwipeLayout已经关闭，需要让Manager清空一下
                SwipeLayoutManager.getInstance().clearCurrentLayout();
            }else if (contentView.getLeft()==-deleteWidth && currentState!= SwipeState.Open) {
                //说明应该将state更改为开
                currentState = SwipeState.Open;

                //回调接口打开的方法
                if(listener!=null){
                    listener.onOpen(getTag());
                }
                //当前的Swipelayout已经打开，需要让Manager记录一下下
                SwipeLayoutManager.getInstance().setSwipeLayout(SwipeLayout.this);
            }

        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (contentView.getLeft()<-deleteWidth/2){
                //小于一半的时候应该打开
                open();
            }else {
                close();
            }
        }
    };

    public void close() {
        viewDragHelper.smoothSlideViewTo(contentView,0,contentView.getTop());
        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
    }

    public void open() {
        viewDragHelper.smoothSlideViewTo(contentView,-deleteWidth,contentView.getTop());
        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = viewDragHelper.shouldInterceptTouchEvent(ev);

        if (!SwipeLayoutManager.getInstance().isShouldSwipe(this)){
            SwipeLayoutManager.getInstance().closeCurrentLayout();
            return true;
        }

            return result;
    }

    private float downX, downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //不能滑动的时候
        if (!SwipeLayoutManager.getInstance().isShouldSwipe(this)) {
//            SwipeLayoutManager.getInstance().closeCurrentLayout();    //关闭的动作放在这里会卡，因此放在onInterceptTouchEvent里
            requestDisallowInterceptTouchEvent(true);
            //消费了ontouchevent
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //1.获取x和y方向移动的距离
                float moveX = event.getX();
                float moveY = event.getY();
                float delatX = moveX - downX;//x方向移动的距离
                float delatY = moveY - downY;//y方向移动的距离
                if (Math.abs(delatX)>Math.abs(delatY)){
                    //x大，这时候说明在侧滑，不需要截拦父类listview截拦
                    requestDisallowInterceptTouchEvent(true);
                }
                //更新downX，downY
                downX = moveX;
                downY = moveY;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        deleteView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        contentWidth = contentView.getMeasuredWidth();

        contentHeight = contentView.getMeasuredHeight();
        deleteWidth = deleteView.getMeasuredWidth();
        deleteHeight = deleteView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int contentRight = contentView.getMeasuredWidth();
        contentView.layout(0,0,contentWidth,contentHeight);
        deleteView.layout(contentRight,0,contentRight+deleteWidth,deleteHeight);
    }

    enum SwipeState{
        Open,Close;
    }
    private SwipeState currentState = SwipeState.Close;//当前默认是关闭状态

    private OnSwipeStateChangeListener listener;
    public void setOnSwipeStateChangeListener(OnSwipeStateChangeListener listener){
        this.listener = listener;
    }
    public interface OnSwipeStateChangeListener{
        void onOpen(Object tag);
        void onClose(Object tag);
    }
}
