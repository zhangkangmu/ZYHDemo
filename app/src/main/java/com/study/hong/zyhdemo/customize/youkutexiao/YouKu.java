package com.study.hong.zyhdemo.customize.youkutexiao;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.study.hong.zyhdemo.R;


/**
 * Created by shuaihong on 2019/2/21.
 */

public class YouKu extends Activity implements View.OnClickListener {

    //最里面的整个部件1
    private RelativeLayout rl_level1;
    //中间层
    private RelativeLayout rl_level2;
    //最外层
    private RelativeLayout rl_level3;
    //默认各个层都是显示的
    boolean isLevel3Display = true;
    boolean isLevel2Display = true;
    boolean isLevel1Display = true;

    private void initViews() {
        // 添加点击事件，中间的和最里面的一层
        findViewById(R.id.ib_home).setOnClickListener(this);
        findViewById(R.id.ib_menu).setOnClickListener(this);

        rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.you_ku_te_xiao);
        //初始化数据，找到xml里的id
        initViews();
    }

    /**
     * @param keyCode
     * @param event
     * @return
     * 按下按键的时候会执行这个代码
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {  //想看对应的码ctrl+点击KeyEvent
        //keyCode是事件码，每一个按键都有一个码
        Log.d("keyCode", keyCode + "");  //打印按下的按键对应的码
        //如果按下的是菜单按键
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (AnimationUtils.runningAnimationCount > 0) {
                // 当前有动画正在执行, 取消当前事件
                return true;
            }
            if (isLevel1Display) {
                long delay = 0;
                // 隐藏三级菜单
                if (isLevel3Display) {
                    AnimationUtils.rotateOutAnim(rl_level3, 0);
                    isLevel3Display = false;
                    delay += 200;
                }

                // 隐藏二级菜单
                if (isLevel2Display) {
                    AnimationUtils.rotateOutAnim(rl_level2, delay);
                    isLevel2Display = false;
                    delay += 200;
                }

                // 隐藏一级菜单
                AnimationUtils.rotateOutAnim(rl_level1, delay);
            } else {
                // 顺次转进来
                AnimationUtils.rotateInAnim(rl_level1, 0);
                AnimationUtils.rotateInAnim(rl_level2, 200);
                AnimationUtils.rotateInAnim(rl_level3, 400);

                isLevel3Display = true;
                isLevel2Display = true;
            }
            isLevel1Display = !isLevel1Display;

            // **********消费了当前事件,否则手机别的程序会继续判断**********
            return true;


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        //判断当前对否有动画正在执行, 有就取消当前事件，放在switch前，对下面的同时判断
        if (AnimationUtils.runningAnimationCount > 0) {
            return;
        }
        int i = v.getId();
        if (i == R.id.ib_home) {//定义一个变量记录延迟的delay
            long delay = 0;

            //如果当前二级菜单已经显示, 先转出去
            if (isLevel2Display) {
                //注意：得考虑第三层是否显示
                //显示着，就先转出去
                if (isLevel3Display) {
                    AnimationUtils.rotateOutAnim(rl_level3, delay);
                    isLevel3Display = false;
                    delay += 200;
                }

                //如果当前二级菜单已经显示, 先转出去,相当于点击一下转进来
                AnimationUtils.rotateOutAnim(rl_level2, delay);
                isLevel2Display = false;
            } else {
                AnimationUtils.rotateInAnim(rl_level2, delay);
                isLevel2Display = true;
            }


        } else if (i == R.id.ib_menu) {//如果当前三级菜单已经显示, 先转出去
            if (isLevel3Display) {
                //如果当前三级菜单已经显示, 先转出去,相当于点击一下转进来
                AnimationUtils.rotateOutAnim(rl_level3, 0);
                isLevel3Display = false;
            } else {
                AnimationUtils.rotateInAnim(rl_level3, 0);
                isLevel3Display = true;
            }

        } else {
        }
    }
}
