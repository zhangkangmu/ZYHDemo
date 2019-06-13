package com.study.hong.zyhdemo.frame.glide;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.study.hong.zyhdemo.R;

import java.io.File;

public class GlideBaseActivity extends Activity {

    private TextView mTvGlide1;
    private ImageView mIvGlide1;
    private TextView mTvGlide2;
    private ImageView mIvGlide2;
    private TextView mTvGlide3;
    private ImageView mIvGlide3;
    private TextView mTvGlide4;
    private ImageView mIvGlide4;
    private TextView mTvGlide5;
    private ImageView mIvGlide5;
    private TextView mTvGlide6;
    private ImageView mIvGlide6;
    private TextView mTvGlide7;
    private ImageView mIvGlide7;
    private TextView mTvGlide8;
    private ImageView mIvGlide8;
    private TextView mTvGlide9;
    private ImageView mIvGlide9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initID();
        initData();
    }

    private void initData() {
        //（1）加载网络图片
        mTvGlide1.setText("（1）加载网络图片");
        Glide.with(this).load("https://cdn.duitang.com/uploads/item/201309/22/20130922202150_ntvAB.thumb.600_0.jpeg").into(mIvGlide1);
        //（2）加载资源图片
        mTvGlide2.setText("（2）加载资源图片");
        Glide.with(this).load(R.drawable.glide_resouce).into(mIvGlide2);
        //（3）加载本地图片
        mTvGlide3.setText("（3）加载本地图片");
        String path = Environment.getExternalStorageDirectory() + "/meinv1.jpg";
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Glide.with(this).load(uri).into(mIvGlide3);

        // （4）加载网络gif
        mTvGlide4.setText("（4）加载网络gif");
        String gifUrl = "http://b.hiphotos.baidu.com/zhidao/pic/item/faedab64034f78f066abccc57b310a55b3191c67.jpg";
        Glide.with(this).load(gifUrl).placeholder(R.drawable.recyclerview_logo).into(mIvGlide4);
// （5）加载资源gif
        mTvGlide5.setText("（5）加载资源gif");
        Glide.with(this).load(R.drawable.glide_loading).asGif().placeholder(R.drawable.recyclerview_logo).into(mIvGlide5);

        //（6）加载本地gif
        mTvGlide6.setText("（6）加载本地gif");
        String gifPath = Environment.getExternalStorageDirectory() + "/meinv2.jpg";
        File gifFile = new File(gifPath);
        Glide.with(this).load(gifFile).placeholder(R.drawable.recyclerview_logo).into(mIvGlide6);

        //（7）加载本地小视频和快照
        mTvGlide7.setText("（7）加载本地小视频和快照");
        String videoPath = Environment.getExternalStorageDirectory() + "/video.mp4";
        File videoFile = new File(videoPath);
        Glide.with(this).load(Uri.fromFile(videoFile)).placeholder(R.drawable.recyclerview_logo).into(mIvGlide7);

        //（8）设置缩略图比例,然后，先加载缩略图，再加载原图
        mTvGlide8.setText("（8）设置缩略图比例,然后，先加载缩略图，再加载原图");
        String urlPath = Environment.getExternalStorageDirectory() + "/meinv1.jpg";
        Glide.with(this).load(new File(urlPath)).thumbnail(0.1f).centerCrop().placeholder(R.drawable.recyclerview_logo).into(mIvGlide8);

        //（9）先建立一个缩略图对象，然后，先加载缩略图，再加载原图
        mTvGlide9.setText("（9）先建立一个缩略图对象，然后，先加载缩略图，再加载原图");
        DrawableRequestBuilder thumbnailRequest = Glide.with(this).load(new File(urlPath));
        Glide.with(this).load(Uri.fromFile(videoFile)).thumbnail(thumbnailRequest).centerCrop().placeholder(R.drawable.recyclerview_logo).into(mIvGlide9);
    }

    private void initID() {
        setContentView(R.layout.glide_base_use);
        mTvGlide1 = findViewById(R.id.tv_glide_1);
        mIvGlide1 = findViewById(R.id.iv_glide_1);
        mTvGlide2 = findViewById(R.id.tv_glide_2);
        mIvGlide2 = findViewById(R.id.iv_glide_2);
        mTvGlide3 = findViewById(R.id.tv_glide_3);
        mIvGlide3 = findViewById(R.id.iv_glide_3);
        mTvGlide4 = findViewById(R.id.tv_glide_4);
        mIvGlide4 = findViewById(R.id.iv_glide_4);
        mTvGlide5 = findViewById(R.id.tv_glide_5);
        mIvGlide5 = findViewById(R.id.iv_glide_5);
        mTvGlide6 = findViewById(R.id.tv_glide_6);
        mIvGlide6 = findViewById(R.id.iv_glide_6);
        mTvGlide7 = findViewById(R.id.tv_glide_7);
        mIvGlide7 = findViewById(R.id.iv_glide_7);
        mTvGlide8 = findViewById(R.id.tv_glide_8);
        mIvGlide8 = findViewById(R.id.iv_glide_8);
        mTvGlide9 = findViewById(R.id.tv_glide_9);
        mIvGlide9 = findViewById(R.id.iv_glide_9);
    }
}
