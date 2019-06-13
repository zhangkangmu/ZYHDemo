package com.study.hong.zyhdemo.features.advertisement;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.study.hong.zyhdemo.R;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by shuaihong on 2019/2/22.
 * 智慧北京TabDetailPager.java里也有用到
 */

public class Advertisement extends SupportFragment implements ViewPager.OnPageChangeListener {
    /**
     * handler 切换轮播图消息
     */
    private static final int MESSAGE_CHANGE = 1;

    private ViewPager viewpager;
    private TextView tv_desc;
    private LinearLayout ll_point_container;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private int lastEnablePoint=0;
    private String[] contentDescs;
    boolean isRunning=false;
    private Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            switch (msg.what) {      //判断标志位
//                case MESSAGE_CHANGE:
//                    Log.e("zyh","MESSAGE_CHANGE");
//                    viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
//                    break;
//            }
            viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
            sendEmptyMessageDelayed(0,2000);
//            sendMessageDelayed(message,2000);
        }
    };
    private Message message;

    public static Advertisement newInstance() {
        Bundle args = new Bundle();
        Advertisement fragment = new Advertisement();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.advertisement_main, container, false);
//        setContentView(R.layout.advertisement_main);
// 初始化布局 View视图
        initView(view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Model数据
        initData();
        // Controller 控制器
        initAdapter();

        //自动无限循环开始
        new Thread(){
            @Override
            public void run() {
                isRunning = true;
//                while(isRunning){
                    //睡眠2秒
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    //开始切换下一张图片
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.d("主线程中设置的当前位置",viewpager.getCurrentItem()+"");
//                            viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
//                        }
//                    });

//                message = Message.obtain();
//                    message.what=MESSAGE_CHANGE;
                if (isRunning){
                    Log.e("zyh","sendMessageDelayed");
                    mHandler.sendEmptyMessageDelayed(0,2000);
//                    mHandler.sendMessageDelayed(message,2000);
                }
                }
//            }
        }.start();
    }

    @Override
    public void onDestroyView() {
        isRunning = false;
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroyView();
    }

    //初始化数据，找到控件id以及设计页面更新监听
    private void initView(View view) {
        //1.找到id
        viewpager = view.findViewById(R.id.viewpager);

        //设置滑动监听
        viewpager.setOnPageChangeListener(this);
        //viewpager默认缓冲左右一个照片，想缓冲两个则增加一下方法
        //viewpager.setOffscreenPageLimit(2);
        tv_desc = view.findViewById(R.id.tv_desc);
        ll_point_container = view.findViewById(R.id.ll_point_container);
        viewpager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("ACTION_DOWN");
                        // 停止广告自动轮播
                        // 删除handler的所有消息
                        mHandler.removeCallbacksAndMessages(null);
                        // mHandler.post(new Runnable() {
                        //
                        // @Override
                        // public void run() {
                        // //在主线程运行
                        // }
                        // });
                        break;
                    case MotionEvent.ACTION_CANCEL:// 取消事件,
                        // 当按下viewpager后,直接滑动listview,导致抬起事件无法响应,但会走此事件
                        System.out.println("ACTION_CANCEL");
                        // 启动广告
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("ACTION_UP");
                        // 启动广告
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;

                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initAdapter() {
        //让文本显示
        tv_desc.setText(contentDescs[0]);

        //默认让第一个点高量显示
        ll_point_container.getChildAt(0).setEnabled(true);

        //2.设置适配器
        viewpager.setAdapter(new MyPagerAdapter());

        lastEnablePoint=0;

        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());  //也可以是这个
        // 2147483647 / 2 = 1073741823 - (1073741823 % 5)
        viewpager.setCurrentItem(5000000); // 设置到某个位置，这样会简单点
    }

    //4.初始化数据
    private void initData() {
        //建立集合存放图片，因为后面要遍历各个图片并且设置文字之类的内容,而且不能直接通过找寻图片放在集合中
        imageResIds = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};

        //准备文字随图片变换，文本描述
        contentDescs = new String[]{
                "巩俐不低俗，我就不能低俗",
                "扑树又回来啦！再唱经典老歌引万人大合唱",
                "揭秘北京电影如何升级",
                "乐视网TV版大派送",
                "热血屌丝的反杀"
        };

        ImageView imageView;
        //图片下的小点
        View pointView;
        //设置点儿的布局，相当于加载类
        LinearLayout.LayoutParams layoutParams;
        imageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < imageResIds.length; i++) {
            //ImageView实例化需要传入一个context
            imageView = new ImageView(_mActivity);
            //注意是有Resource的
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);

            // 加小白点, 指示器,在图片下方
            pointView = new View(_mActivity);
            pointView.setBackgroundResource(R.drawable.advertisement_selector_bg_point);
            layoutParams = new LinearLayout.LayoutParams(10, 10);

            if(i != 0) {
                layoutParams.leftMargin = 20;
            }
            ///设置默认所有都不可用,响应seletor_bg_point.xml里的内容
            pointView.setEnabled(false);
            //让LinearLayout加载上点儿容器
            ll_point_container.addView(pointView,layoutParams);


        }
    }

    //3.建立适配器
    class MyPagerAdapter extends PagerAdapter {
        //可以滑动的次数
        @Override
        public int getCount() {
            //设置无限滑动，2^32次
            return Integer.MAX_VALUE;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);  原先的返回值
            //创建一个新的位置记录滑动大于5的数值，并且进行运算
            int newPosition=position%imageViewList.size();
            ImageView imageView = imageViewList.get(newPosition);
            // a. 把View对象添加到container中
            container.addView(imageView);
            // b. 把View对象返回给框架, 适配器
            return imageView; // 必须重写, 否则报异常
        }

        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object); 原先的方法
            // object 要销毁的对象
            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }

        // 3. 指定复用的判断逻辑, 固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //System.out.println("isViewFromObject: "+(view == object));
            // 当划到新的条目, 又返回来, view是否可以被复用.
            // 返回判断规则
            return view == object;
        }
    }





    //后面的三个方法都是pagerview的监听方法
    // 滚动时调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    // 新的条目被选中时调用
    @Override
    public void onPageSelected(int position) {
        Log.d("onPageSelected",""+position);
        //创建一个新的位置记录滑动大于5的数值，并且进行运算
        int newPosition=position%imageViewList.size();
        //设置文本
        tv_desc.setText(contentDescs[newPosition]);

        //设置点儿随不同的图片更改不同状态方法，使用一个值记录上一次的位置，新的一个位置出现就让前面一个为false则可，
        //注意这个顺序，先是设置前面一个为false
        ll_point_container.getChildAt(lastEnablePoint).setEnabled(false);
        ll_point_container.getChildAt(newPosition).setEnabled(true);
        //注意这个顺序，先禁用上一个的位置点儿
        lastEnablePoint = newPosition;
    }

    // 滚动状态变化时调用
    @Override
    public void onPageScrollStateChanged(int state) {
    }


}
