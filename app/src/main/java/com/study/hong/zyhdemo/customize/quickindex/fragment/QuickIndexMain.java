package com.study.hong.zyhdemo.customize.quickindex.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.customize.quickindex.adapter.MyAdapter;
import com.study.hong.zyhdemo.customize.quickindex.other.Friend;
import com.study.hong.zyhdemo.customize.quickindex.ui.QuickIndexBar;
import com.study.hong.zyhdemo.customize.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 洪 on 2019/5/8.
 */

public class QuickIndexMain extends SupportFragment {

    @BindView(R.id.quickIndexBar)
    QuickIndexBar quickIndexBar;
    Unbinder unbinder;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.currentWord)
    TextView currentWord;

    private ArrayList<Friend> friends = new ArrayList<Friend>();

    public static QuickIndexMain newInstance() {
        Bundle args = new Bundle();
        QuickIndexMain fragment = new QuickIndexMain();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customize_quickindex, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //1.准备数据
        fillList();
        //2.对数据进行排序
        Collections.sort(friends);
        //3.设置Adapter
        listview.setAdapter(new MyAdapter(friends, getContext()));
        Utils.getPinyin("张宇洪");


        quickIndexBar.setOnTouchListener(new QuickIndexBar.OnTouchListener() {
            @Override
            public void onTouchLetter(String letter) {
                Log.d("zyh", letter);
                //根据当前触摸的字母，去集合中找那个item的首字母和letter一样，然后将对应的item放到屏幕顶端
                for (int i = 0; i < friends.size(); i++) {
                    String firstWord = friends.get(i).getPinyin().charAt(0) + "";
                    if (letter.equals(firstWord)) {
                        //说明找到了，那么应该讲当前的item放到屏幕顶端
                        listview.setSelection(i);
                        break;//只需要找到第一个就行
                    }
                }
                //显示当前触摸的字母
                showCurrentWord(letter);
            }
        });
        ViewHelper.setScaleX(currentWord,0);
        ViewHelper.setScaleY(currentWord,0);
    }
    private boolean isScale = false;
    private Handler handler = new Handler();

    private void showCurrentWord(String letter) {
//        currentWord.setVisibility(View.VISIBLE);
        ViewPropertyAnimator.animate(currentWord).scaleX(1f).setDuration(500).start();
        ViewPropertyAnimator.animate(currentWord).scaleY(1f).setDuration(500).start();
        currentWord.setText(letter);
        //动画会超过目标值一些，然后再弹回来。效果看起来有点像你一屁股坐在沙发上后又被弹起来一点的感觉
        //回弹动画
        if(!isScale){
            isScale = true;
            ViewPropertyAnimator.animate(currentWord).scaleX(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(450).start();
            ViewPropertyAnimator.animate(currentWord).scaleY(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(450).start();
        }
        //记住要移除这个消息，否则会闪烁
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                currentWord.setVisibility(View.INVISIBLE);
                //增加隐藏的动画
                ViewPropertyAnimator.animate(currentWord).scaleX(0f).setDuration(500).start();
                ViewPropertyAnimator.animate(currentWord).scaleY(0f).setDuration(500).start();
                isScale=false;
            }
        }, 500);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void fillList() {
        // 虚拟数据
        friends.add(new Friend("aa李伟"));
        friends.add(new Friend("张三"));
        friends.add(new Friend("阿三"));
        friends.add(new Friend("阿四"));
        friends.add(new Friend("段誉"));
        friends.add(new Friend("段正淳"));
        friends.add(new Friend("张三丰"));
        friends.add(new Friend("陈坤"));
        friends.add(new Friend("林俊杰1"));
        friends.add(new Friend("陈坤2"));
        friends.add(new Friend("王二a"));
        friends.add(new Friend("林俊杰a"));
        friends.add(new Friend("张四"));
        friends.add(new Friend("林俊杰"));
        friends.add(new Friend("王二"));
        friends.add(new Friend("王二b"));
        friends.add(new Friend("赵四"));
        friends.add(new Friend("杨坤"));
        friends.add(new Friend("赵子龙"));
        friends.add(new Friend("杨坤1"));
        friends.add(new Friend("李伟1"));
        friends.add(new Friend("宋江"));
        friends.add(new Friend("宋江1"));
        friends.add(new Friend("m伟3"));
        friends.add(new Friend("LLL"));
        friends.add(new Friend("bbbddd"));
        friends.add(new Friend("BBBDDD"));
    }
}
