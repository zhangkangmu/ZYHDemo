package com.study.hong.zyhdemo.features.cotrolswitch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shuaihong on 2019/2/27.
 * 自定义控件，展示开关按钮的view
 */

public class SwitchView extends View {

    private boolean mSwitchState=false;
    //背景图片
    private Bitmap switchBackground;
    //滑块的图片
    private Bitmap slideButtonDrawable;
    private Paint paint;
    private float currentX;
    //监听器
    private OnSwitchStateUpdataListener onSwitchStateUpdataListener;

    public SwitchView(Context context) {
        super(context);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //设置自定义属性
        String namespace = "http://schemas.android.com/apk/res/com.hong.zyh.customizecomponent.cotrolswitch";
        int switch_background = attrs.getAttributeResourceValue(namespace, "switch_background", -1);
        int slide_button = attrs.getAttributeResourceValue(namespace, "slide_button", -1);

        mSwitchState = attrs.getAttributeBooleanValue(namespace, "switch_state", false);
        setSwitchBackgroundResource(switch_background);
        setSlideButtonResource(slide_button);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //创造画笔，提供给onDraw（）用
        paint = new Paint();
    }

    /**
     * @param switchBackgroundResource
     * 设置背景
     */
    public void setSwitchBackgroundResource(int switchBackgroundResource) {
//        this.switchBackgroundResource = switchBackgroundResource;
        switchBackground = BitmapFactory.decodeResource(getResources(), switchBackgroundResource);
    }

    /**
     * @param slideButtonResource
     *设置滑动按钮样子
     */
    public void setSlideButtonResource(int slideButtonResource) {
//        this.slideButtonResource = slideButtonResource;
        slideButtonDrawable = BitmapFactory.decodeResource(getResources(), slideButtonResource);
    }

    /**
     * @param mSwitchState
     * 设置是否可以滑动
     */
    public void setmSwitchState(boolean mSwitchState) {
        this.mSwitchState = mSwitchState;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);   //这个是原本的，需要自己测量
        //先放背景，这里是先测量设置背景的大小
        setMeasuredDimension(switchBackground.getWidth(),switchBackground.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);  //原本实现父类的方法
        //1. 绘制背景
        canvas.drawBitmap(switchBackground, 0, 0, paint);
        // 2. 绘制滑块
        if (isTouchMode) {
            //currentX是滑块顶部x坐标，但是我们要让他点击的时候控制中间开始滑动
            float touchLeft = currentX-slideButtonDrawable.getWidth()/2.0f;

            //设置滑块最大滑动距离
            int maxLeft=switchBackground.getWidth()-slideButtonDrawable.getWidth();
            if (touchLeft<0){
                touchLeft=0;
                //注意，这里必须是else if，不能是else
            }else if(touchLeft>maxLeft){
                touchLeft=maxLeft;
            }
            canvas.drawBitmap(slideButtonDrawable,touchLeft,0,paint);
        } else {
            if (mSwitchState) {
                //当开关打开的时候要往右边移动一点，移动的距离是：背景-滑块的长度
                int newLeft = switchBackground.getWidth() - slideButtonDrawable.getWidth();
                canvas.drawBitmap(slideButtonDrawable, newLeft, 0, paint);
            } else {
                canvas.drawBitmap(slideButtonDrawable, 0, 0, paint);  //关闭的时候
            }
        }
    }

    //建立的变量记录是否触发了，通过这个值来让判断滑块的位置
    boolean isTouchMode = false;  //如果是false就定在一个位置，如果是true就改变w位置
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            //按下的时候触发
            case MotionEvent.ACTION_DOWN:
                isTouchMode=true;
                Log.d("onTouchEventACTION_DOWN",event.getX()+"");
                //滑块的当前位置
                currentX = event.getX();
                break;
            //移动的时候触发
            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                break;
            //抬起的时候触发
            case MotionEvent.ACTION_UP:
                isTouchMode=false;
                currentX = event.getX();

                float center = switchBackground.getWidth() / 2.0f;

                // 根据当前按下的位置, 和控件中心的位置进行比较.
                boolean state = currentX > center;

                //配上开关接口
                if(state!=mSwitchState && onSwitchStateUpdataListener != null){
                    onSwitchStateUpdataListener.OnStateUpdate(state);
                }

                mSwitchState = state;

                break;
            default:
                break;
        }

        // 重绘界面
        invalidate(); // 会引发onDraw()被调用, 里边的变量会重新生效.界面会更新

        // 消费了用户的触摸事件, 才可以收到其他的事件，因此这里必须是true
        return true;
    }

    //定义一个接口方便外部调用
    public interface OnSwitchStateUpdataListener{
        // 状态回调, 把当前状态传出去
        void OnStateUpdate(boolean state);
    }

    //设置一个方法让外部调用这个方法，需要调用new一个OnSwitchStateUpdataListener做内部类
    public void setSwitchStateUpdateListener(OnSwitchStateUpdataListener onSwitchStateUpdataListener){
        this.onSwitchStateUpdataListener=onSwitchStateUpdataListener;
    }
}
