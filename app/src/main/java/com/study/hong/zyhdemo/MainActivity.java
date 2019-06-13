package com.study.hong.zyhdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.study.hong.zyhdemo.base.BaseActivity;
import com.study.hong.zyhdemo.base.BaseFragment;
import com.study.hong.zyhdemo.customize.CustomizeMain;
import com.study.hong.zyhdemo.features.FeaturesMain;
import com.study.hong.zyhdemo.frame.Frame;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends SupportActivity implements View.OnClickListener {

    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private SupportFragment mContent;
    private List<BaseFragment> mBaseFragment;
    private FrameLayout mFlContent;
    private RadioButton mBtKuangjia;
    private RadioButton mBtZidingyi;
    private RadioButton mBtTexiao;
    private RadioButton mBtYangshi;
    private RadioGroup mRadioGroup;
//    SupportFragment frame =Frame.newInstance();
//    SupportFragment customizemain =CustomizeMain.newInstance();
//    SupportFragment featuresmain =FeaturesMain.newInstance();
//    SupportFragment[] mBaseFragment ={frame,customizemain,featuresmain};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlContent = findViewById(R.id.fl_content);
        mBtKuangjia = findViewById(R.id.bt_kuangjia);
        mBtZidingyi = findViewById(R.id.bt_zidingyi);
        mBtTexiao = findViewById(R.id.bt_texiao);
        mBtYangshi = findViewById(R.id.bt_yangshi);
        mRadioGroup = findViewById(R.id.radioGroup);
        initListen();
        initFragment();
        loadRootFragment(R.id.fl_content, mBaseFragment.get(0));
    }

    private void initListen() {
        mBtKuangjia.setOnClickListener(this);
        mBtZidingyi.setOnClickListener(this);
        mBtTexiao.setOnClickListener(this);
        mBtYangshi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_kuangjia:
                loadRootFragment(R.id.fl_content, mBaseFragment.get(1));
                break;
            case R.id.bt_zidingyi:
                loadRootFragment(R.id.fl_content, mBaseFragment.get(0));
                break;
            case R.id.bt_texiao:
                loadRootFragment(R.id.fl_content, mBaseFragment.get(2));
                break;
            case R.id.bt_yangshi:
                loadRootFragment(R.id.fl_content, mBaseFragment.get(3));
                break;
            default:
                break;

        }
    }
//
//    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
//
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            switch (checkedId) {
//                case R.id.bt_kuangjia://常用框架
//
//                    position = 0;
//                    break;
//                case R.id.bt_zidingyi://第三方
//                    if (findFragment(FeaturesMain.class) == null) {
//                        loadRootFragment(R.id.fl_content, FeaturesMain.newInstance());  // 加载根Fragment
//                    }
//                    position = 1;
//                    break;
//                case R.id.bt_texiao://自定义
//                    if (findFragment(Frame.class) == null) {
//                        loadRootFragment(R.id.fl_content, Frame.newInstance());  // 加载根Fragment
//                    }
//                    position = 2;
//                    break;
//                case R.id.bt_yangshi://其他
//                    if (findFragment(Frame.class) == null) {
//                        loadRootFragment(R.id.fl_content, Frame.newInstance());  // 加载根Fragment
//                    }
//                    position = 3;
//                    break;
//                default:
//                    position = 0;
//                    break;
//            }
//
//            //根据位置得到对应的Fragment
//            BaseFragment to = getFragment();
//            //替换
//            switchFrament(mContent, to);
//
//        }
//    }
//
//
//    /**
//     * 根据位置得到对应的Fragment
//     *
//     * @return
//     */
//    private BaseFragment getFragment() {
//        BaseFragment fragment = mBaseFragment.get(position);
//        return fragment;
//    }
//
//    /**
//     * @param from 刚显示的Fragment,马上就要被隐藏了
//     * @param to   马上要切换到的Fragment，一会要显示
//     */
//    private void switchFrament(SupportFragment from, SupportFragment to) {
//        if (from != to) {
//            mContent = to;
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            //才切换
//            //判断有没有被添加
//            if (!to.isAdded()) {
//                //to没有被添加
//                //from隐藏
//                if (from != null) {
//                    ft.hide(from);
//                }
//                //添加to
//                if (to != null) {
////                    ft.add(R.id.fl_content, to).commit();
//                    if (findFragment(CustomizeMain.class) == null) {
//                        loadRootFragment(R.id.fl_content, CustomizeMain.newInstance());  // 加载根Fragment
//                    }
//                }
//            } else {
//                //to已经被添加
//                // from隐藏
//                if (from != null) {
//                    ft.hide(from);
//                }
//                //显示to
//                if (to != null) {
//                    ft.show(to).commit();
//                }
//            }
//        }
//
//    }
//
    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(CustomizeMain.newInstance());//常用框架Fragment
        mBaseFragment.add(Frame.newInstance());//第三方Fragment
        mBaseFragment.add(FeaturesMain.newInstance());//自定义控件Fragment
        mBaseFragment.add(FeaturesMain.newInstance());//其他Fragment
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
