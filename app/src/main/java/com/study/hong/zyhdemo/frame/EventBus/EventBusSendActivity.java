package com.study.hong.zyhdemo.frame.EventBus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.frame.EventBus.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by hong on 2019/6/5.
 */

public class EventBusSendActivity extends Activity {
    private Button mBtEventbusSendMain;
    private Button mBtEventbusSendSticky;
    private TextView mTvEventbusSendResult;
    private boolean isFirstFlag = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_eventbus_send);
        initView();
        initListener();

    }
    private void initView() {
        mBtEventbusSendMain = findViewById(R.id.bt_eventbus_send_main);
        mBtEventbusSendSticky = findViewById(R.id.bt_eventbus_send_sticky);
        mTvEventbusSendResult = findViewById(R.id.tv_eventbus_send_result);
    }

    private void initListener() {
        // 主线程发送数据按钮点击事件处理
        mBtEventbusSendMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 4 发送消息
                EventBus.getDefault().post(new MessageEvent("send页面中点击了按钮，主线程发送过来的数据"));

                // 结束当前页面
                finish();
            }
        });

        // 接收粘性事件数据按钮的点击事件处理
        mBtEventbusSendSticky.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {

                if(isFirstFlag) {

                    isFirstFlag = false;

                    // 4 注册
                    EventBus.getDefault().register(EventBusSendActivity.this);
                }

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMainThread(StickyEvent event) {
        // 粘性,显示接收的消息
        mTvEventbusSendResult.setText(event.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 5 解注册
        EventBus.getDefault().unregister(EventBusSendActivity.this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
