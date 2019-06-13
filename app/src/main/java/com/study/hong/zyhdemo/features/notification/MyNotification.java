package com.study.hong.zyhdemo.features.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.study.hong.zyhdemo.MainActivity;
import com.study.hong.zyhdemo.R;

import static android.content.Intent.*;


/**
 * Created by hong on 2019/6/13.
 * https://blog.csdn.net/qq_29428215/article/details/86315295：可以看下这篇文章
 */

public class MyNotification extends Activity implements View.OnClickListener{

    private Button mButton;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton0;
    private NotificationManager nm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.features_notification_mian);
        mButton = findViewById(R.id.button);
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton3 = findViewById(R.id.button3);
        mButton0 = findViewById(R.id.button0);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        initListen();
    }

    private void initListen() {
        mButton.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton0.setOnClickListener(this);
        mButton3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Notification.Builder builder = new Notification.Builder(this);
                Intent mIntent = new Intent(ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
                //延期意图,与Activity则getActivity，与服务有关就get服务
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.drawable.ic_tab_netaudio_press);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_haizeiwang));
                //点击通知后，状态栏是否自动删除通知。
                builder.setAutoCancel(true);
                //设置他为一个正在进行的通知。他们通常是用来表示一个后台任务，用户积极参与或以某种方式正在等待，因此占用设备。
                // （当设置为true的时候就无法清除通知栏，若为false则可以清除。）
                builder.setOngoing( true );
                builder.setContentTitle("帅哥你好啊~我是大标题");
                builder.setContentText("我很好~我是标题内容");
                int pushid = (int) System.currentTimeMillis();

                //判断是否是8.0Android.O
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel chan1 = new NotificationChannel( "push", "Test Channel", NotificationManager.IMPORTANCE_HIGH );
                    chan1.enableLights( true );
                    chan1.enableVibration( true );
                    chan1.setDescription( "push" );
                    chan1.setLockscreenVisibility( Notification.VISIBILITY_PUBLIC );
                    nm.createNotificationChannel( chan1 );
                }

                nm.notify(pushid,builder.build());
//                //链式调用  高版本的写法
//		 Notification noti = new Notification.Builder(this)
//         .setContentTitle("我是大标题")
//         .setContentText("我是标题的内容")
//         .setSmallIcon(R.drawable.ic_haizeiwang)
//         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_haizeiwang))
//         .build();
 //         实现呼吸灯 震动一下
//          noti.defaults = Notification.DEFAULT_ALL;

                break;
            case R.id.button1:
            //折叠式Notification
                Notification.Builder builder2=new Notification.Builder(this);
                Intent intent2=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
                PendingIntent pendingIntent2=PendingIntent.getActivity(this,0,intent2,0);
                builder2.setContentIntent(pendingIntent2);
                builder2.setSmallIcon(R.mipmap.ic_launcher);
                builder2.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                builder2.setAutoCancel(true);
                builder2.setContentTitle("折叠通知");

                RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.features_notification_item);
//                remoteViews.setTextViewText( R.id.text, "内容：XXXXXXXXXX" );
//                remoteViews.setOnClickPendingIntent();

                Notification  notification=builder2.build();
//                notification.contentView = remoteViews;
//                使用这个
                notification.bigContentView=remoteViews;
                nm.notify(1,notification);
                break;
            case R.id.button2:
                //悬挂式布局
                Notification.Builder builder3=new Notification.Builder(this);
                Intent intent3=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jianshu.com/p/82e249713f1b"));
                PendingIntent pendingIntent3=PendingIntent.getActivity(this,0,intent3,0);
                builder3.setContentIntent(pendingIntent3);
                builder3.setSmallIcon(R.mipmap.ic_launcher);
                builder3.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                builder3.setAutoCancel(true);
                builder3.setContentTitle("悬挂通知");
               //点击跳转
                Intent XuanIntent=new Intent();
                XuanIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                XuanIntent.setClass(this,MyNotification.class);
                //延期意图
                PendingIntent xuanpengdIntent=PendingIntent.getActivity(this,0,XuanIntent,PendingIntent.FLAG_CANCEL_CURRENT);
                builder3.setFullScreenIntent(xuanpengdIntent,true);
                nm.notify(2,builder3.build());

                break;
            case R.id.button0:
                fullScreenNotification1(2,"https://www.baidu.com/");
                break;
            case R.id.button3:
                nm.cancelAll();
                break;
        }
    }


    public void fullScreenNotification1(final int notifyId, String url) {
        if (!NotificationUtile.isOpenPermission(this)) {
            return;
        }
        final NotificationManager nm = NotificationUtile.getNotificationManager( this );
        NotificationCompat.Builder builder3 = getNotificationBuilder();
        Intent intent3 = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
        PendingIntent pendingIntent3 = PendingIntent.getActivity( this, 0, intent3, 0 );
        builder3.setContentIntent( pendingIntent3 );
        builder3.setSmallIcon( R.mipmap.ic_launcher );
        builder3.setLargeIcon( BitmapFactory.decodeResource( getResources(), R.mipmap.ic_launcher ) );
        builder3.setAutoCancel( true );
        builder3.setContentTitle( "自定义消息标题" );
        builder3.setContentText( "自定义消息内容" );
        //第一次通知,普通的
        nm.notify( (int) System.currentTimeMillis(), builder3.build() );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//SDK版本大于等于21才有悬挂式通知栏
            Intent XuanIntent = new Intent();
            XuanIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            PendingIntent xuanpengdIntent = PendingIntent.getActivity( this, 0, XuanIntent, PendingIntent.FLAG_CANCEL_CURRENT );
//           设置横幅通知
            builder3.setFullScreenIntent( xuanpengdIntent, true );
            final String notifyTag = notifyId + "10";
            //第二次通知,的
            nm.notify( notifyTag, notifyId, builder3.build() );
            new Thread( new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep( 3000 );//三秒后悬挂式通知消失
                        nm.cancel( notifyTag, notifyId );//按tag id 来清除消息
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } ).start();
        }
    }


    @NonNull
    public NotificationCompat.Builder getNotificationBuilder() {
        //版本大于等于8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            //是否绕过请勿打扰模式
            channel.canBypassDnd();
            //闪光灯
            channel.enableLights(true);
            //锁屏显示通知
            channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            //闪关灯的灯光颜色
            channel.setLightColor(Color.RED);
            //桌面launcher的消息角标
            channel.canShowBadge();
            //是否允许震动
            channel.enableVibration(true);
            //获取系统通知响铃声音的配置
            channel.getAudioAttributes();
            //获取通知取到组
            channel.getGroup();
            //设置可绕过  请勿打扰模式
            channel.setBypassDnd(true);
            //设置震动模式
            channel.setVibrationPattern(new long[]{100, 100, 200});
            //是否会有灯光
            channel.shouldShowLights();
            NotificationUtile.getNotificationManager(this).createNotificationChannel(channel);
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext());
        notification.setContentTitle("新消息来了");
        notification.setContentText("周末到了，不用上班了");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setAutoCancel(true);
        return notification;
    }
}
