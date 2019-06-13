package com.study.hong.zyhdemo.frame.EventBus;

import android.app.Activity;
import android.content.Intent;
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

public class EventBusMain extends Activity {

    private Button mBtEventbusSend;
    private Button mBtEventbusSticky;
    private TextView mTvEventbusResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_mian);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        // 跳转到发送页面
        mBtEventbusSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EventBusMain.this, EventBusSendActivity.class);

                startActivity(intent);
            }
        });

        // 发送粘性事件到发送页面
        mBtEventbusSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2 发送粘性事件
                EventBus.getDefault().postSticky(new StickyEvent("这是主页面发出来的消息,我是粘性事件"));

                // 跳转到发送数据页面
                Intent intent = new Intent(EventBusMain.this, EventBusSendActivity.class);

                startActivity(intent);
            }
        });
    }

    private void initData() {
        // 1注册广播,也就是订阅者
        EventBus.getDefault().register(EventBusMain.this);
    }

    private void initView() {
        mBtEventbusSend = findViewById(R.id.bt_eventbus_send);
        mBtEventbusSticky = findViewById(R.id.bt_eventbus_sticky);
        mTvEventbusResult = findViewById(R.id.tv_eventbus_result);
    }

    // 5接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MesssageEventBus(MessageEvent event){
        // 显示接收的消息
        mTvEventbusResult.setText(event.name);
    }
//

//    /**
//     * 使用onEvent来接收事件，那么接收事件和分发事件在一个线程中执行
//     * @param event
//     */
//    public void onEvent(MessageEvent event) {
//
//    }
//
//    /**
//     * 使用onEventMainThread来接收事件，那么不论分发事件在哪个线程运行，接收事件永远在UI线程执行
//     * 可以用来更新UI
//     * @param event
//     */
//    public void onEventMainThread(MessageEvent event) {
//        // 显示接收的消息
//        mTvEventbusResult.setText(event.name);
//    }
//
//    /**
//     * 使用onEventBackgroundThread来接收事件，如果分发事件在子线程运行，那么接收事件直接在同样线程
//     * 运行，如果分发事件在UI线程，那么会启动一个子线程运行接收事件
//     * @param event
//     */
//    public void onEventBackgroundThread(MessageEvent event) {
//
//    }
//
//    /**
//     * 使用onEventAsync接收事件，无论分发事件在（UI或者子线程）哪个线程执行，接收都会在另外一个子线程执行
//     * @param event
//     */
//    public void onEventAsync(MessageEvent event) {
//
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 2 解注册
        EventBus.getDefault().unregister(EventBusMain.this);
    }

}
