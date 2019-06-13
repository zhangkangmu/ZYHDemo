package com.study.hong.zyhdemo.customize.quickindex.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.study.hong.zyhdemo.customize.utils.Utils;


/**
 * Created by 洪 on 2019/5/9.
 */

public class QuickIndexBar extends View {
    private String [] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };
    private Paint paint;
    private int width;
    private float cellHeight;

    public QuickIndexBar(Context context) {
        super(context);
        init();
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //设置抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(16);
        //设置文本的起点是文字边框底边的中心
        paint.setTextAlign(Paint.Align.CENTER);
    }

    //onMesure==》onSizeChanged ==》onlayout
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        //得到一个格子的高度
        cellHeight = getMeasuredHeight() *1f/ indexArr.length;   //这里注意：有1f，否则会有省去精确度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < indexArr.length; i++) {
            paint.setColor(lastIndex==i?Color.BLACK:Color.WHITE);
            //计算每一个字母所在的区域
            //绘制文本y坐标:格子高度一半+文字高度的一半+position*格子高度
            float y = cellHeight/2 + Utils.getTextHeight(indexArr[i],paint)/2 + i*cellHeight;
            canvas.drawText(indexArr[i], width/2, y, paint);
        }
    }

    private int lastIndex = -1;//记录上次的触摸字母的索引
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y =event.getY();
                int index = (int) (y / cellHeight);
                if (lastIndex != index) {
                    if (index <= 25) {
//                Log.d("zyh",indexArr[index]);
                        if (mOnTouchListener != null) {
                            mOnTouchListener.onTouchLetter(indexArr[index]);
                        }
                    }
                }
                lastIndex=index;
                break;
            case MotionEvent.ACTION_UP:
                //重置lastIndex
                lastIndex = -1;
                break;
            default:
                break;
        }
        //引起重绘
        invalidate();
        return true;
    }

    private OnTouchListener mOnTouchListener;

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    public interface OnTouchListener {
        //注意要把触摸到的字符回调回去给别人用
        void onTouchLetter(String letter);
}
}
