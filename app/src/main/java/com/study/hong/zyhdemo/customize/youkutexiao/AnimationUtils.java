package com.study.hong.zyhdemo.customize.youkutexiao;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by shuaihong on 2019/2/21.
 * 这个工具类是用于操控动画的旋转的
 */

public class AnimationUtils {

    // 正在运行的动画个数
    public static int runningAnimationCount = 0;

    //旋转出去的动画
    public static void rotateOutAnim(RelativeLayout layout,long delay) {
        int childCount = layout.getChildCount();
        // 如果控件被隐藏了，则找到所有的子View, 禁用
        for (int i = 0; i < childCount; i++) {
            layout.getChildAt(i).setEnabled(false);
        }
        Log.d("rotateOutAnim","rotateOutAnim");
        RotateAnimation rotateAnimation = new RotateAnimation(
                0f, -180f, // 开始, 结束的角度, 逆时针是减少
                Animation.RELATIVE_TO_SELF, 0.5f,  // 相对的x坐标点(指定旋转中心x值)
                Animation.RELATIVE_TO_SELF, 1.0f);// 相对的y坐标点(指定旋转中心y值)
        //开始设置动画时长
        rotateAnimation.setDuration(500);
        //最后停留在结束动画页面
        rotateAnimation.setFillAfter(true);
        //为了满足第二层和第三层不同时旋转出去，因此增加一个延迟的效果
        rotateAnimation.setStartOffset(delay);
        //监听旋转动画，防止重复旋转，或者快递点击多个同时出去
        rotateAnimation.setAnimationListener(new MyAnimationListener());
        //那个控件开始执行动画
        layout.startAnimation(rotateAnimation);
    }


    /**
     * @param layout，转动的控件
     * 旋转进来的动画
     */
     public static void rotateInAnim(RelativeLayout layout,long delay) {

         int childCount = layout.getChildCount();
         // 如果控件被显示了，则找到所有的子View, 启用
         for (int i = 0; i < childCount; i++) {
             layout.getChildAt(i).setEnabled(true);
         }
        RotateAnimation rotateAnimation = new RotateAnimation(
                -180f, 0f, // 开始, 结束的角度, 顺时针是增加
                Animation.RELATIVE_TO_SELF, 0.5f,  // 相对的x坐标点(指定旋转中心x值)
                Animation.RELATIVE_TO_SELF, 1.0f); // 相对的y坐标点(指定旋转中心y值)
       //开始设置动画时长
        rotateAnimation.setDuration(500);
        //最后停留在结束动画页面
        rotateAnimation.setFillAfter(true);
        //为了满足第二层和第三层不同时旋转进来，因此增加一个延迟的效果
        rotateAnimation.setStartOffset(delay);
         //监听旋转动画，防止重复旋转，或者快递点击多个同时进来
         rotateAnimation.setAnimationListener(new MyAnimationListener());
        //那个控件开始执行动画
        layout.startAnimation(rotateAnimation);
    }

     /**
     *  执行监听播放动画的类，static是因为放置方法内调用找不到
      */
    static class MyAnimationListener implements Animation.AnimationListener{
        //开始执行动画执行的方法
        @Override
        public void onAnimationStart(Animation animation) {
            runningAnimationCount++;
        }
        //结束动画执行的方法
        @Override
        public void onAnimationEnd(Animation animation) {
            runningAnimationCount--;
        }
        //重复执行动画执行的方法
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
